package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Collections;
import java.util.Stack;

public class UpdateCommand implements Command {
    private final Stack<MusicBand> mystack;

    public UpdateCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        Integer curentid;
        try {
            curentid = Integer.valueOf(args);
            boolean has_this_id = false;
            for (MusicBand band : mystack) {
                if (band.getId().equals(curentid)) {
                    has_this_id = true;
                }
            }
            if (has_this_id == false) {
                String note = "Некорректный ввод параметра ID. Элемента с таким ID не существует.";
                //System.out.println(note);
                System.out.println("\t-[update]\t"+CommandAccomplishment.NOTFOUND);
                return new Response(CommandAccomplishment.NOTFOUND, mystack);
            } else {
                MusicBand oldband = new MusicBand();
                MusicBand newband = musicBand;
                Integer id = curentid;
                newband.saveID(id);
                for (MusicBand band : mystack) {
                    if (band.getId().equals(id)) {
                        oldband = band;
                    }
                }
                Collections.replaceAll(mystack, oldband, newband);
                String note = "Элемент с данным ID был обновлен.";
                System.out.println(note);
                System.out.println("\t-[update]\t"+CommandAccomplishment.SUCCESSFUL);
                return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
            }
        } catch (NumberFormatException e) {
            System.out.println("\t-[update]\t"+CommandAccomplishment.NOTFOUND);
            return new Response(CommandAccomplishment.NOTFOUND, mystack);
        }
    }
}
