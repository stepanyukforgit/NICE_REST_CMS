package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.ProductProposal;
import aleksey.stepanyuk.service.product_proposal.ProductProposalService;
import aleksey.stepanyuk.service.product_proposal.dto.ProdPropForEditDto;
import aleksey.stepanyuk.service.product_proposal.dto.ProdPropForListDto;
import aleksey.stepanyuk.service.product_proposal.dto.ProdPropForOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product_proposal")
public class ProductProposalRestController {

    private final ProductProposalService productProposalService;

    @Autowired
    public ProductProposalRestController(ProductProposalService productProposalService) {
        this.productProposalService = productProposalService;
    }

    @PostMapping("/save")
    public ResponseEntity<ProductProposal> createProductProposal(@RequestBody ProductProposal productProposal) {
        return ResponseEntity.ok(productProposalService.save(productProposal));
    }

    @PostMapping("/save/{productId}")
    public ResponseEntity<ProductProposal> createProductProposal(
            @RequestBody ProductProposal productProposal,
            @PathVariable Long productId) {
        return ResponseEntity.ok(productProposalService.save(productProposal, productId));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProdPropForEditDto> readProductProposal(@PathVariable Long id) {
        return ResponseEntity.ok(productProposalService.read(id));
    }

    @GetMapping("/read/product_id/{id}")
    public ResponseEntity<List<ProdPropForOrderDto>> readByProdId(@PathVariable Long id) {
        return ResponseEntity.ok(productProposalService.readByProdId(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProductProposal(@PathVariable Long id) {
        productProposalService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProdPropForListDto>> productProposalList() {
        return ResponseEntity.ok(productProposalService.productProposalList());
    }
}