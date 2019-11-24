package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    int findRouteCount(int cid,String rname);
    List<Route> findRouteByPage(int cid,int start,int pageSize,String rname);
}
