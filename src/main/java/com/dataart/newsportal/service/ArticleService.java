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

    Page<ArticleDto> getAllArticles(Pageable page, String heading);

    ArticleDto uploadZip(MultipartFile file, String heading) throws IOException;

    List<ArticleDto> getArticles();
}
