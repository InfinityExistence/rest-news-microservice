package com.cfuv.rest_news.controller;

import com.cfuv.rest_news.entity.News;
import com.cfuv.rest_news.entity.pages.PageCustom;
import com.cfuv.rest_news.exceptions.NoSuchNewsException;
import com.cfuv.rest_news.sevice.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Новости")
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping("/news")
    @Operation(summary = "Все новости")
    public PageCustom<News> showAllNews(@Parameter(description = "Номер текущей страницы")
                                        @RequestParam(defaultValue = "0", required = false)
                                        int p) {
        return newsService.getAllNews(p);

    }

    @GetMapping("/news/search")
    @Operation(summary = "Поиск новостей")
    public PageCustom<News> findNews(@Parameter(description = "Номер текущей страницы")
                                     @RequestParam(defaultValue = "0", required = false) int p,
                                     @RequestParam() String text) {


        return newsService.findNewsByTitleOrArticle(p, text);

    }

    @GetMapping("/news/latest")
    @Operation(summary = "Показать последние 3 новости")
    public List<News> showLatestNews() {
        return newsService.getLatestNews();

    }

    @GetMapping(("/news/{id}"))
    @Operation(summary = "Показать конкретную новость")
    public News showNewsById(@Parameter(description = "id новости")
                             @PathVariable("id") long id) {
        News news = newsService.getNews(id);
        if (news == null) {
            throw new NoSuchNewsException("There is no news with id " + id + " in DataBase");
        }
        return news;
    }

    @PostMapping("/news")
    @Operation(summary = "Добавить новость",
    parameters = @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization",
            required = true,
            schema = @Schema(type = "string", defaultValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJyb2xlcyI6WyJlZGl0b3IiXX0.XakFspE3jgN4R-1q7kBYHmYMjaES63WuPS4pvE7Mxe8"),
            description = "Токен с правами редактирования"
    )
    )
    public News addNews(@Parameter()
                        @RequestBody News news) {
        newsService.addNews(news);
        return news;
    }

    @PutMapping("/news")
    @Operation(summary = "Редактировать новость",
            parameters = @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization",
            required = true,
            schema = @Schema(type = "string", defaultValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJyb2xlcyI6WyJlZGl0b3IiXX0.XakFspE3jgN4R-1q7kBYHmYMjaES63WuPS4pvE7Mxe8"),
            description = "Токен с правами редактирования"
    ))
    public News updateNews(@RequestBody News news) {
        newsService.updateNews(news);
        return news;

    }

    @DeleteMapping("/news/{id}")
    @Operation(summary = "Удалить новость",
            parameters = @Parameter(
                    in = ParameterIn.HEADER,
                    name = "Authorization",
                    required = true,
                    schema = @Schema(type = "string", defaultValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJyb2xlcyI6WyJlZGl0b3IiXX0.XakFspE3jgN4R-1q7kBYHmYMjaES63WuPS4pvE7Mxe8"),
                    description = "Токен с правами редактирования"
            ))
    public String deleteNews(@Parameter(description = "id новости")
                             @PathVariable("id") long id) {
        News news = newsService.getNews(id);
        if (news == null)
            throw new NoSuchNewsException("No such news with id " + id + " in DataBase");
        newsService.deleteNews(id);


        return "News with id " + id + " has deleted.";
    }

}
