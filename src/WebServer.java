import messageHandle.MessageHandler;
import messageRecieve.WebSocketReceiver;

public class WebServer {
    private final MessageHandler requestHandler;
    private final WebSocketReceiver messageReceiver;

    public WebServer(MessageHandler handler, WebSocketReceiver receiver) {
        this.requestHandler = handler;
        this.messageReceiver = receiver;
    }

    public void run_forever() {
        messageReceiver.setHandler(requestHandler);
        messageReceiver.run();
    }
}