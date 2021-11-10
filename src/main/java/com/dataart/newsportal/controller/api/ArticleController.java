package com.dataart.newsportal.controller.api;

import com.dataart.newsportal.service.ArticleService;
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
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping({"/", "/index"})
    @ResponseBody
    public ResponseEntity<?> getAllArticles(Pageable page, String heading) {
        return ResponseEntity.
                ok(articleService.getAllArticles(page, heading));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("heading") String heading) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.uploadZip(file, heading));
    }
}
