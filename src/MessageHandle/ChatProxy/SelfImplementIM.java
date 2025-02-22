package MessageHandle.ChatProxy;

import MessageHandle.Response;
import java.util.Map;

public class SelfImplementIM implements ChatProxy {
    public String transmitMessage(Map<String, ?> request_json) {
        return new Response<String>(200, "success", "transmit message").toJsonString();
    }

    public String acknowledgeMessage(Map<String, ?> request_json) {
        return new Response<String>(200, "success", "acknowledge message").toJsonString();
    }

    public String pullUnReceivedMessage(Map<String, ?> request_json) {
        return new Response<String>(200, "success", "pull unreceived message").toJsonString();
    }

    public String requestMessageID(Map<String, ?> request_json) {
        return new Response<String>(200, "success", "request message ID").toJsonString();
    }
}
