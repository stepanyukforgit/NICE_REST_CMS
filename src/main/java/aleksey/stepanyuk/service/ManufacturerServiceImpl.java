package aleksey.stepanyuk.service;

import aleksey.stepanyuk.domain.entity.Manufacturer;
import aleksey.stepanyuk.domain.repo.ManufacturerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    private ModelMapper modelMapper;

    @Autowired
    ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ModelMapper modelMapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer read(Long id) {
        return manufacturerRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        manufacturerRepository.delete(id);
    }

    @Override
    public List<ManufForListDto> manufacturerList() {

        List<ManufForListDto> manufDtoList =
                manufacturerRepository.findAll()
                        .stream().map(manufacturer -> modelMapper.map(manufacturer, ManufForListDto.class))
                        .collect(Collectors.toList());
        return manufDtoList;
    }
}