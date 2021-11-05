package com.dataart.newsportal.repository;

import com.dataart.newsportal.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
//    @Query("SELECT DISTINCT p.ID, p.TITLE, p.CONTENT FROM ARTICLES")
//    Page<Article> findAllDistinct(Pageable pageable);
}
