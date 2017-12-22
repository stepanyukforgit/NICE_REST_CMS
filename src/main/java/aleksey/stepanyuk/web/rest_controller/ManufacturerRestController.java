package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.Manufacturer;
import aleksey.stepanyuk.service.ManufacturerService;
import aleksey.stepanyuk.service.dto.ManufForListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/manufacturer")
public class ManufacturerRestController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerRestController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer) {
        return ResponseEntity.ok(manufacturerService.save(manufacturer));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Manufacturer> readManufacturer(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturerService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteManufacturer(@PathVariable Long id) {
        manufacturerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ManufForListDto>> manufacturerList() {
        return ResponseEntity.ok(manufacturerService.manufacturerList());
    }
}
