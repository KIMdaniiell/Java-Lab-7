package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public class RemoveGreaterCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            String note = "Элементы, превышающие данный, удалены.";
            System.out.println(note);
        }
    }
}
