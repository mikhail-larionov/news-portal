package com.dataart.newsportal.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(value = "ARTICLES")
public class Article {
    @Id
    @Column(value = "ID")
    private Long id;
    @Column(value = "TITLE")
    private String title;
    @Column(value = "CONTENT")
    private String content;

}
