package aleksey.stepanyuk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductCategoryController {

    @GetMapping("/a_product_category_create")
    public String productCategoryCreate(){
        return "admin/components/product_category/a_product_category_create";
    }

    @GetMapping("/a_product_category_edit")
    public String productCategoryEdit(){
        return "admin/components/product_category/a_product_category_edit";
    }

    @GetMapping("/a_product_category_list")
    public String productCategoryList(){
        return "admin/components/product_category/a_product_category_list";
    }
}
