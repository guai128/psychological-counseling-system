package MessageHandle.ChatProxy;

import HttpRequestProxy.HttpProxy;
import MessageHandle.Response;
import org.json.JSONObject;
import java.util.Map;

public class TencentIM implements ChatProxy {
    final String DOMAIN = "console.tim.qq.com";
    final String SDK_APPID = "1600073452";
    final String ADMIN_IDENTIFIER = "administrator";
    final String ADMIN_USERSIG = "521c7befeecab266012da8e1c6a6c2b2c8cb0d6067b1e75d86abb77be3cfb32b";
    final String RANDOM = "99999999";
    final String CONTENT_TYPE = "json";

    // sample url : https://xxxxxx/v4/openim/sendmsg?sdkappid=88888888&identifier=admin&usersig=xxx&random=99999999&contenttype=json
    public String transmitMessage(Map<String, ?> request_json) {
        final String baseUrl = String.format("https://%s/v4/openim/sendmsg", DOMAIN);
        final String url = String.format("%s?sdkappid=%s&identifier=%s&usersig=%s&random=%s&contenttype=%s",
                baseUrl, SDK_APPID, ADMIN_IDENTIFIER, ADMIN_USERSIG, RANDOM, CONTENT_TYPE);

        // make posted data
        JSONObject json = new JSONObject();
        String res = HttpProxy.sendPost(url, json.toMap());

        // check response and store message into database

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
