package aleksey.stepanyuk.service.product;

import aleksey.stepanyuk.domain.entity.Product;
import aleksey.stepanyuk.service.product.dto.ProdForEditDto;
import aleksey.stepanyuk.service.product.dto.ProdForListDto;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    ProdForEditDto read(Long id);

    void delete(Long id);

    List<ProdForListDto> productList();
}