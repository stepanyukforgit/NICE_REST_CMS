package aleksey.stepanyuk.service.product_proposal;

import aleksey.stepanyuk.domain.entity.Product;
import aleksey.stepanyuk.domain.entity.ProductProposal;
import aleksey.stepanyuk.domain.repo.ProductProposalRepository;
import aleksey.stepanyuk.service.product_proposal.dto.ProdPropForEditDto;
import aleksey.stepanyuk.service.product_proposal.dto.ProdPropForListDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductProposalServiceImpl implements ProductProposalService {

    private ProductProposalRepository productProposalRepository;

    private ModelMapper modelMapper;

    @Autowired
    ProductProposalServiceImpl(ProductProposalRepository productProposalRepository, ModelMapper modelMapper) {
        this.productProposalRepository = productProposalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductProposal save(ProductProposal productProposal) {
        return productProposalRepository.save(productProposal);
    }

    @Override
    public ProductProposal save(ProductProposal productProposal, Long productId) {
        Product product = new Product();
        product.setId(productId);
        productProposal.setProduct(product);
        return productProposalRepository.save(productProposal);
    }

    @Override
    public ProdPropForEditDto read(Long id) {
        return modelMapper.map(productProposalRepository.findOne(id), ProdPropForEditDto.class);
    }

    @Override
    public void delete(Long id) {
        productProposalRepository.delete(id);
    }

    @Override
    public List<ProdPropForListDto> productProposalList() {

        List<ProdPropForListDto> prodPropDtoList =
                productProposalRepository.findAll()
                        .stream().map(productProposal -> modelMapper.map(productProposal, ProdPropForListDto.class))
                        .collect(Collectors.toList());
        return prodPropDtoList;
    }
}