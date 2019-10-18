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
    public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";

    @Deprecated
    public static String doJsonPost(String urlPath, String Json) {
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("accept", "application/json");
            // 往服务器里面发送数据
            byte[] writebytes = Json.getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
            OutputStream outwritestream = conn.getOutputStream();
            outwritestream.write(Json.getBytes());
            outwritestream.flush();
            outwritestream.close();
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Deprecated
    public static String getConnectionForPostAsXml(String url, String xmlData) throws IOException {
        // 创建URL对象
        URL myURL = new URL(url);
        // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
        httpsConn.setConnectTimeout(30000);
        httpsConn.setRequestMethod("POST");
        httpsConn.setDoOutput(true);
        // 取得该连接的输入流，以读取响应内容
        httpsConn.getOutputStream().write(xmlData.getBytes());// 输入参数
        InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(insr);
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        String result = buffer.toString();
        return result;
    }

    @Deprecated
    public static Connection getConnectionForGetNoCookies(String url, Map<String, String>... datas) {
        Connection connection = Jsoup.connect(url).userAgent(USER_AGENT).ignoreContentType(true).timeout(5000);
        if (datas != null && datas.length > 0 && !datas[0].isEmpty()) {
            connection.data(datas[0]);
        }
        return connection;
    }

    @Deprecated
    public static Connection getConnectionForGet(String url, Map<String, String>... datas) {
        return getConnectionForGetNoCookies(url, datas);
    }

    @Deprecated
    public static String getContentForGet(String url, int timeout) {
        try {
            Document objectDoc;
            try {
                Connection connection = getConnectionForGetNoCookies(url).timeout(timeout);
                objectDoc = connection.get();
            } catch (SocketTimeoutException e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    // ignore
                }
                Connection connection = getConnectionForGetNoCookies(url).timeout(timeout);
                objectDoc = connection.get();
            }
            return objectDoc.body().text();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
