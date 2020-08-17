package com.rnb.newbase.controller.api;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class HttpPaginationCondition <T extends Object> {
    @NotNull
    private Integer pageSize;
    @NotNull
    private Integer currentPage;
    @Valid
    private T condition;

    public HttpPaginationCondition() {

    }

    public HttpPaginationCondition(int pageSize, int currentPage, T condition){
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.condition = condition;
    }

}
