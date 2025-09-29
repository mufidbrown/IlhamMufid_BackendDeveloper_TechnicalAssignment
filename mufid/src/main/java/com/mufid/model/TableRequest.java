package com.mufid.model;

import com.mufid.shared.constant.GlobalConstant;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Map;

public class TableRequest {

    @Min(0)
    private int page = 0;

    @Min(1)
    @Max(GlobalConstant.MAX_PAGE_SIZE)
    private int size = GlobalConstant.DEFAULT_PAGE_SIZE;

    private String sortBy = GlobalConstant.DEFAULT_SORT_FIELD;
    private String sortDirection = GlobalConstant.DEFAULT_SORT_DIRECTION;

    private Map<String, Object> filters;
    private String searchKeyword;

    public TableRequest() {}

    public TableRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public TableRequest(int page, int size, String sortBy, String sortDirection) {
        this(page, size);
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    // Getters and Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
