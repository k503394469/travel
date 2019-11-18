package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findUserByName(String username);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    User findUserByCode(String code);

    void setUserStatus(User user);

    User findUserByNameAndPassword(String username, String password);
}
