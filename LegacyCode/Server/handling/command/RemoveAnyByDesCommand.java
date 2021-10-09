package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Iterator;
import java.util.Stack;

public class RemoveAnyByDesCommand implements Command {
    private final Stack<MusicBand> mystack;

    public RemoveAnyByDesCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {

        String description = args;
        Iterator<MusicBand> iterator = mystack.iterator();
        boolean not_deleted = true;

        while (iterator.hasNext()) {
            MusicBand band = iterator.next();
            if (not_deleted && band.getDescription().equals(description)) {
                iterator.remove();
                not_deleted = false;
                String note = "Элемент с данным описанием был удален.";
                //System.out.println(note);
                System.out.println("\t-[remove_any_by_description]\t"+CommandAccomplishment.SUCCESSFUL);
                return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
            }
        }
        if (not_deleted) {
            String note = ("Элемента с таким описанием не существует.");
            //System.out.println(note);
            System.out.println("\t-[remove_any_by_description]\t"+CommandAccomplishment.NOTFOUND);
            return new Response(CommandAccomplishment.NOTFOUND, mystack);
        }
        System.out.println("\t-[remove_any_by_description]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
