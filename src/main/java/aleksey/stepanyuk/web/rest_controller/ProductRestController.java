package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.Product;
import aleksey.stepanyuk.service.product.ProductService;
import aleksey.stepanyuk.service.product.dto.ProdForEditDto;
import aleksey.stepanyuk.service.product.dto.ProdForListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProdForEditDto> readProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProdForListDto>> productList() {
        return ResponseEntity.ok(productService.productList());
    }
}