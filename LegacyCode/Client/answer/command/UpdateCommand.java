package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public class UpdateCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.NOTFOUND) {
            String note = "Некорректный ввод параметра ID. Элемента с таким ID не существует.";
            System.out.println(note);
        } else if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            String note = "Элемент с данным ID был обновлен.";
            System.out.println(note);
        }
    }
}
