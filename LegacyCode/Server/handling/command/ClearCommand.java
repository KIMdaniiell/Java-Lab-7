package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Stack;

public class ClearCommand implements Command {
    private final Stack<MusicBand> mystack;

    public ClearCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand band) {
        mystack.clear();
        //System.out.println("Коллекция очищена.");
        System.out.println("\t-[clear]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
