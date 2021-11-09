package shatalov.finalProject.service;

import org.springframework.web.multipart.MultipartFile;
import shatalov.finalProject.entity.Article;

import java.util.List;

public interface ArticleService {
    void create(MultipartFile file, String topic);

    Article read(Long id);

    List<Article> readAllHeaders(String topic);
}
