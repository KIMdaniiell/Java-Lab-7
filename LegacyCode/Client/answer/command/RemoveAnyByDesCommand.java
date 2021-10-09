package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public class RemoveAnyByDesCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.NOTFOUND) {
            String note = "Элемента с данным описанием не существует.";
            System.out.println(note);
        } else if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            String note = "Элемент с данным описанием был удален.";
            System.out.println(note);
        }
    }
}
