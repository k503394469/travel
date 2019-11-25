package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.service.RouteImgService;

import java.util.List;

public class RouteImgServiceImpl implements RouteImgService {
    private RouteImgDao dao=new RouteImgDaoImpl();
    @Override
    public List<RouteImg> imgList(int rid) {
        List<RouteImg> imgList = dao.imgList(rid);
        return imgList;
    }
}
