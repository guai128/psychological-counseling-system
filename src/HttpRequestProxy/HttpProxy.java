package HttpRequestProxy;

import java.io.IOException;
import java.util.Map;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpProxy {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    private static String getResponse(HttpRequestBase request) {
        try {
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String sendGet(String url) {
        HttpGet httpget = new HttpGet(url);

        return getResponse(httpget);
    }

    public static String sendGet(String url, Map<String, String> header) {
        HttpGet httpGet = new HttpGet(url);
        for(Map.Entry<?, ?> entry : header.entrySet()) {
            httpGet.setHeader(entry.getKey().toString(),entry.getValue().toString());
        }

        return getResponse(httpGet);
    }

    public static String sendPost(String url, Map<String, ?> params) {
        JSONObject jsonObject = new JSONObject();
        for(Map.Entry<?, ?> entry:params.entrySet()){
            jsonObject.put(entry.getKey().toString(),entry.getValue().toString());
        }
        StringEntity entity = new StringEntity(jsonObject.toString(), Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);

        return getResponse(httppost);
    }

    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);

        return getResponse(httppost);
    }
}

