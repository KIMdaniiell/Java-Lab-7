package recieve;

import format.RequestWrapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestReciever {
    /*
     * This class is responsible for processing requests from the client.
     * Main data is encapsulated in RequestWrapper class.
     */

    private final SocketChannel socketChannel;


    public RequestReciever(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public RequestWrapper getRequestWrapper() throws ClassNotFoundException, IOException {
        int bufferSize = getPacketSize();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
        while (byteBuffer.hasRemaining()) {
            socketChannel.read(byteBuffer);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        RequestWrapper requestWrapper = (RequestWrapper) objectInputStream.readObject();

        objectInputStream.close();
        byteArrayInputStream.close();

        return requestWrapper;

    }

    private int getPacketSize() throws IOException {
        ByteBuffer lengthBuffer = ByteBuffer.allocate(4);
        while (lengthBuffer.hasRemaining()) {
            socketChannel.read(lengthBuffer);
        }
        lengthBuffer.flip();
        return lengthBuffer.getInt();
    }
}
