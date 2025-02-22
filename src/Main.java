import messageHandle.MessageHandler;
import messageHandle.ChatHandler;
import messageRecieve.WebSocketReceiver;
import java.net.InetSocketAddress;

public class Main {
    public static void runServer() {
//        MessageHandler handler = new MessageHandler(
//            new ChatHandler() // ... add more handlers here
//        );
        MessageHandler handler = new ChatHandler();
        WebSocketReceiver receiver = new WebSocketReceiver(new InetSocketAddress("localhost", 8080));
        WebServer server = new WebServer(handler, receiver);

        server.run_forever();
    }

    public static void testWebsocket() {
        WebSocketReceiver receiver = new WebSocketReceiver(new InetSocketAddress("localhost", 8080));
        receiver.run();
    }

    public static void main(String[] args) {
        runServer();
    }
}

