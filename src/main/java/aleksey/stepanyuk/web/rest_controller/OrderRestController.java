package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.Order;
import aleksey.stepanyuk.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class OrderRestController {
    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.saveProdIdNull(order));
    }

    //todo add PUT

    @GetMapping("/read/{id}")
    public ResponseEntity<Order> readOrder(@PathVariable Long id) {
        Order order = orderService.read(id);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Order>> orderList() {
        return ResponseEntity.ok(orderService.orderList());
    }
}