package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao=new RouteDaoImpl();
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public Page<Route> findQuery(int cid, int currentPage, int pageSize,String rname) {
        Page<Route> page=new Page<>();
        if (currentPage<=0){
            currentPage=1;
        }
        if (cid==0){
            cid=1;
        }
        page.setCurrentPage(currentPage);//当前页
        page.setPageSize(pageSize);//每页条数
        int count = dao.findRouteCount(cid,rname);
        page.setTotalCount(count);
        int totalPage=(count%pageSize==0)?count/pageSize:count/pageSize+1;
        page.setTotalPage(totalPage);
        int start=(currentPage-1)*pageSize;
        List<Route> routeList = dao.findRouteByPage(cid, start, pageSize,rname);
        page.setRouteList(routeList);
        return page;
    }

    @Override
    public Route findOneRoute(int rid) {
        Route route = dao.findOneRoute(rid);
        int count = favoriteDao.findFavoriteCountByRid(rid);
        route.setCount(count);
        return route;
    }
}
