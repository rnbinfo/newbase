package com.rnbbusiness.newbase.web.api;

import lombok.Data;

@Data
public class HttpResponse<T> {

    private HttpResponseHeader header;
    private T body;

}
