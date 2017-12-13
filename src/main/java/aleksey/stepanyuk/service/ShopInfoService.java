package aleksey.stepanyuk.service;


import aleksey.stepanyuk.domain.entity.ShopInfo;

public interface ShopInfoService {

    ShopInfo save(ShopInfo shopInfo);
    ShopInfo read();
    void delete();
}
