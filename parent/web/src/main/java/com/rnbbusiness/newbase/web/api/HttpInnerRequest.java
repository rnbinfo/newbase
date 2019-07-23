package com.rnbbusiness.newbase.web.api;

import lombok.Data;

import javax.validation.Valid;


@Data
public class HttpInnerRequest<T> implements HttpRequest {

    private HttpInnerRequestHeader header;
    @Valid
    private T body;
}
