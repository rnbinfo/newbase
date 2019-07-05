package com.rnbbusiness.newbase.web.api;

import lombok.Data;


@Data
public class HttpOuterRequest<T> implements HttpRequest {

    private HttpOuterRequestHeader header;
    private T body;

}
