package aleksey.stepanyuk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrderController {

    @GetMapping("/a_order_create")
    public String orderCreate() {
        return "admin/components/order/a_order_create";
    }

    @GetMapping("/a_order_edit")
    public String orderEdit() {
        return "admin/components/order/a_order_edit";
    }

    @GetMapping("/a_order_list")
    public String orderList() {
        return "admin/components/order/a_order_list";
    }
}