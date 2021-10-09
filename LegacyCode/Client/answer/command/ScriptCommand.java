package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public class ScriptCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            System.out.println("Скрипт завершен.");
        }
    }
}
