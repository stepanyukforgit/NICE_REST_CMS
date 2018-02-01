package aleksey.stepanyuk.domain.repo;

import aleksey.stepanyuk.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}