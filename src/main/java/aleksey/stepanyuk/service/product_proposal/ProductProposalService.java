package aleksey.stepanyuk.service.product_proposal;

import aleksey.stepanyuk.domain.entity.ProductProposal;
import aleksey.stepanyuk.service.product_proposal.dto.ProdPropForEditDto;
import aleksey.stepanyuk.service.product_proposal.dto.ProdPropForListDto;

import java.util.List;

public interface ProductProposalService {

    ProductProposal save(ProductProposal productProposal);

    ProductProposal save(ProductProposal productProposal, Long productId);

    ProdPropForEditDto read(Long id);

    void delete(Long id);

    List<ProdPropForListDto> productProposalList();
}