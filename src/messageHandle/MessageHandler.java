package messageHandle;

import org.json.JSONObject;
import java.util.HashMap;

public class MessageHandler {
    protected HashMap<String, Request> requestMap;

    public MessageHandler() {
        requestMap = HashMap.newHashMap(2);
    }

    public String handle(String message) {
        // convert message to json
        JSONObject json = null;
        try {
            json = new JSONObject(message);
        } catch (Exception e) {
            return new Response<String>(-1, "Invalid JSON", "null").toJsonString();
        }

        // check if request type is valid
        String request_type = json.getString("type");
        if (!requestMap.containsKey(request_type)) {
            return new Response<String>(-1, "Invalid request type", "null").toJsonString();
        }

        // check if the required params are present
        Request request = requestMap.get("type");
        String[] requiredParams = request.getRequire_params();
        for (String param : requiredParams) {
            if (!json.has(param)) {
                return new Response<String>(-1, "Missing required parameter: " + param, "null").toJsonString();
            }
        }

        // handle request
        return request.handle(json.toMap());
    }
}
