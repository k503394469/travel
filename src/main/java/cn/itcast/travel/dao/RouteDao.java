package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    int findRouteCount(int cid);
    List<Route> findRouteByPage(int cid,int start,int pageSize);
}
