package aleksey.stepanyuk.service.order;

import aleksey.stepanyuk.domain.entity.Order;
import aleksey.stepanyuk.domain.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public Order saveProdIdNull(Order order) {
        return repository.save(order);
    }

    @Override
    public Order read(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Order> orderList() {
        return repository.findAll();
    }
}