package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.ProductCategory;
import aleksey.stepanyuk.service.ProductCategoryService;
import aleksey.stepanyuk.service.dto.ProdCatForListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product_category")
public class ProductCategoryRestController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryRestController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(productCategoryService.save(productCategory));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProductCategory> readProductCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProductCategory(@PathVariable Long id) {
        productCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProdCatForListDto>> productCategoryList() {
        return ResponseEntity.ok(productCategoryService.productCategoryList());
    }
}
