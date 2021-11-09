package shatalov.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shatalov.finalProject.entity.Article;
import shatalov.finalProject.exception.EmptyZipException;
import shatalov.finalProject.exception.TooManyFilesException;
import shatalov.finalProject.exception.TooShortTxtException;
import shatalov.finalProject.repository.ArticleRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public void create(MultipartFile file, String topic) {
        try {
            ZipInputStream zis = new ZipInputStream(file.getInputStream());
            String wholeArticle = unpackingZipToString(zis);
            Article article = new Article();
            String header = wholeArticle.substring(0, wholeArticle.indexOf("\n") - 1);
            String body = wholeArticle.substring(wholeArticle.indexOf("\n") + 1);
            if (body.equals("")) {
                throw new TooShortTxtException("Wrong .txt file! No body of the article");
            }
            article.setHeader(header);
            article.setBody(body);
            article.setTopic(topic);
            zis.close();
            articleRepository.save(article);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String unpackingZipToString(ZipInputStream zis) {
        String resultString = "";
        Optional<ZipEntry> opt = null;
        try {
            opt = Optional.ofNullable(zis.getNextEntry());
            if (opt.isEmpty()) {
                throw new EmptyZipException("There is no files inside the zip");
            } else {
                byte[] data = zis.readAllBytes();
                resultString = new String(data, StandardCharsets.UTF_8);
            }
            if (zis.getNextEntry() != null) {
                throw new TooManyFilesException("Wrong zip format! There is only 1 file possible");
            }
            return resultString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    @Override
    public List<Article> readAllHeaders(String topic) {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        Collections.reverse(articles); // сделано для отображения статей в формате от новых к старым
        if (!Objects.equals(topic, "all")) {
            articles = articles.stream().filter(article -> (article.getTopic().equals(topic))).toList();
        }
        return articles;
    }

    @Override
    public Article read(Long id) {
        Optional<Article> opt = articleRepository.findById(id);
        return opt.orElse(null);
    }
}
