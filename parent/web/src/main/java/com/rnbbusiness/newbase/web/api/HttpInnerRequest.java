package com.rnbbusiness.newbase.web.api;

import lombok.Data;


@Data
public class HttpInnerRequest<T> implements HttpRequest {

    private HttpInnerRequestHeader header;
    private T body;

}
