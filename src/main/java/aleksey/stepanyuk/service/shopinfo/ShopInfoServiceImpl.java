package aleksey.stepanyuk.service.shopinfo;

import aleksey.stepanyuk.domain.entity.ShopInfo;
import aleksey.stepanyuk.domain.repo.ShopInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopInfoServiceImpl implements ShopInfoService {

    private final ShopInfoRepository shopInfoRepository;

    @Autowired
    public ShopInfoServiceImpl(ShopInfoRepository shopInfoRepository) {
        this.shopInfoRepository = shopInfoRepository;
    }

    @Override
    public ShopInfo save(ShopInfo shopInfo) {
        return shopInfoRepository.save(shopInfo);
    }

    @Override
    public ShopInfo read() {
        return shopInfoRepository.findAll().stream().findFirst().orElse(new ShopInfo());
    }

    @Override
    public void delete() {
        shopInfoRepository.deleteAll();
    }
}