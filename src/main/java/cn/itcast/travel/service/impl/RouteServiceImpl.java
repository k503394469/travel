package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao=new RouteDaoImpl();
    @Override
    public Page<Route> findQuery(int cid, int currentPage, int pageSize) {
        Page<Route> page=new Page<>();
        if (currentPage<=0){
            currentPage=1;
        }
        page.setCurrentPage(currentPage);//当前页
        page.setPageSize(pageSize);//每页条数
        int count = dao.findRouteCount(cid);
        page.setTotalCount(count);
        int totalPage=(count%pageSize==0)?count/pageSize:count/pageSize+1;
        page.setTotalPage(totalPage);
        int start=(currentPage-1)*pageSize;
        List<Route> routeList = dao.findRouteByPage(cid, start, pageSize);
        page.setRouteList(routeList);
        return page;
    }
}
