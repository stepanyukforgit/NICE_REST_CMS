package aleksey.stepanyuk.service;

import aleksey.stepanyuk.domain.entity.ProductCategory;
import aleksey.stepanyuk.domain.repo.ProductCategoryRepository;
import aleksey.stepanyuk.service.dto.ProdCatForListDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;

    private ModelMapper modelMapper;

    @Autowired
    ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository, ModelMapper modelMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory read(Long id) {
        return productCategoryRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        productCategoryRepository.delete(id);
    }

    @Override
    public List<ProdCatForListDto> productCategoryList() {

        List<ProdCatForListDto> prodCatDtoList =
                productCategoryRepository.findAll()
                        .stream().map(productCategory -> modelMapper.map(productCategory, ProdCatForListDto.class))
                        .collect(Collectors.toList());
        return prodCatDtoList;
    }
}
