package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByName(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username=?";
            user = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),username);
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        jt.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    @Override
    public User findUserByCode(String code) {
        User user = null;
        try {
            String sql="select * from tab_user where code=?";
            user = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),code);
        } catch (DataAccessException e) {
            user=null;
        }
        return user;
    }

    @Override
    public void setUserStatus(User user) {
        String sql="update tab_user set status='Y' where uid=?";
        jt.update(sql,user.getUid());
    }

    @Override
    public User findUserByNameAndPassword(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username=? and password=?";
            user = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),username,password);
        } catch (Exception e) {
            user = null;
        }
        return user;
    }
}
