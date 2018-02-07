package aleksey.stepanyuk.domain.repo;

import aleksey.stepanyuk.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}