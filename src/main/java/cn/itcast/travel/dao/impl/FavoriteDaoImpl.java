package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findFavoriteByRidAndUid(int rid, int uid) {
        String sql="select * from tab_favorite where rid=? and uid=?";
        Favorite favorite=null;
        try {
            favorite = jt.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class),rid,uid);
        } catch (DataAccessException e) {
        }
        return favorite;
    }

    @Override
    public int findFavoriteCountByRid(int rid) {
        String sql="select count(*) from tab_favorite where rid=?";
        Integer count = jt.queryForObject(sql, Integer.class, rid);
        return count;
    }

    @Override
    public void add(int rid, int uid) {
        String sql="insert into tab_favorite values(?,?,?)";
        jt.update(sql,rid,new Date(),uid);
    }
}
