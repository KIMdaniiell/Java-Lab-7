package connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Connection implements AutoCloseable {
    /*
     * This class accepts connection requests from client and provides connected SocketChannels.
     */

    private final int port;
    private final ServerSocketChannel serverSocketChannel;

    public Connection(int port) throws IllegalArgumentException, IOException {
        this.port = port;
        serverSocketChannel = openServerSocketChannel();
    }

    private ServerSocketChannel openServerSocketChannel() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        serverSocketChannel.bind(inetSocketAddress);
        return serverSocketChannel;
    }

    public SocketChannel getSocketChannel() throws IOException {
        SocketChannel socketChannel;
        do {
            socketChannel = serverSocketChannel.accept();
        } while (socketChannel == null);
        socketChannel.configureBlocking(false);
        return socketChannel;
    }

    @Override
    public void close() throws IOException {
        this.serverSocketChannel.close();
    }
}
