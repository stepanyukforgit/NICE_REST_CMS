package aleksey.stepanyuk.domain.repo;

import aleksey.stepanyuk.domain.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{
}
