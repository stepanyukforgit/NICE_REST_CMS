package aleksey.stepanyuk.service.article;

import aleksey.stepanyuk.domain.entity.Article;

import java.util.List;

public interface ArticleService {

    Article save(Article article);

    Article read(Long id);

    void delete(Long id);

    List<Article> articleList();
}