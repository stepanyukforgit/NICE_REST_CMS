package aleksey.stepanyuk.service.manufacturer;

import aleksey.stepanyuk.domain.entity.Manufacturer;
import aleksey.stepanyuk.service.manufacturer.dto.ManufForListDto;

import java.util.List;

public interface ManufacturerService {

    Manufacturer save(Manufacturer manufacturer);

    Manufacturer read(Long id);

    void delete(Long id);

    List<ManufForListDto> manufacturerList();
}