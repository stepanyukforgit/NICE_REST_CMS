package aleksey.stepanyuk.service.carrier;

import aleksey.stepanyuk.domain.entity.Carrier;
import aleksey.stepanyuk.domain.repo.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {

    private CarrierRepository carrierRepository;

    @Autowired
    CarrierServiceImpl(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    @Override
    public Carrier save(Carrier carrier) {
        return carrierRepository.save(carrier);
    }

    @Override
    public Carrier read(Long id) {
        return carrierRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        carrierRepository.delete(id);
    }

    @Override
    public List<Carrier> carrierList() {
        return carrierRepository.findAll();
    }
}