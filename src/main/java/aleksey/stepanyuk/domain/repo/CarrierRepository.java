package aleksey.stepanyuk.domain.repo;

import aleksey.stepanyuk.domain.entity.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}