package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.time.LocalDate;
import java.util.Stack;

public class InfoCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            String note = "Тип : " + mystack.getClass().getName();
            System.out.println(note);
            note = "Количество элементов : " + mystack.size();
            System.out.println(note);
            note = "Дата инициализации : " + LocalDate.now();
            System.out.println(note);
            for (MusicBand band : mystack) {
                System.out.println("\t-" + band.toString());
            }
        }
    }
}
