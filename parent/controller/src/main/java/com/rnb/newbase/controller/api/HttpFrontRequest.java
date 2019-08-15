package com.rnb.newbase.controller.api;

import lombok.Data;

import javax.validation.Valid;

@Data
public class HttpFrontRequest<T> implements HttpRequest {
    @Valid
    private HttpFrontRequestHeader header;
    @Valid
    private T body;
}
