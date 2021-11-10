package com.dataart.newsportal.repository;

import com.dataart.newsportal.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
    Page<Article> findAllByHeading(Pageable pageConfig, String heading);
}
