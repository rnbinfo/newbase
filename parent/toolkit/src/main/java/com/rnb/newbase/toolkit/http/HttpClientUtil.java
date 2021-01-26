package com.rnb.newbase.toolkit.http;

import com.rnb.newbase.toolkit.util.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
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

    public static String doJsonPost(String url, Map<String, String> headers,  String jsonData, int timeout) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String result = "";
        try {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
            cm.setMaxTotal(MAX_CONN_TOTAL);
            client = HttpClients.custom().setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);
            StringEntity requestEntity = new StringEntity(jsonData, Charset.forName(DEFAULT_CHARSET));
            requestEntity.setContentType("application/json");
            httpPost.setEntity(requestEntity);
            if (headers != null) {
                for (String headerKey : headers.keySet()) {
                    httpPost.addHeader(headerKey, headers.get(headerKey));
                }
            }
            logger.debug("Do json post to url[{}], jsonData[{}]", url, jsonData);
            result = sendPost(client, httpPost, timeout < DEFAULT_TIMEOUT ? DEFAULT_TIMEOUT : timeout);
            logger.debug("Do json post response[{}]", result);
        } finally {
            if (response != null)
                response.close();
            if (client != null)
                client.close();
        }
        return result;
    }

    public static String doJsonPost(String url, String jsonData, int timeout) throws Exception {
        return doJsonPost(url, null,  jsonData, timeout);
    }

    public static String doJsonPatch(String url, Map<String, String> headers,  String jsonData, int timeout) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String result = "";
        try {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
            cm.setMaxTotal(MAX_CONN_TOTAL);
            client = HttpClients.custom().setConnectionManager(cm).build();
            HttpPatch httpPatch = new HttpPatch(url);
            StringEntity requestEntity = new StringEntity(jsonData, Charset.forName(DEFAULT_CHARSET));
            requestEntity.setContentType("application/json");
            httpPatch.setEntity(requestEntity);
            if (headers != null) {
                for (String headerKey : headers.keySet()) {
                    httpPatch.addHeader(headerKey, headers.get(headerKey));
                }
            }
            logger.debug("Do json patch to url[{}], jsonData[{}]", url, jsonData);
            result = setPatch(client, httpPatch, timeout < DEFAULT_TIMEOUT ? DEFAULT_TIMEOUT : timeout);
            logger.debug("Do json patch response[{}]", result);
        } finally {
            if (response != null)
                response.close();
            if (client != null)
                client.close();
        }
        return result;
    }

    public static String doJsonPatch(String url, String jsonData, int timeout) throws Exception {
        return doJsonPatch(url, null,  jsonData, timeout);
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
            logger.error("Http response status: " + statusCode);
            logger.error("Http response: " + EntityUtils.toString(response.getEntity()));
            throw new RuntimeException("Http response status:" + statusCode);
        }
        return result;
    }

    private static String setPatch(CloseableHttpClient client, HttpPatch httpPatch, int timeout) throws IOException {
        String result = null;
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout).setConnectTimeout(timeout)
                .setSocketTimeout(timeout).build();
        httpPatch.setConfig(requestConfig);
        CloseableHttpResponse response = client.execute(httpPatch);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
        } else {
            logger.error("Http response status: " + statusCode);
            logger.error("Http response: " + EntityUtils.toString(response.getEntity()));
            throw new RuntimeException("Http response status:" + statusCode);
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
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
                logger.error("Http response status: " + statusCode);
                logger.error("Http response: " + EntityUtils.toString(response.getEntity()));
                throw new RuntimeException("Http response status:" + statusCode);
            }
        } finally {
            response.close();
            httpclient.close();
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求（通过代理方式）
     *
     * @param url   发送请求的URL
     * @param agent 请求的agent头
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGetWithProxy(String url, String agent, String proxyHost, int proxyPort) throws IOException {
        String result = null;
        if (StringUtil.isBlank(proxyHost)) {
            result = sendGet(url, agent);
        } else {
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
                    logger.error("Http response status: " + statusCode);
                    logger.error("Http response: " + EntityUtils.toString(response.getEntity()));
                    throw new RuntimeException("Http response status:" + statusCode);
                }
            } finally {
                response.close();
                httpclient.close();
            }
        }
        return result;
    }
}
