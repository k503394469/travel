package cn.itcast.travel.service;

public interface FavoriteService {
    boolean isFavorite(int rid,int uid);

    void addFavouriteWithRIdAndUid(int rid, int uid);
}
