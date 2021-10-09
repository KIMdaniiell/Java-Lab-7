package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;
import java.util.stream.Collectors;

public class SortCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            mystack = mystack.stream().sorted((o1, o2) -> {
                int result = o1.getName().compareTo(o2.getName());
                if (result == 0) {
                    result = o1.getGenre().compareTo(o2.getGenre());
                }
                return result;
            }).collect(Collectors.toCollection(Stack<MusicBand>::new));
            String note = "Коллекция отсортирована.";
            System.out.println(note);
            new InfoCommand().execute(mystack, CommandAccomplishment.SUCCESSFUL);
        }
    }
}
