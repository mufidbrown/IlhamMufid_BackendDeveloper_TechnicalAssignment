package com.mufid.model;

import java.util.List;
import java.util.Map;

public class SearchModel {
    private String query;
    private List<String> searchFields;
    private String searchType; // EXACT, CONTAINS, STARTS_WITH, ENDS_WITH, FUZZY
    private Boolean caseSensitive;
    private Integer minLength;
    private Integer maxResults;
    private List<String> excludeFields;
    private Map<String, Object> filters;
    private String highlightTag; // for highlighting search results
    private Boolean includeInactive;

    public SearchModel() {
        this.searchType = "CONTAINS";
        this.caseSensitive = false;
        this.minLength = 2;
        this.maxResults = 50;
        this.includeInactive = false;
    }

    public SearchModel(String query) {
        this();
        this.query = query;
    }

    public SearchModel(String query, List<String> searchFields) {
        this(query);
        this.searchFields = searchFields;
    }

    // Builder pattern methods
    public SearchModel query(String query) {
        this.query = query;
        return this;
    }

    public SearchModel fields(List<String> searchFields) {
        this.searchFields = searchFields;
        return this;
    }

    public SearchModel type(String searchType) {
        this.searchType = searchType;
        return this;
    }

    public SearchModel caseSensitive(Boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        return this;
    }

    public SearchModel maxResults(Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public SearchModel withFilters(Map<String, Object> filters) {
        this.filters = filters;
        return this;
    }

    public SearchModel highlight(String highlightTag) {
        this.highlightTag = highlightTag;
        return this;
    }

    public SearchModel includeInactive(Boolean includeInactive) {
        this.includeInactive = includeInactive;
        return this;
    }

    // Utility methods
    public boolean isValidQuery() {
        return query != null &&
                !query.trim().isEmpty() &&
                query.trim().length() >= (minLength != null ? minLength : 2);
    }

    public String getProcessedQuery() {
        if (query == null) return "";

        String processed = query.trim();
        if (!caseSensitive) {
            processed = processed.toLowerCase();
        }

        return processed;
    }

    public boolean isFuzzySearch() {
        return "FUZZY".equalsIgnoreCase(searchType);
    }

    public boolean isExactSearch() {
        return "EXACT".equalsIgnoreCase(searchType);
    }

    // Getters and Setters
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public List<String> getSearchFields() { return searchFields; }
    public void setSearchFields(List<String> searchFields) { this.searchFields = searchFields; }

    public String getSearchType() { return searchType; }
    public void setSearchType(String searchType) { this.searchType = searchType; }

    public Boolean getCaseSensitive() { return caseSensitive; }
    public void setCaseSensitive(Boolean caseSensitive) { this.caseSensitive = caseSensitive; }

    public Integer getMinLength() { return minLength; }
    public void setMinLength(Integer minLength) { this.minLength = minLength; }

    public Integer getMaxResults() { return maxResults; }
    public void setMaxResults(Integer maxResults) { this.maxResults = maxResults; }

    public List<String> getExcludeFields() { return excludeFields; }
    public void setExcludeFields(List<String> excludeFields) { this.excludeFields = excludeFields; }

    public Map<String, Object> getFilters() { return filters; }
    public void setFilters(Map<String, Object> filters) { this.filters = filters; }

    public String getHighlightTag() { return highlightTag; }
    public void setHighlightTag(String highlightTag) { this.highlightTag = highlightTag; }

    public Boolean getIncludeInactive() { return includeInactive; }
    public void setIncludeInactive(Boolean includeInactive) { this.includeInactive = includeInactive; }
}
