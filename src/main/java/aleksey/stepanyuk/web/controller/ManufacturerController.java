package aleksey.stepanyuk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManufacturerController {

    @GetMapping("/a_manufacturer_create")
    public String manufacturerCreate() {
        return "admin/components/manufacturer/a_manufacturer_create";
    }

    @GetMapping("/a_manufacturer_edit")
    public String manufacturerEdit() {
        return "admin/components/manufacturer/a_manufacturer_edit";
    }

    @GetMapping("/a_manufacturer_list")
    public String manufacturerList() {
        return "admin/components/manufacturer/a_manufacturer_list";
    }
}