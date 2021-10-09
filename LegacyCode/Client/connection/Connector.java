package connection;

import format.RequestWrapper;
import format.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Connector implements AutoCloseable{
    /*
     * This class manages sockets. There are
     * two methods, on which data exchange with the server
     * is based: sendRequestWrapper, receiveAnswer.
     */

    private Socket socket;
    private final int port;

    public Connector(int port) {
        this.port = port;
    }

    private Socket connect(int port) throws IOException {
        Socket socket = new Socket();
        //socket.setSoTimeout(15000);
        InetAddress serverAddress = InetAddress.getLocalHost();
        InetSocketAddress serverSocketAddress = new InetSocketAddress(serverAddress, port);
        socket.connect(serverSocketAddress);
        return socket;
    }


    public Response sendRequestWrapper(RequestWrapper requestWrapper) throws ClassNotFoundException, IOException {
        this.socket = connect(port);
        OutputStream outputStream = socket.getOutputStream();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOutputStream);
        objOut.writeObject(requestWrapper);
        objOut.flush();

        outputStream.write(ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array());
        //добавил информацию о размере пакета

        byteArrayOutputStream.writeTo(outputStream);
        byteArrayOutputStream.flush();
        outputStream.flush();

        return receiveAnswer();
    }

    public Response receiveAnswer() throws ClassNotFoundException, IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return (Response) objectInputStream.readObject();
    }

    public void close () throws IOException {
        if (socket != null){
            socket.close();
        }
    }
}
