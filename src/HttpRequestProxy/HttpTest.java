package HttpRequestProxy;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpTest {

    public static void main(String[] args) throws Exception {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("https://www.example.com");
            httpget.addHeader("Authorization", "Bearer sk");
            CloseableHttpResponse response = httpclient.execute(httpget);
            System.out.println(response.getStatusLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}