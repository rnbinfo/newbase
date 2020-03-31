package com.rnb.newbase.toolkit.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static final int MAX_CONN_TOTAL = 200;
    private static final int MAX_CONN_PERROUTE = 20;
    private static final int DEFAULT_TIMEOUT = 30000;
    private static final String DEFAULT_CHARSET = "UTF-8";
    public static String WIN_CHROME_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
    public static String MAC_FIREFOX_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:74.0) Gecko/20100101 Firefox/74.0";
    public static String MAC_CHROME_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36";

    public static String doFormPost(String url, Map<String, String> formParamters, int timeout) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String result = null;
        try {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
            cm.setMaxTotal(MAX_CONN_TOTAL);
            client = HttpClients.custom().setConnectionManager(cm).build();
            List<BasicNameValuePair> params = new ArrayList<>();
            for (String key : formParamters.keySet()) {
                params.add(new BasicNameValuePair(key, formParamters.get(key)));
            }
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(params, Charset.forName(DEFAULT_CHARSET)));
            result = sendPost(client, httppost, timeout);
        } finally {
            if (response != null)
                response.close();
            if (client != null)
                client.close();
        }
        return result;
    }

    public static String doJsonPost(String url, String jsonData, int timeout) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String result = "";
        try {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
            cm.setMaxTotal(MAX_CONN_TOTAL);
            client = HttpClients.custom().setConnectionManager(cm).build();
            HttpPost httppost = new HttpPost(url);
            HttpEntity requestEntity = new StringEntity(jsonData, Charset.forName(DEFAULT_CHARSET));
            httppost.setEntity(requestEntity);
            result = sendPost(client, httppost, timeout);
        } finally {
            if (response != null)
                response.close();
            if (client != null)
                client.close();
        }
        return result;
    }

    private static String sendPost(CloseableHttpClient client, HttpPost httppost, int timeout) throws IOException {
        String result = null;
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout).setConnectTimeout(timeout)
                .setSocketTimeout(timeout).build();
        httppost.setConfig(requestConfig);
        CloseableHttpResponse response = client.execute(httppost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
        } else {
            throw new RuntimeException("Response status:" + statusCode);
        }
        return result;
    }
}
