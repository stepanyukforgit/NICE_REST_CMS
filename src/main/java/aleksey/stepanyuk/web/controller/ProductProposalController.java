package aleksey.stepanyuk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductProposalController {

    @GetMapping("/a_product_proposal_create")
    public String productProposalCreate() {
        return "admin/components/product_proposal/a_product_proposal_create";
    }

    @GetMapping("/a_product_proposal_edit")
    public String productProposalEdit() {
        return "admin/components/product_proposal/a_product_proposal_edit";
    }
}