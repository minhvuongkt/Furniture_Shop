package com.nhom13.models;

import java.util.List;

public class SearchResult<T> {

    String searchValue;
    int pageSize;
    int page;
    int rowCount;
    List<T> data;
    int PageCount;

    public int getPageCount() {
        if (pageSize == 0) {
            return 1;
        }

        int n = rowCount / pageSize;
        if (rowCount % pageSize > 0) {
            n += 1;
        }
        return n;
    }

    public SearchResult(String searchValue, int pageSize, int page, int rowCount, List<T> data) {
        this.searchValue = searchValue;
        this.pageSize = pageSize;
        this.page = page;
        this.rowCount = rowCount;
        this.data = data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public int getPage() {
        return page;
    }

    public int getRowCount() {
        return rowCount;
    }

    public List<T> getData() {
        return data;
    }
}
