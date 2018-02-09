package aleksey.stepanyuk.service.order;

import aleksey.stepanyuk.domain.entity.Order;
import aleksey.stepanyuk.domain.repo.OrderRepository;
import aleksey.stepanyuk.service.order.dto.OrderForListDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, ModelMapper modelMapper) {
        this.orderRepository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order saveProdIdNull(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order read(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }

    @Override
    public List<OrderForListDto> orderList() {
        List<Order> source = orderRepository.findAll();
        Type listType = new TypeToken<List<OrderForListDto>>() {}.getType();
        return modelMapper.map(source, listType);
    }
}