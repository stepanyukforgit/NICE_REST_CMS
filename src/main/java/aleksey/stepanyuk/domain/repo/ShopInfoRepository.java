package aleksey.stepanyuk.domain.repo;


import aleksey.stepanyuk.domain.entity.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopInfoRepository extends JpaRepository<ShopInfo, Long> {
}
