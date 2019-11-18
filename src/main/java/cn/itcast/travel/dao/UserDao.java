package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * �����û�����ѯ�û�
     * @param username
     * @return
     */
    User findUserByName(String username);

    /**
     * �����û�
     * @param user
     */
    void saveUser(User user);

    User findUserByCode(String code);

    void setUserStatus(User user);

    User findUserByNameAndPassword(String username, String password);
}
