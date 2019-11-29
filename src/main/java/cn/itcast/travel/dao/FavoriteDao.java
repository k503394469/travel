package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public interface FavoriteDao {
    Favorite findFavoriteByRidAndUid(int rid,int uid);
    int findFavoriteCountByRid(int rid);

    void add(int rid, int uid);
}
