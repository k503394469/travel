package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
