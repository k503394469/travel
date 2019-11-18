package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    boolean saveUser(User user);

    boolean activeUser(String code);

    User login(User loginUser);
}
