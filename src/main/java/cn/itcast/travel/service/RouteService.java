package cn.itcast.travel.service;

import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    Page<Route> findQuery(int cid,int currentPage,int pageSize,String rname);

    Route findOneRoute(int rid);
}
