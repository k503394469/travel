package com.liu.test;

import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JackTest {
    public static void main(String[] args) throws JsonProcessingException {
        User u=new User();
        u.setName("zzz");
        ObjectMapper mapper=new ObjectMapper();
        String sss = mapper.writeValueAsString(u);
        System.out.println(sss);
    }
}
