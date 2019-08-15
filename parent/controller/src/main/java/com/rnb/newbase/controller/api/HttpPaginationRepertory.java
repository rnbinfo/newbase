package com.rnb.newbase.controller.api;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class HttpPaginationRepertory<T extends Object> {

    private int currentIndex = 0;
    private long totalCount = 0;
    private int totalPage;
    private List<T> pageItems;

    public HttpPaginationRepertory() {}

    public HttpPaginationRepertory(List<T> queryPagedResult){
        Page pageObject = (Page) queryPagedResult;

        this.totalCount = pageObject.getTotal();
        this.pageItems = queryPagedResult;
        currentIndex = pageObject.getPageNum();
        totalPage = pageObject.getPages();
    }

}
