package com.oxlxs.hotelmanagementsysback.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    public PageResponse(List<T> content, int currentPage, int totalPages, int size, long totalElements) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.size = size;
        this.totalElements = totalElements;
    }

    private List<T> content;
    private int totalPages;
    private int currentPage;
    private int size;
    private Long totalElements;
}
