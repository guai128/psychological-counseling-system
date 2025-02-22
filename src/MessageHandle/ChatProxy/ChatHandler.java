package MessageHandle.ChatProxy;

import MessageHandle.MessageHandler;
import MessageHandle.Request;
import MessageHandle.Response;

import java.util.Map;

public class ChatHandler extends MessageHandler {
    private ChatProxy chat_delegation = new SelfImplementIM();

    public ChatHandler() {
        super();
        requestMap.put("chat", new Request("chat", new String[]{"message"}, this::chat));
    }

    public void setChatDelegation(ChatProxy delegation) {
        chat_delegation = delegation;
    }

    public String transmitMessage(Map<String, ?> request_json) {
        chat_delegation.transmitMessage(request_json);
        return new Response<String>(200, "success", "transmit message").toJsonString();
    }

    public String acknowledgeMessage(Map<String, ?> request_json) {
        chat_delegation.acknowledgeMessage(request_json);
        return new Response<String>(200, "success", "acknowledge message").toJsonString();
    }

    public String pullUnReceivedMessage(Map<String, ?> request_json) {
        chat_delegation.pullUnReceivedMessage(request_json);
        return new Response<String>(200, "success", "pull unreceived message").toJsonString();
    }

    public String requestMessageID(Map<String, ?> request_json) {
        chat_delegation.requestMessageID(request_json);
        return new Response<String>(200, "success", "request message ID").toJsonString();
    }

    public String chat(Map<String, ?> request_json) {
        String message = (String) request_json.get("message");
        return new Response<String>(200, "success", message).toJsonString();
    }
}
