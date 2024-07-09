package com.cfuv.rest_news.dao;


import com.cfuv.rest_news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    /**
     * @return List<News> возвращает лист из 3х последних новостей
     */
    @Query("from News n1 order by n1.timestamp desc limit 3")
    List<News> getLatestNews();

    /**
     * @param text     Текст который содержится в заголовке или основном блоке новостей ((.toUpper()))
     * @param pageable Объект страничного просмотра
     * @return page страница с информацией об элементах и кол-ве страниц
     */
    @Query(value = "from News " +
            "n1 where upper(n1.title) like %:text% " +
            "or upper(n1.article) like %:text%",
            countQuery = "select count(n1) from News " +
                    "n1 where upper(n1.title) like %:text% " +
                    "or upper(n1.article) like %:text%")
    Page<News> findNewsByTitleOrArticle(@Param("text") String text, Pageable pageable);


}
