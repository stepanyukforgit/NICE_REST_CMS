package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.ShopInfo;
import aleksey.stepanyuk.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shopinfo")
public class ShopInfoRestController {

    private final ShopInfoService shopInfoService;

    @Autowired
    public ShopInfoRestController(ShopInfoService shopInfoService) {
        this.shopInfoService = shopInfoService;
    }

    @PostMapping("/save")
    public ResponseEntity<ShopInfo> createShopInfo(@RequestBody ShopInfo shopInfo) {
        return ResponseEntity.ok(shopInfoService.save(shopInfo));
    }

    @GetMapping("/read")
    public ResponseEntity<ShopInfo> readShopInfo(){
        ShopInfo shopInfo = shopInfoService.read();
        return ResponseEntity.ok(shopInfo);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteShopInfo() {
        shopInfoService.delete();
        return ResponseEntity.ok().build();
    }
}