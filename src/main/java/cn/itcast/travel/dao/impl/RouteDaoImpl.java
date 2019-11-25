package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findRouteCount(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List param = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid=? ");
            param.add(cid);
        }
        if (rname != null && !"".equals(rname) && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like ? ");
            param.add("%" + rname + "%");
        }
        sql = sb.toString();
        Integer count = jt.queryForObject(sql, Integer.class, param.toArray());
        System.out.println(sql);
        return count;
    }

    @Override
    public List<Route> findRouteByPage(int cid, int start, int pageSize, String rname) {
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List param = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid=? ");
            param.add(cid);
        }
        if (rname != null && !"".equals(rname) && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like ? ");
            param.add("%" + rname + "%");
        }
        sb.append(" limit ? , ?");
        param.add(start);
        param.add(pageSize);
        sql = sb.toString();
        List<Route> routeList = jt.query(sql, new BeanPropertyRowMapper<>(Route.class), param.toArray());
        System.out.println(sql);
        return routeList;
    }

    @Override
    public Route findOneRoute(int rid) {
        String sql="select * from tab_route where rid=?";
        Route route = jt.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        return route;
    }
}
