package aleksey.stepanyuk.service;


import aleksey.stepanyuk.domain.entity.Manufacturer;
import aleksey.stepanyuk.service.dto.ManufForListDto;

import java.util.List;

public interface ManufacturerService {

    Manufacturer save(Manufacturer manufacturer);

    Manufacturer read(Long id);

    void delete(Long id);

    List<ManufForListDto> manufacturerList();
}
