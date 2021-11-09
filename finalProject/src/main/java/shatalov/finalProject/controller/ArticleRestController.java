package shatalov.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shatalov.finalProject.entity.Article;
import shatalov.finalProject.service.ArticleService;

import java.util.List;

@RestController
public class ArticleRestController {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping(value = "/uploadArticle", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestParam("topic") String topic, @RequestParam("file") MultipartFile file) {
        articleService.create(file, topic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/articlesGetForTopic/{topic}")
    public ResponseEntity<List<Article>> read(@PathVariable(name = "topic") String topic) {
        final List<Article> articles = articleService.readAllHeaders(topic);
        return articles != null && !articles.isEmpty()
                ? ResponseEntity.ok(articles)
                : ResponseEntity.notFound().build();

    }

    @GetMapping(value = "/article/{id}")
    public ResponseEntity<Article> read(@PathVariable(name = "id") Long id) {
        final Article article = articleService.read(id);
        return article != null
                ? ResponseEntity.ok(article)
                : ResponseEntity.notFound().build();
    }

}
