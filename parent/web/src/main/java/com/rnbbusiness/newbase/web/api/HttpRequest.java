package com.rnbbusiness.newbase.web.api;

import lombok.Data;


@Data
public class HttpRequest<T> {

    private HttpRequestHeader header;
    private T body;

}
