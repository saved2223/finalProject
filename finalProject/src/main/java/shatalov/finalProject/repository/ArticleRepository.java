package shatalov.finalProject.repository;

import org.springframework.data.repository.CrudRepository;
import shatalov.finalProject.entity.Article;

public interface ArticleRepository  extends CrudRepository<Article, Long> {
}
