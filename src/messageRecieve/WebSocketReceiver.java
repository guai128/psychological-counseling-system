package messageRecieve;
import messageHandle.MessageHandler;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;

public class WebSocketReceiver extends WebSocketServer {
    MessageHandler massageHandler = null;

    public WebSocketReceiver(InetSocketAddress address) {
        super(address);
    }

    public void setHandler(MessageHandler handler) {
        massageHandler = handler;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("New connection: " + webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("Closed connection: " + webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        if (massageHandler == null) {
            throw new RuntimeException("Message handler not set");
        }

        String response = massageHandler.handle(s);
        webSocket.send(response);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("Server started");
    }
}
