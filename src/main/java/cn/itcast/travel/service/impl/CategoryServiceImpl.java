package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao=new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        List<Category> categoryList = null;
        Jedis jedis = JedisUtil.getJedis();
        Set<String> categorySet = jedis.zrange("category", 0, -1);
        if (categorySet==null||categorySet.size()==0){
            categoryList=dao.findAll();
            for (Category categorys : categoryList) {
                jedis.zadd("category",categorys.getCid(),categorys.getCname());
            }
        }else {
            //如果存在于redis内部,则直接获取,把set转为list
            categoryList=new LinkedList<>();
            for (String cname : categorySet) {
                Category temp=new Category();
                temp.setCname(cname);
                categoryList.add(temp);
            }
        }
        return categoryList;
    }
}
