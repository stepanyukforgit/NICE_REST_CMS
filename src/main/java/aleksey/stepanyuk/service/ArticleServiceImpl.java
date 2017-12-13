package aleksey.stepanyuk.service;

import aleksey.stepanyuk.domain.entity.Article;
import aleksey.stepanyuk.domain.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Article save(Article article) {
        return repository.save(article);
    }

    @Override
    public Article read(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Article> articleList() {
        return repository.findAll();
    }
}
