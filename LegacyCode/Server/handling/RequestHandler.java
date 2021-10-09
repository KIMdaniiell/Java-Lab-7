package handling;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;
import handling.command.*;

import java.util.HashMap;
import java.util.Stack;

public class RequestHandler {
    /*
     * This class is a command receiver.
     * It contains a HashSet of available commands.
     * Some of commands are handling processed exclusively on the client's side,
     * so not all of them are presented in the set.
     * The execute method returns a response wrapper class.
     *
     */


    private final HashMap<String, Command> commandHashMap;
    private final String data_path;
    private final Stack<MusicBand> mystack;
    private Response response;

    public RequestHandler(String data_path, Stack<MusicBand> collection) {
        this.data_path = data_path;
        commandHashMap = new HashMap<>();
        mystack = collection;
        initHashMap();
    }

    public Response execute(String name, String args, MusicBand band) {
        if (commandHashMap.containsKey(name)) {
            response = commandHashMap.get(name).execute(args, band);
        } else {
            System.out.println("no such command");
            return new Response(CommandAccomplishment.NOSUCHCOMMAND, null);
        }
        return response;
    }

    public void initHashMap() {

        commandHashMap.put("info", new InfoCommand(mystack));
        commandHashMap.put("show", new ShowCommand(mystack));
        commandHashMap.put("clear", new ClearCommand(mystack));
        commandHashMap.put("reorder", new ReorderCommand(mystack));
        commandHashMap.put("sort", new SortCommand(mystack));
        commandHashMap.put("print_field_descending_description", new PrintDescriptionCommand(mystack));
        commandHashMap.put("filter_less_than_genre", new FilterCommand(mystack));
        commandHashMap.put("add", new AddCommand(mystack));
        commandHashMap.put("remove_greater", new RemoveGreaterCommand(mystack));
        commandHashMap.put("update", new UpdateCommand(mystack));
        commandHashMap.put("remove_any_by_description", new RemoveAnyByDesCommand(mystack));
        commandHashMap.put("remove_by_id", new RemoveByIdCommand(mystack));

        //commandHashMap.put("exit", new ExitCommand(mystack)); реазилуется на стороне клиента
        //commandHashMap.put("help", new HelpCommand(mystack)); реазилуется на стороне клиента
        //commandHashMap.put("save", new SaveCommand(mystack));
        //commandHashMap.put("execute_script", new ScriptCommand(mystack)); реазилуется на стороне клиента

    }
}
