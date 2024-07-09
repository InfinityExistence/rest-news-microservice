package com.cfuv.rest_news.sevice;

import com.cfuv.rest_news.dao.NewsRepository;
import com.cfuv.rest_news.entity.News;
import com.cfuv.rest_news.entity.pages.PageCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class NewsServiceImpl implements NewsService {
    private final int PAGE_SIZE = 20;
    @Autowired
    private NewsRepository newsRepository;

    public List<News> getLatestNews() {

        return newsRepository.getLatestNews();

    }

    @Override
    public PageCustom<News> findNewsByTitleOrArticle(int p, String text) {
        Pageable pageable = PageRequest.of(p, PAGE_SIZE)
                .withSort(Sort.by("timestamp").descending());
        Page<News> news = newsRepository.findNewsByTitleOrArticle(text.toUpperCase(), pageable);
        return new PageCustom<>(news);
    }

    @Override
    public PageCustom<News> getAllNews(int p) {
        Pageable pageable = PageRequest.of(p, PAGE_SIZE)
                .withSort(Sort.by("timestamp").descending());
        Page<News> news = newsRepository
                .findAll(pageable);


        return new PageCustom<>(news);
    }

    @Override
    public void addNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public News getNews(long id) {
        News news = null;
        Optional<News> optional = newsRepository.findById(id);
        if (optional.isPresent()) {
            news = optional.get();
        }
        return news;
    }

    @Override
    public void deleteNews(long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void updateNews(News news) {
        newsRepository.save(news);
    }
}
