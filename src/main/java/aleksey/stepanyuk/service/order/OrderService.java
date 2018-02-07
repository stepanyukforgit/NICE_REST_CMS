package aleksey.stepanyuk.service.order;

import aleksey.stepanyuk.domain.entity.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    Order saveProdIdNull(Order order);

    Order read(Long id);

    void delete(Long id);

    List<Order> orderList();
}