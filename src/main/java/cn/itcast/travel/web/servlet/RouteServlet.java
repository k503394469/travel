package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();

    public void findQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        //ËÑË÷¹Ø¼ü´Ê
        String rname=request.getParameter("rname");
//        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid = 0;
        if (cidStr != null && !"".equals(cidStr)&&!"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr != null && !"".equals(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int pageSize = 5;

        Page<Route> query = service.findQuery(cid, currentPage, pageSize,rname);
        String pageJson = writeJsonAsString(query);
        System.out.println(pageJson);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(pageJson);
    }

}
