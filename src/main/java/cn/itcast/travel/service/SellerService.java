package cn.itcast.travel.service;

import cn.itcast.travel.domain.Seller;

public interface SellerService {
    Seller findSellerBySid(int sid);
}
