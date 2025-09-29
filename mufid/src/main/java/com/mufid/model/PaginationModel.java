package com.mufid.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PaginationModel {
    @Min(value = 1, message = "Page number must be greater than 0")
    private Integer page = 1;

    @Min(value = 1, message = "Page size must be greater than 0")
    @Max(value = 100, message = "Page size must not exceed 100")
    private Integer size = 10;

    private String sortBy = "id";
    private String sortDirection = "ASC"; // ASC or DESC

    public PaginationModel() {}

    public PaginationModel(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PaginationModel(Integer page, Integer size, String sortBy, String sortDirection) {
        this(page, size);
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    // Utility methods
    public Integer getOffset() {
        return (page - 1) * size;
    }

    public boolean isValidSortDirection() {
        return "ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection);
    }

    // Getters and Setters
    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }

    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }

    public String getSortDirection() { return sortDirection; }
    public void setSortDirection(String sortDirection) { this.sortDirection = sortDirection; }
}
