package aleksey.stepanyuk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @GetMapping("/a_product_create")
    public String productCreate() {
        return "admin/components/product/a_product_create";
    }

    @GetMapping("/a_product_edit")
    public String productEdit() {
        return "admin/components/product/a_product_edit";
    }

    @GetMapping("/a_product_list")
    public String productList() {
        return "admin/components/product/a_product_list";
    }
}