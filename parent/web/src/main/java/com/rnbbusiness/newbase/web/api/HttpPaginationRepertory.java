package com.rnbbusiness.newbase.web.api;

import lombok.Data;

import java.util.List;

@Data
public class HttpPaginationRepertory<T extends Object> {

    private int currentIndex = 0;
    private int totalCount = 0;
    private int totalPage;
    private List<T> pageItems;

    public HttpPaginationRepertory() {}

    public HttpPaginationRepertory(int totalCount, List<T> pageItems, HttpPaginationCondition<? extends Object> paginationCondition){
        this.totalCount = totalCount;
        this.pageItems = pageItems;
        currentIndex = ((paginationCondition.getCurrentPage() - 1)*paginationCondition.getPageSize())+1;
        totalPage = (totalCount == 0) ? 1 : (((this.totalCount - 1)/ paginationCondition.getPageSize()) + 1);
    }

}
