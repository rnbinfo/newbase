package com.rnb.newbase.controller.api;


public class HttpResponse<T> {
    private HttpResponseHeader header;
    private T body;

    public HttpResponseHeader getHeader() {
        return header;
    }

    public void setHeader(HttpResponseHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
