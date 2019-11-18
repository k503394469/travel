package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkCode = request.getParameter("check");
        ResultInfo info=new ResultInfo();
        HttpSession session = request.getSession();
        ObjectMapper mapper=new ObjectMapper();
        String session_checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkCode==null||!session_checkCode.equalsIgnoreCase(checkCode)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(json);
            return;
        }
        Map<String, String[]> loginData = request.getParameterMap();

        User loginUser=new User();
        try {
            BeanUtils.populate(loginUser,loginData);
        } catch (Exception e) {

        }
        UserService service=new UserServiceImpl();
        User u=service.login(loginUser);

        if (u==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //""==u.getStatus()||"".equals(u.getStatus())||u.getStatus()==null||
        if (u!=null&&!"Y".equalsIgnoreCase(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("账户未激活,请激活后登陆");
        }
        if (u!=null&&"Y".equalsIgnoreCase(u.getStatus())){
            info.setFlag(true);
            session.setAttribute("user",u);
        }
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
