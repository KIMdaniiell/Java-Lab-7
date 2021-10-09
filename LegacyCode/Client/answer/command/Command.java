package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public interface Command {
    void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment);
}
