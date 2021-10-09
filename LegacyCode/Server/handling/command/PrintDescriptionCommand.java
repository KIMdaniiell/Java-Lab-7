package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Stack;

public class PrintDescriptionCommand implements Command {
    private final Stack<MusicBand> mystack;

    public PrintDescriptionCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        // Парсинг происходит на стороне клиента
        /*
        for (MusicBand band : mystack) {
            System.out.println(band.getDescription());
        }
         */
        System.out.println("\t-[print_field_descending_description]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
