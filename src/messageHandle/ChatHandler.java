package messageHandle;

import java.util.Map;

public class ChatHandler extends MessageHandler {
    public ChatHandler() {
        super();
        requestMap.put("chat", new Request("chat", new String[]{"message"}, this::chat));
    }

    public String chat(Map<String, ?> request_json) {
        String message = (String) request_json.get("message");
        return new Response<String>(200, "success", message).toJsonString();
    }
}
