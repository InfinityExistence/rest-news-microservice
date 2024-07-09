package com.cfuv.rest_news.entity.pages;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageCustom<T> {
    private final List<T> content;
    private final int currentPage;
    private final int totalPages;
    private final int totalElements;

    public PageCustom(Page<T> page) {
        this.content = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getNumberOfElements();
    }
}
