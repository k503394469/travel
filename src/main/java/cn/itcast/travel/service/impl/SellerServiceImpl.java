package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.SellerService;

public class SellerServiceImpl implements SellerService {
    private SellerDao dao=new SellerDaoImpl();
    @Override
    public Seller findSellerBySid(int sid) {
        Seller seller = dao.findSellerBySid(sid);
        return seller;
    }
}
