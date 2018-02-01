package aleksey.stepanyuk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ShopInfoController {

    @GetMapping("/a_shopinfo")
    public String article() {
        return "admin/components/shopinfo/a_shopinfo";
    }
}