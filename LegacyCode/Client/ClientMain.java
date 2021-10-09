import answer.AnswerHandler;
import connection.Connector;
import console.ConsoleReader;
import format.RequestWrapper;
import format.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ClientMain {
    /*
     * This client is based on TCP protocol.
     * It sends RequestWrapper instances and
     * receives Response as a result.
     */

    public static void main(String[] args) {
        int serverPort = 40748;
        String sport = System.getenv("SPORT");                          // default value is "40748"
        if (sport == null){
            System.out.println("Error: Environmental variable [SPORT] not found.");
        } else {
            serverPort = Integer.parseInt(sport);
            if ((serverPort<0)||(serverPort>65535)) {
                System.out.println("Error: Port parameter is outside the specified range of valid port values.");
            } else {
                final int maxScriptRecursionDepth = 5;

                Connector connector = new Connector(serverPort);
                ConsoleReader consoleReader = new ConsoleReader(connector, new Scanner(System.in));
                AnswerHandler answerHandler = new AnswerHandler();


                boolean isStopped = false;

                while (!isStopped) {
                    try {
                        RequestWrapper requestWrapper = consoleReader.read(answerHandler, maxScriptRecursionDepth);
                        if (requestWrapper != null) {
                            if (requestWrapper.getCommand().equals("exit")) {
                                isStopped = true;
                            } else {
                                Response response = connector.sendRequestWrapper(requestWrapper);
                                answerHandler.readAnswer(response, requestWrapper);
                            }
                        }
                        connector.close();
                    } catch (SocketException e) {
                        System.out.println("Error: Server failed to proses the request.");
                        //e.printStackTrace();
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: The input stream was lost. EOF.");
                        //e.printStackTrace();
                        break;
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: Unable to find script file.");
                        //e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error: Deserialization failed. Try again.");
                        //e.printStackTrace();
                    } catch (IOException e) {
                        System.out.println("Error: IO exception. Connection failed. Try again.");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
