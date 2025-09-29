package com.mufid.model;

import com.mufid.base.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> extends ApiResponse<List<T>> {

    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int size;
    private boolean first;
    private boolean last;

    public PageResponse() {
        super();
    }

    public PageResponse(Page<T> page, String message) {
        super(true, message, page.getContent());
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.size = page.getSize();
        this.first = page.isFirst();
        this.last = page.isLast();
    }

    public static <T> PageResponse<T> of(Page<T> page, String message) {
        return new PageResponse<>(page, message);
    }

    // Getters and Setters
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
