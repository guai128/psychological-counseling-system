package messageHandle;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MessageHandler {
    protected HashMap<String, Request> requestMap;

    public MessageHandler(MessageHandler... children) {
        requestMap = HashMap.newHashMap(10);
        requestMap.put("heart_beat", new Request("heart_beat", new String[]{}, this::heartBeat));

        // add children's requestMap to this requestMap
        for (MessageHandler child : children) {
            requestMap.putAll(child.requestMap);
        }
    }

    public MessageHandler() {
        requestMap = HashMap.newHashMap(10);
        requestMap.put("heart_beat", new Request("heart_beat", new String[]{}, this::heartBeat));
    }

    public String handle(String message) {
        // convert message to json
        JSONObject json;
        try {
            json = new JSONObject(message);
        } catch (Exception e) {
            return new Response<>(-1, "Invalid JSON", "null").toJsonString();
        }

        // check if request type is valid
        String request_type = json.getString("type");
        if (!requestMap.containsKey(request_type)) {
            return new Response<>(-1, "Invalid request type", "null").toJsonString();
        }

        // check if the required params are present
        Request request = requestMap.get(request_type);
        String[] requiredParams = request.getRequireParams();
        for (String param : requiredParams) {
            if (!json.has(param)) {
                return new Response<>(-1, "Missing required parameter: " + param, "null").toJsonString();
            }
        }

        // handle request
        return request.handle(json.toMap());
    }

    public String heartBeat(Map<String, ?> request_json) {
        return new Response<>(200, "success", "活着").toJsonString();
    }
}
