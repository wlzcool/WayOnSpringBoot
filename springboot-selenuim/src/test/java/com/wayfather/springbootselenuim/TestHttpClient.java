package com.wayfather.springbootselenuim;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

/**
 * @author IBM
 * @className TestHttpClient
 * @description TODO
 * @date 2019/10/30 10:42
 **/
public class TestHttpClient {
    @Test
    public void main() {
        // 登陆 Url
        String loginUrl = "https://www.qichacha.com/tax";
        // 需获取Cookies后才能访问的 Url
        String dataUrl = "https://www.qichacha.com/tax_search?key=91130000677356885K";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 获取Cookies
        HttpGet httpget = new HttpGet(loginUrl);
        httpget.setHeader("Referer", "http://www.google.com");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            //System.out.println(response);
            Header[] headers = response.getHeaders("Set-Cookie");
            if(headers.length>0){
                for (Header header :headers){
                    System.out.println(header);
                }
            }
            // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
            HttpGet httpget1 = new HttpGet(dataUrl);
            httpget1.setHeader("Referer", "https://www.qichacha.com/tax");
            httpget1.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");

            CloseableHttpResponse httpResponse = httpclient.execute(httpget1);
            String result = "";
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                System.out.println("--------------------------------------");
                System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                System.out.println("--------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
