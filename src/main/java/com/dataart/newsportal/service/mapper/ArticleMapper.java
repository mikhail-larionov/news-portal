package com.dataart.newsportal.service.mapper;

import com.dataart.newsportal.controller.dto.ArticleDto;
import com.dataart.newsportal.entity.Article;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    private final ModelMapper mapper;

    public ArticleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ArticleDto toDto(Article article) {
        return mapper.map(article, ArticleDto.class);
    }

    public Article toEntity(ArticleDto articleDto) {
        return mapper.map(articleDto, Article.class);
    }

}
