package com.cfuv.rest_news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "news", schema = "public")
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private long id;
    @Column(name = "title")
    @NotBlank
    private String title;
    @Column(name = "article")
    @NotBlank
    private String article;
    @Column(name = "author")
    @NotBlank
    private String author;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    @Column(name = "time")
    @CurrentTimestamp
    private Timestamp timestamp;


}

