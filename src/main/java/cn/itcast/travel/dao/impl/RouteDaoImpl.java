package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findRouteCount(int cid) {
        String sql="select count(*) from tab_route where cid=?";
        Integer count = jt.queryForObject(sql, Integer.class, cid);
        return count;
    }

    @Override
    public List<Route> findRouteByPage(int cid, int start, int pageSize) {
        String sql="select * from tab_route where cid=? limit ?,?";
        List<Route> routeList = jt.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, start, pageSize);
        return routeList;
    }
}
