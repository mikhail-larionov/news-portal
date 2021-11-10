package com.dataart.newsportal.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String heading;
}
