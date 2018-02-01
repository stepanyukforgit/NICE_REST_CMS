package aleksey.stepanyuk.service.carrier;

import aleksey.stepanyuk.domain.entity.Carrier;

import java.util.List;

public interface CarrierService {

    Carrier save(Carrier carrier);

    Carrier read(Long id);

    void delete(Long id);

    List<Carrier> carrierList();
}
