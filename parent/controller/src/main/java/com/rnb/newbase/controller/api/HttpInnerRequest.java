package com.rnb.newbase.controller.api;

import javax.validation.Valid;


public class HttpInnerRequest<T> implements HttpRequest {
    @Valid
    private HttpInnerRequestHeader header;
    @Valid
    private T body;

    @Override
    public HttpInnerRequestHeader getHeader() {
        return header;
    }

    public void setHeader(HttpInnerRequestHeader header) {
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
        return "HttpInnerRequest{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
