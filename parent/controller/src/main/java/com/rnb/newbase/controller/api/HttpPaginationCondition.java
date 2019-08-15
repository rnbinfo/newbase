package com.rnb.newbase.controller.api;

import lombok.Data;

@Data
public class HttpPaginationCondition <T extends Object> {

    private int pageSize;
    private int currentPage;
    private T condition;

    public HttpPaginationCondition() {

    }

    public HttpPaginationCondition(int pageSize, int currentPage, T condition){
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.condition = condition;
    }

}
