package com.wayfather.springbootwebclient.common;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HttpUtil
 * @Description TODO
 * @Author IBM
 * @Date 2019/10/8 10:27
 **/
@Component
public class HttpUtil {
    private static String serverChanUrl;
    @Value("${web.ServerChan.url}")
    public void setServerChanUrl(String url) {
        serverChanUrl = url;
    }
    /**
     * 设置post请求
     * @param text 标题     *
     * @return String
     */
    public static String sendServerChanInfo(String text) {
        return sendServerChanInfo( text, "");
    }
    /**
     * 设置post请求
     * @param text 标题
     * @param desp 描述
     * @return String
     */
    public static String sendServerChanInfo(String text, String desp) {

        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("text", text));
        data.add(new BasicNameValuePair("desp", desp));

        return sendPost(serverChanUrl, data, 30);
    }

    /**
     * 设置post请求
     *
     * @param url  请求地址
     * @param data 请求数据
     * @return String
     */
    public static String sendPost(String url, List<NameValuePair> data) {
        return sendPost(url, data, 30);
    }

    /**
     * 设置post请求
     *
     * @param url     请求地址
     * @param data    请求数据
     * @param timeOut 超时时间
     * @return String
     */
    public static String sendPost(String url, List<NameValuePair> data, int timeOut) {
        String result = null;
        if (timeOut <= 0) {
            timeOut = 30;
        }
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeOut * 1000)
                .setConnectionRequestTimeout(timeOut * 1000)
                .setSocketTimeout(timeOut * 1000).build();
        CloseableHttpClient client =
                HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(data, "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = client.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
