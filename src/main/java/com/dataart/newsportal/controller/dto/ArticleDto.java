package com.dataart.newsportal.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
}
