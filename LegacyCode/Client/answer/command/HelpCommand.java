package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public class HelpCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        String note = "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +
                "sort : отсортировать коллекцию в естественном порядке\n" +
                "remove_any_by_description description : удалить из коллекции один элемент, значение поля description которого эквивалентно заданному\n" +
                "filter_less_than_genre genre : вывести элементы, значение поля genre которых меньше заданного\n" +
                "print_field_descending_description : вывести значения поля description всех элементов в порядке убывания";

        System.out.println(note);
    }
}
