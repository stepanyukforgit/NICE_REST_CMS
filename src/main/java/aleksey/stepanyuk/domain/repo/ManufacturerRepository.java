package aleksey.stepanyuk.domain.repo;

import aleksey.stepanyuk.domain.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>{
}
