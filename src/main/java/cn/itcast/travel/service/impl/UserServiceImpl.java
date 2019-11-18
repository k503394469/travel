package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();
    @Override
    public boolean saveUser(User user) {
        User find_u=null;
        //获取是否有用户名存在
        try {
            find_u = dao.findUserByName(user.getUsername());
            if(find_u!=null) {
                return false;
            }else {
                dao.saveUser(user);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activeUser(String code) {
        User user=dao.findUserByCode(code);
        if (user!=null){
            user.setStatus("Y");
            dao.setUserStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User loginUser) {
        User u=dao.findUserByNameAndPassword(loginUser.getUsername(),loginUser.getPassword());
        return u;
    }
}
