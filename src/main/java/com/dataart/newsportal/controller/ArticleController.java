package com.dataart.newsportal.controller;

import com.dataart.newsportal.controller.dto.ArticleDto;
import com.dataart.newsportal.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping({"/", "/index"})
    public ResponseEntity<?> getAllArticles(Pageable page) {
        return ResponseEntity.
                ok(articleService.getAllArticles(page));
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable String id) {
        return ResponseEntity.
                ok(articleService.getArticleById(Long.valueOf(id)));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.uploadZip(file));
    }
    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/all")
    public ResponseEntity<?> getArticles(){
        return ResponseEntity
                .ok(articleService.getArticles());
    }
}
