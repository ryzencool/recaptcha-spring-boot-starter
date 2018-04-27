package com.zmy.recaptchaspringbootstarter.autoconfig.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    /**
     * 默认的等待数据读取的超时时长，毫秒
     */
    public static final int DEFAULT_READ_TIMEOUT = 5000;

    /**
     * 默认连接超时时间，毫秒
     */
    public static final int DEFAULT_CONNECT_TIMEOUT = 3000;

    /**
     * 默认字符编码, utf-8
     */
    public static final String UTF_8 = "UTF-8";
    /**
     * 发送请求
     * @param url
     * @param requestMap
     * @return
     */
    public static String post(String url, Map<String, String> requestMap) throws Exception {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .setSocketTimeout(DEFAULT_READ_TIMEOUT)
                .setConnectionRequestTimeout(DEFAULT_CONNECT_TIMEOUT).build();
        String returnMsg = "";

        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            // 先迭代HashMap
            for (Map.Entry<String, String> entry : requestMap.entrySet()) {
                String key = entry.getKey();
                nvps.add(new BasicNameValuePair(key, entry.getValue()));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, UTF_8));
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + UTF_8);
            httpPost.addHeader("Accept-Language", "zh-cn");

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                returnMsg = EntityUtils.toString(httpEntity, UTF_8); //应答消息默认使用与请求相同的字符编码
                EntityUtils.consume(httpEntity);
            }
            httpPost.abort();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // ignore
            }
        }
        return returnMsg;

    }
}
