package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao=new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = dao.findFavoriteByRidAndUid(rid, uid);
        if (favorite==null){
            return false;
        }else {
            return true;
        }
    }
}
