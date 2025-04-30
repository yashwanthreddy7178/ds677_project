package com.pinnuli.servlet;

import com.pinnuli.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteOneServlet")
public class DeleteOneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //接收页面的值
        String id = request.getParameter("id");
        MaintainService maintainService = new MaintainService();
        maintainService.deleteOne(id);
        //跳转
        request.getRequestDispatcher("/List.action").forward(request, response);
    }
}
