package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;
import java.util.stream.Collectors;

public class ReorderCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            mystack = mystack.stream().sorted((o1, o2) -> {
                int result = o2.getName().compareTo(o1.getName());
                if (result == 0) {
                    result = o2.getGenre().compareTo(o1.getGenre());
                }
                return result;
            }).collect(Collectors.toCollection(Stack<MusicBand>::new));
            String note = "Коллекция отсортирована в обратном порядке.";
            System.out.println(note);
            new InfoCommand().execute(mystack, CommandAccomplishment.SUCCESSFUL);
        }
    }
}
