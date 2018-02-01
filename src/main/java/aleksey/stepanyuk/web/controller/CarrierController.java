package aleksey.stepanyuk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CarrierController {

    @GetMapping("/a_carrier_create")
    public String carrierCreate() {
        return "admin/components/carrier/a_carrier_create";
    }

    @GetMapping("/a_carrier_edit")
    public String carrierEdit() {
        return "admin/components/carrier/a_carrier_edit";
    }

    @GetMapping("/a_carrier_list")
    public String carrierList() {
        return "admin/components/carrier/a_carrier_list";
    }
}