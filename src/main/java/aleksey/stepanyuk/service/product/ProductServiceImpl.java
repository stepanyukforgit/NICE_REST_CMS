package aleksey.stepanyuk.service.product;

import aleksey.stepanyuk.domain.entity.Product;
import aleksey.stepanyuk.domain.repo.ProductRepository;
import aleksey.stepanyuk.service.product.dto.ProdForEditDto;
import aleksey.stepanyuk.service.product.dto.ProdForListDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProdForEditDto read(Long id) {
        return modelMapper.map(productRepository.findOne(id), ProdForEditDto.class);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<ProdForListDto> productList() {

        List<ProdForListDto> prodForListDtos =
                productRepository.findAll()
                        .stream().map(product -> modelMapper.map(product, ProdForListDto.class))
                        .collect(Collectors.toList());
        return prodForListDtos;
    }
}