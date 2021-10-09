package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public class ExitCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        System.out.println("Завершение работы программы ...");
    }
}
