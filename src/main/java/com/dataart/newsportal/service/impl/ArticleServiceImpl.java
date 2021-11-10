package com.dataart.newsportal.service.impl;

import com.dataart.newsportal.controller.dto.ArticleDto;
import com.dataart.newsportal.entity.Article;
import com.dataart.newsportal.exception.IllegalFileExtension;
import com.dataart.newsportal.exception.IllegalHeadingNameException;
import com.dataart.newsportal.exception.IllegalNumberOfFilesException;
import com.dataart.newsportal.repository.ArticleRepository;
import com.dataart.newsportal.service.ArticleService;
import com.dataart.newsportal.service.Headings;
import com.dataart.newsportal.service.mapper.ArticleMapper;

import org.apache.commons.io.FilenameUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service

public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private static final String TARGET_ORIGINAL_FILE_EXTENSION = ".zip";
    private static final String TARGET_ARTICLE_FILE_NAME = "article.txt";

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public Page<ArticleDto> getAllArticles(Pageable pageConfig, String heading) {
        Page<Article> page;
        if (heading == null) {
            page = articleRepository.findAll(pageConfig);
        } else if (checkHeadingForExisting(heading)) {
            page = articleRepository.findAllByHeading(pageConfig, heading);
        } else {
            throw new IllegalHeadingNameException("Illegal heading name");
        }
        return page
                .map(articleMapper::toDto);
    }

    @Override
    public ArticleDto uploadZip(MultipartFile file, String heading) throws IOException {
        if (!checkHeadingForExisting(heading)) {
            throw new IllegalHeadingNameException("Illegal heading name");
        }
        if (checkFileForExtension(file.getOriginalFilename(), TARGET_ORIGINAL_FILE_EXTENSION)) {
            throw new IllegalFileExtension("File is not zip");
        }
        Article articleFromZip = getArticleFromZip(file, heading);
        Article article = articleRepository.save(articleFromZip);
        return articleMapper.toDto(article);
    }

    @Override
    public List<ArticleDto> getArticles() {
        List<Article> all = (List<Article>) articleRepository.findAll();
        return all.stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    private Article getArticleFromZip(MultipartFile file, String heading) throws IOException {
        try (InputStream is = new ByteArrayInputStream(file.getBytes());
             ZipInputStream zipInputStream = new ZipInputStream(is)) {
            ZipEntry entry;
            int numberOfFiles = 0;
            String title = null;
            String content = null;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                numberOfFiles++;
                if (numberOfFiles > 1) {
                    throw new IllegalNumberOfFilesException("Too much files in zip");
                }
                if (entry.getName().equals(TARGET_ARTICLE_FILE_NAME)) {
                    throw new IllegalFileExtension("Illegal file extension");
                }
                Scanner scanner = new Scanner(zipInputStream);
                title = getTitle(scanner);
                content = getContent(scanner);
            }
            return new Article(null, title, content, heading);
        }
    }

    private String getTitle(Scanner scanner) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            throw new IllegalNumberOfFilesException("There are lower then 2 strings in file");
        }
    }

    private String getContent(Scanner scanner) {
        StringBuilder content = new StringBuilder();
        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine()).append(" ");
        }
        if (content.length() < 1) {
            throw new IllegalNumberOfFilesException("There are lower then 2 strings in file");
        } else {
            return content.toString();
        }
    }

    private boolean checkFileForExtension(String fileName, String extension) {
        return FilenameUtils.getExtension(fileName).equals(extension);
    }

    private boolean checkHeadingForExisting(String heading) {
        return Headings.getHeadingByName(heading).isPresent();
    }
}
