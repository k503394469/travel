package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByName(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username=?";
            user = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";
        jt.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }
}
