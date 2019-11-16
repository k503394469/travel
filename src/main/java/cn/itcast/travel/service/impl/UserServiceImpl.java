package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();
    @Override
    public boolean saveUser(User user) {
        //获取是否有用户名存在
        User find_u = dao.findUserByName(user.getUsername());
        if(find_u!=null) {
            return false;
        }else {
            dao.saveUser(user);
            return true;
        }
    }
}
