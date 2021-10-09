package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.MusicGenre;
import format.Response;

import java.util.Stack;

public class FilterCommand implements Command {
    private final Stack<MusicBand> mystack;

    public FilterCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        String offer = args;
        Stack<MusicBand> newStack = new Stack<MusicBand>();
        for (MusicBand band : mystack) {
            if (band.getGenre().compareTo(MusicGenre.valueOf(offer)) < 0) {
                System.out.println(band.toString());
            }
            newStack.push(band);
        }
        System.out.println("\t-[filter_less_than_genre]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, newStack);
    }
}
