package com.rnb.newbase.controller.api;

import com.rnb.newbase.api.AbstractGatewayApi;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class HttpPaginationGatewayCondition<T extends Object> extends AbstractGatewayApi {
    @NotNull
    private Integer pageSize;
    @NotNull
    private Integer currentPage;
    @Valid
    private T condition;

    public HttpPaginationGatewayCondition() {

    }

    public HttpPaginationGatewayCondition(int pageSize, int currentPage, T condition){
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.condition = condition;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}
