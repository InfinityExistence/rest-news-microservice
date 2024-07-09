package com.cfuv.rest_news.sevice;


import com.cfuv.rest_news.entity.News;
import com.cfuv.rest_news.entity.pages.PageCustom;

import java.util.List;

public interface NewsService {
    PageCustom<News> getAllNews(int p);

    void addNews(News news);

    News getNews(long id);

    void deleteNews(long id);

    void updateNews(News news);

    List<News> getLatestNews();

    PageCustom<News> findNewsByTitleOrArticle(int p, String title);

}
