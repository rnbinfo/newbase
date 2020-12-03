package com.rnb.newbase.toolkit.http;

import com.rnb.newbase.toolkit.util.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final int MAX_CONN_TOTAL = 200;
    private static final int MAX_CONN_PERROUTE = 20;
    private static final int DEFAULT_TIMEOUT = 3000;
    private static final String DEFAULT_CHARSET = "UTF-8";
    public static String WIN_CHROME_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
    public static String MAC_FIREFOX_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:74.0) Gecko/20100101 Firefox/74.0";
    public static String MAC_CHROME_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36";

    public static String doFormUrlEncodedPost(String url, Map<String, String> formParameters, int timeout) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String result = null;
        try {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
            cm.setMaxTotal(MAX_CONN_TOTAL);
            client = HttpClients.custom().setConnectionManager(cm).build();
            List<BasicNameValuePair> params = new ArrayList<>();
            for (String key : formParameters.keySet()) {
                params.add(new BasicNameValuePair(key, formParameters.get(key)));
            }
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(params, Charset.forName(DEFAULT_CHARSET)));
            logger.debug("Do form urlencoded post to url[{}], parameters[{}]", url, formParameters);
            result = sendPost(client, httppost, timeout < DEFAULT_TIMEOUT ? DEFAULT_TIMEOUT : timeout);
            logger.debug("Do form urlencoded post response[{}]", result);
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
            StringEntity requestEntity = new StringEntity(jsonData, Charset.forName(DEFAULT_CHARSET));
            requestEntity.setContentType("application/json");
            httppost.setEntity(requestEntity);
            logger.debug("Do json post to url[{}], jsonData[{}]", url, jsonData);
            result = sendPost(client, httppost, timeout < DEFAULT_TIMEOUT ? DEFAULT_TIMEOUT : timeout);
            logger.debug("Do json post response[{}]", result);
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

    /**
     * 向指定URL发送GET方法的请求
     * 使用CloseableHttpClient方式
     *
     * @param url   发送请求的URL
     * @param agent 请求的agent头
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String agent) throws IOException {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建 HttpGet
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", StringUtil.isBlank(agent) ? MAC_CHROME_USER_AGENT : agent);
        // 执行get请求
        logger.debug("Do get to url[{}] by CloseableHttpClient", url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        logger.debug("Do get response[{}]", response.toString());
        try {
            // 获取响应结果
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
            } else {
                throw new RuntimeException("Response status:" + statusCode);
            }
        } finally {
            response.close();
            httpclient.close();
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求（通过代理方式）
     * 使用CloseableHttpClient方式
     *
     * @param url   发送请求的URL
     * @param agent 请求的agent头
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGetWithProxy(String url, String agent, String proxyHost, int proxyPort) throws IOException {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建 HttpGet
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", StringUtil.isBlank(agent) ? MAC_CHROME_USER_AGENT : agent);
        httpGet.setConfig(
                RequestConfig.custom()
                        .setProxy(new HttpHost(proxyHost, proxyPort, "HTTP"))
                        .build()
        );
        // 执行get请求
        logger.debug("Do get to url[{}] by CloseableHttpClient", url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        logger.debug("Do get response[{}]", response.toString());
        try {
            // 获取响应结果
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
            } else {
                throw new RuntimeException("Response status:" + statusCode);
            }
        } finally {
            response.close();
            httpclient.close();
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     * 使用HttpURLConnection方式
     *
     * @param url
     * @param agent
     * @return
     * @throws Exception
     */
    public static String doGet(String url, String agent) {
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            //默认值我GET
            connection.setRequestMethod("GET");
            //添加请求头
            connection.setRequestProperty("User-Agent", StringUtil.isBlank(agent) ? MAC_CHROME_USER_AGENT : agent);
            logger.debug("Do get to url[{}] by HttpURLConnection", url);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpStatus.SC_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                throw new RuntimeException("Response status:" + responseCode);
            }
        } catch (Exception e) {
            logger.error("Send get request exception!");
            logger.error("Exception => ", e);
        }
        return null;
    }
}
