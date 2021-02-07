package com.rnb.newbase.controller.api;

import com.github.pagehelper.Page;

import java.util.List;

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

    public HttpPaginationRepertory(List<T> queryPagedResult, HttpPaginationRepertory<? extends Object> result){
        this.totalCount = result.getTotalCount();
        this.pageItems = queryPagedResult;
        currentIndex = result.getCurrentIndex();
        totalPage = result.getTotalPage();
    }

    public HttpPaginationRepertory(List<T> queryPagedResult, Page pageObject){
        this.totalCount = pageObject.getTotal();
        this.pageItems = queryPagedResult;
        currentIndex = pageObject.getPageNum();
        totalPage = pageObject.getPages();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }
}
