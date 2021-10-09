package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Stack;

public class AddCommand implements Command {
    private final Stack<MusicBand> mystack;

    public AddCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        MusicBand myband = musicBand;
        myband.setId(mystack);
        mystack.push(myband);
        //System.out.println("Был добавлен новый объект - " + myband.toString());
        System.out.println("\t-[add]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
