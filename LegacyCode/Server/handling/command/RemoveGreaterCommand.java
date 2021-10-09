package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Iterator;
import java.util.Stack;

public class RemoveGreaterCommand implements Command {
    private final Stack<MusicBand> mystack;

    public RemoveGreaterCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        MusicBand someband = musicBand;

        Iterator<MusicBand> iterator = mystack.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().compareTo(someband) > 0) {
                iterator.remove();
            }
        }
        System.out.println("\t-[remove_greater]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
