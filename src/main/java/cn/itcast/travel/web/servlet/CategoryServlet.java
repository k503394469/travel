package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    public void cat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("category///cat");
    }

    public void dog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("category///dog");

    }
}
