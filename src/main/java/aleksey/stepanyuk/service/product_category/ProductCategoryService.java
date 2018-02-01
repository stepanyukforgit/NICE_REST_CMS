package aleksey.stepanyuk.service.product_category;

import aleksey.stepanyuk.domain.entity.ProductCategory;
import aleksey.stepanyuk.service.product_category.dto.ProdCatForListDto;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory save(ProductCategory productCategory);

    ProductCategory read(Long id);

    void delete(Long id);

    List<ProdCatForListDto> productCategoryList();
}