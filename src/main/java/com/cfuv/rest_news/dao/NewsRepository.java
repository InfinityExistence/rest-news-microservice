package com.cfuv.rest_news.dao;


import com.cfuv.rest_news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    /**
     * @return List<News> возвращает лист из 3-х последних новостей
     */
    List<News> findTop3ByOrderByTimestampDesc();

    /**
     * @param title текст для поиска в title
     * @param article текст для поиска в article
     * @param pageable Объект страничного просмотра
     * @return page страница с информацией об элементах и кол-ве страниц
     */
    Page<News> findNewsByTitleContainingOrArticleContainingAllIgnoreCase(String title,String article, Pageable pageable);


}
