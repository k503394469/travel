package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteImgService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.SellerService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteImgServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import cn.itcast.travel.service.impl.SellerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private RouteImgService imgService = new RouteImgServiceImpl();
    private SellerService sellerService = new SellerServiceImpl();
    private FavoriteService favoriteService=new FavoriteServiceImpl();

    public void findQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        //ËÑË÷¹Ø¼ü´Ê
        String rname = request.getParameter("rname");
//        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid = 0;
        if (cidStr != null && !"".equals(cidStr) && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr != null && !"".equals(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int pageSize = 5;

        Page<Route> query = service.findQuery(cid, currentPage, pageSize, rname);
        String pageJson = writeJsonAsString(query);
        System.out.println(pageJson);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(pageJson);
    }

    public void route_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        Route route = service.findOneRoute(Integer.parseInt(rid));
        List<RouteImg> imgList = imgService.imgList(route.getRid());
        route.setRouteImgList(imgList);
        Seller seller = sellerService.findSellerBySid(route.getSid());
        route.setSeller(seller);
        response.setContentType("application/json;charset=utf-8");
        String json = writeJsonAsString(route);
        response.getWriter().print(json);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid_s=request.getParameter("rid");
        int rid=Integer.parseInt(rid_s);
        User u= (User) request.getSession().getAttribute("user");
        int uid;
        if (u==null){
            uid=0;
        }else {
            uid=u.getUid();
        }
        boolean favorite = favoriteService.isFavorite(rid, uid);
        String json = writeJsonAsString(favorite);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(json);
    }
}
