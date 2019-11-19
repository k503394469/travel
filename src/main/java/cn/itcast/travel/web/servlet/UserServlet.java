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
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    /**
     * 登陆
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkCode = request.getParameter("check");
        ResultInfo info = new ResultInfo();
        HttpSession session = request.getSession();
        ObjectMapper mapper = new ObjectMapper();
        String session_checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkCode == null || !session_checkCode.equalsIgnoreCase(checkCode)) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(json);
            return;
        }
        Map<String, String[]> loginData = request.getParameterMap();

        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, loginData);
        } catch (Exception e) {

        }
//        service = new UserServiceImpl();
        User u = service.login(loginUser);

        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //""==u.getStatus()||"".equals(u.getStatus())||u.getStatus()==null||
        if (u != null && !"Y".equalsIgnoreCase(u.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("账户未激活,请激活后登陆");
        }
        if (u != null && "Y".equalsIgnoreCase(u.getStatus())) {
            info.setFlag(true);
            session.setAttribute("user", u);
        }
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(json);
    }

    /**
     * 注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取验证码
        String checkCode = request.getParameter("check");
        HttpSession session = request.getSession();
        String session_checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (session_checkcode == null || !(checkCode.equalsIgnoreCase(session_checkcode))) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //获取注册表达信息
        Map<String, String[]> userMap = request.getParameterMap();
        User user = new User();
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        try {
            BeanUtils.populate(user, userMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        service = new UserServiceImpl();
        boolean flag = service.saveUser(user);
        ResultInfo info = new ResultInfo();
        if (flag) {
            try {
                MailUtils.sendMail(user.getEmail(), "<a href='http://localhost:6324/t/user/active?code=" + user.getCode() + "'>请点击激活账号</a>", "账号激活");
                info.setFlag(true);
            } catch (Exception e) {
                info.setFlag(false);
                info.setErrorMsg("注册失败");
            }
        } else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        //返回JSON数据
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 查找一个用户
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOneUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        ObjectMapper mapper = new ObjectMapper();
        String name_json = mapper.writeValueAsString(user);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(name_json);
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 激活账号
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
//        service = new UserServiceImpl();
        boolean flag = service.activeUser(code);
        if (flag) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("激活成功");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("激活失败,请联系管理员");
        }
    }
}
