package com.rnbbusiness.newbase.web.api;

import lombok.Data;

import javax.validation.Valid;


@Data
public class HttpOuterRequest<T> implements HttpRequest {

    private HttpOuterRequestHeader header;
    @Valid
    private T body;

}
