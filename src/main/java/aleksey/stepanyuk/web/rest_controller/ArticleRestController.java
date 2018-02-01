package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.Article;
import aleksey.stepanyuk.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/article")
public class ArticleRestController {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/save")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.save(article));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Article> readArticle(@PathVariable Long id) {
        Article article = articleService.read(id);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Article>> articleList() {
        return ResponseEntity.ok(articleService.articleList());
    }
}