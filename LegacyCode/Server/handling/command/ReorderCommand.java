package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Stack;
import java.util.stream.Collectors;

public class ReorderCommand implements Command {
    private Stack<MusicBand> mystack;

    public ReorderCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        //По сути бесполезная команда, т.к. элементы передаются клиенту отсортированными по размеру (требование варианта).

        mystack = mystack.stream().sorted((o1, o2) -> {
            int result = o2.getName().compareTo(o1.getName());
            if (result == 0) {
                result = o2.getGenre().compareTo(o1.getGenre());
            }
            return result;
        }).collect(Collectors.toCollection(Stack<MusicBand>::new));

        String note = "Коллекция отсортирована в обратном порядке.";
        //System.out.println(note);
        System.out.println("\t-[reorder]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
