package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.Carrier;
import aleksey.stepanyuk.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/carrier")
public class CarrierRestController {

    private final CarrierService carrierService;

    @Autowired
    public CarrierRestController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping("/save")
    public ResponseEntity<Carrier> createCarrier(@RequestBody Carrier carrier) {
        return ResponseEntity.ok(carrierService.save(carrier));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Carrier> readCarrier(@PathVariable Long id){
        return ResponseEntity.ok(carrierService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCarrier(@PathVariable Long id) {
        carrierService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Carrier>> carrierList() {
        return ResponseEntity.ok(carrierService.carrierList());
    }
}
