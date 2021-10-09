package console;

import answer.AnswerHandler;
import connection.Connector;
import format.CommandAccomplishment;
import format.MusicBand;
import format.RequestWrapper;
import format.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class ClientScriptCommand {

    public Response execute(RequestWrapper requestWrapper, Connector connector, int stackPointer) throws NoSuchElementException, ClassNotFoundException, IOException {
        System.out.println("Запущен скрипт: [" + requestWrapper.getArg() + "]. Текущая глубина рекурсии - " + (5 - stackPointer) + " .");
        Response response = new Response(CommandAccomplishment.SUCCESSFUL, new Stack<MusicBand>());

        String script_path = requestWrapper.getArg();
        File script_file = new File(script_path);
        Scanner scanner = new Scanner(script_file);

        ConsoleReader consoleReader = new ConsoleReader(connector, scanner);
        AnswerHandler answerHandler = new AnswerHandler();
        if (!script_file.exists()) {
            throw new FileNotFoundException();
        }
        if ((5 - stackPointer) >= 5) {
            System.out.println("Превышена глубина рекурсии: " + (5 - stackPointer));
        } else if (scanner.hasNext()) {
            boolean scriptIsStopped = false;
            do {
                RequestWrapper scriptRequestWrapper = consoleReader.read(answerHandler, stackPointer);
                if (scriptRequestWrapper != null) {
                    if (scriptRequestWrapper.getCommand().equals("exit")) {
                        scriptIsStopped = true;
                    } else {
                        Response scriptResponse = connector.sendRequestWrapper(scriptRequestWrapper);
                        answerHandler.readAnswer(scriptResponse, scriptRequestWrapper);
                    }
                }
            } while (scanner.hasNext() && (!scriptIsStopped));
        } else {
            System.out.println("Скрип не содержит команд.");
        }

        return response;
    }
}

