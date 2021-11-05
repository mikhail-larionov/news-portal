package com.dataart.newsportal.service;

import com.dataart.newsportal.controller.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ArticleService {
    ArticleDto getArticleById(Long id);

    Page<ArticleDto> getAllArticles(Pageable page);

    ArticleDto uploadZip(MultipartFile file) throws IOException;

    List<ArticleDto> getArticles();
}
