package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Iterator;
import java.util.Stack;

public class RemoveByIdCommand implements Command {
    private final Stack<MusicBand> mystack;

    public RemoveByIdCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        Integer id = Integer.valueOf(args);
        Iterator<MusicBand> iterator = mystack.iterator();
        boolean not_deleted = true;

        while (iterator.hasNext()) {
            MusicBand band = iterator.next();
            Integer bandid = Integer.valueOf(band.getId().toString());
            if (bandid.equals(id)) {
                String note = "Удален элемент - " + band.toString();
                //System.out.println(note);
                iterator.remove();
                not_deleted = false;
                note = "Элемент с данным описанием был удален.";
                //System.out.println(note);
                System.out.println("\t-[remove_by_id]\t"+CommandAccomplishment.SUCCESSFUL);
                return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
            }
        }
        if (not_deleted) {
            String note = "Элемента с таким описанием не существует.";
            //System.out.println(note);
            System.out.println("\t-[remove_by_id]\t"+CommandAccomplishment.NOTFOUND);
            return new Response(CommandAccomplishment.NOTFOUND, mystack);
        }
        System.out.println("\t-[remove_by_id]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
