package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;
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

@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取验证码
        String checkCode = request.getParameter("check");
        HttpSession session = request.getSession();
        String session_checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (session_checkcode==null||!(checkCode.equalsIgnoreCase(session_checkcode))){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //获取注册表达信息
        Map<String, String[]> userMap = request.getParameterMap();
        User user=new User();
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        try {
            BeanUtils.populate(user,userMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService service=new UserServiceImpl();
        boolean flag=service.saveUser(user);
        ResultInfo info=new ResultInfo();
        if (flag){
            try {
                MailUtils.sendMail(user.getEmail(),"<a href='http://localhost:6324/t/activeUserServlet?code="+user.getCode()+"'>请点击激活账号</a>","账号激活");
                info.setFlag(true);
            } catch (Exception e) {
                info.setFlag(false);
                info.setErrorMsg("注册失败");
            }
        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        //返回JSON数据
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
