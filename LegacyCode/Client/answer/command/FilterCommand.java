package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public class FilterCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            for (MusicBand band : mystack) {
                System.out.println(band.toString());
            }
        }
    }
}
