package com.rnb.newbase.controller.api;

import javax.validation.Valid;

public class HttpFrontRequest<T> implements HttpRequest {
    @Valid
    private HttpFrontRequestHeader header;
    @Valid
    private T body;

    @Override
    public HttpFrontRequestHeader getHeader() {
        return header;
    }

    public void setHeader(HttpFrontRequestHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpFrontRequest{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
