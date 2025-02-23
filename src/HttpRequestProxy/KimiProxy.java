package HttpRequestProxy;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map;

public class KimiProxy {
    private static final String API_KEY = "sk-kpZmG3a0pWQIkwc69L5sdgpCXgU2rtjcivkTVu3EO7uasOsI";
    private static final String CALL_WORD = "你是一名心理咨询师，你将引导用户并解答用户的问题，默认用户使用中文。";
    private static final String ERROR_WORD = "服务器繁忙，请稍后再试。";
    private static final Map<String, Vector<Map<String, String>>> session_pool = new HashMap<>();

    private static String post_request(Vector<Map<String, String>> messages) {
        Map<String, String> header = Map.of("Content-Type", "application/json",
                "Authorization", "Bearer " + API_KEY);
        Map<String, ?> body = Map.of("model", "moonshot-v1-8k",
                "messages", messages,"temperature", 0.3);

        return HttpProxy.sendPost("https://api.moonshot.cn/v1/chat/completions", header, body);
    }

    public static String register_session() {
        Vector<Map<String, String>> messages = new Vector<>(10);
        messages.add(Map.of("role", "system", "content", CALL_WORD));

        // get response from server and parse it to get returned messages
        String str_response = post_request(messages);
        Map<String, ?> map_response = new JSONObject(str_response).toMap();
        Vector<Map<String, ?>> choices = (Vector<Map<String, ?>>) map_response.get("choices");
        for (Map<String, ?> choice : choices) {
            messages.add((Map<String, String>)choice.get("message"));
        }

        // add message into session pool
        String session_id = map_response.get("id").toString();
        session_pool.put(session_id, messages);

        return session_id;
    }

    public static String chat(String session_id, String message) {
        Vector<Map<String, String>> messages = session_pool.get(session_id);
        messages.add(Map.of("role", "user", "content", message));

        String str_response = post_request(messages);
        Map<String, ?> map_response = new JSONObject(str_response).toMap();
        Vector<Map<String, ?>> choices = (Vector<Map<String, ?>>) map_response.get("choices");
        if (choices == null) {
            messages.removeLast();
            return ERROR_WORD;
        }

        Map<String, String> last_message = (Map<String, String>)choices.getLast().get("message");
        return last_message.get("content");
    }

    public static void test() {
        String session_id = register_session();
        while (true) {
            System.out.print("You: ");
            String message = System.console().readLine();
            if (message.equals("exit")) {
                break;
            }
            String response = chat(session_id, message);
            System.out.println("Kimi: " + response);
        }
    }

    public static void main(String[] args) {
        test();
    }
}
