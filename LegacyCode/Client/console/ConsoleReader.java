package console;

import answer.AnswerHandler;
import answer.command.ExitCommand;
import connection.Connector;
import format.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class ConsoleReader {
    /*
     * This class is responsible for processing commands from the given
     * InputStream instance (System.in or the script file);
     * Commands and their arguments are wrapped in to RequestWrapper class.
     *
     * There are certain (such as help, execute_script and exit) commands,
     * which do not require processing on the server side.
     * They are handled in client application.
     */
    private final HashSet<String> commandHashSet = new HashSet<String>();
    Scanner scanner;
    Connector connector;

    public ConsoleReader(Connector connector, Scanner scanner) {
        this.scanner = scanner;
        this.connector = connector;
        initHashSet();
    }

    private void initHashSet() {
        commandHashSet.add("help");
        commandHashSet.add("info");
        commandHashSet.add("show");
        commandHashSet.add("clear");
        commandHashSet.add("exit");
        commandHashSet.add("reorder");
        commandHashSet.add("sort");
        commandHashSet.add("print_field_descending_description");
        commandHashSet.add("filter_less_than_genre");
        commandHashSet.add("add");
        commandHashSet.add("remove_greater");
        commandHashSet.add("update");
        commandHashSet.add("remove_any_by_description");
        commandHashSet.add("remove_by_id");
        commandHashSet.add("execute_script");
    }

    public RequestWrapper read(AnswerHandler answerHandler, int scriptRecursionDepth) throws NoSuchElementException, ClassNotFoundException, IOException {
        RequestWrapper requestWrapper = getRequestWrapper();

        String commandName = requestWrapper.getCommand();
        switch (commandName) {
            case "help": {
                Response response = new Response(CommandAccomplishment.SUCCESSFUL, new Stack<MusicBand>());
                answerHandler.readAnswer(response, requestWrapper);
                requestWrapper = null;
                break;
            }
            case "exit": {
                new ExitCommand().execute(new Stack<MusicBand>(), CommandAccomplishment.SUCCESSFUL);
                break;
            }
            case "execute_script": {
                Response response = new ClientScriptCommand().execute(requestWrapper, connector, scriptRecursionDepth - 1);
                answerHandler.readAnswer(response, requestWrapper);
                requestWrapper = null;
                break;
            }
            case "remove_by_id":{
                try {
                    Integer.parseInt(requestWrapper.getArg());
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Error: Invalid command argument format.");
                    requestWrapper = null;
                    break;
                }
            }
            case "filter_less_than_genre":{
                if (!MusicGenre.contains(requestWrapper.getArg())){
                    System.out.println("Error: Invalid command argument format.");
                    requestWrapper = null;
                    break;
                }
            }
        }

        return requestWrapper;
    }

    private RequestWrapper getRequestWrapper() {
        RequestWrapper requestWrapper = new RequestWrapper();
        String commandWord = null;
        String commandArgument = null;
        while (!commandHashSet.contains(commandWord)) {
            if (commandWord != null) {
                System.out.println("Error: Unknown command.");
            }
            String commandLine = scanner.nextLine();
            commandWord = commandLine.toLowerCase().split(" ")[0];
            commandArgument = commandLine.substring(commandWord.length()).trim();
        }
        requestWrapper.setCommand(commandWord);
        requestWrapper.setArg(commandArgument);
        if ((commandWord.equals("add")) || (commandWord.equals("update")) || (commandWord.equals("remove_greater"))) {
            MusicBand musicBand = new BandBuilder().readNewMusicBand(scanner);
            requestWrapper.setMusicBand(musicBand);
        }
        return requestWrapper;
    }
}
