package com.hongdatchy.controller;

import com.google.gson.Gson;
import com.hongdatchy.model.Admin;
import com.hongdatchy.model.Category;
import com.hongdatchy.model.JsonResult;
import com.hongdatchy.model.LoginForm;
import com.hongdatchy.security.TokenService;
import com.hongdatchy.service.AdminService;
import com.hongdatchy.service.CategoryService;
import com.hongdatchy.serviceImpl.AdminServiceImpl;
import com.hongdatchy.serviceImpl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/api/v2/admin/*")
public class AdminController extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();
    private JsonResult jsonResult = new JsonResult();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        String pathInfo = request.getPathInfo();
        if(pathInfo.indexOf("/login") == 0) {
            try {
                LoginForm loginForm = new Gson().fromJson(request.getReader(), LoginForm.class);
                boolean isAdmin = adminService.login(loginForm);
                if (isAdmin) {
                    TokenService tokenService = new TokenService();
                    String token = tokenService.getToken(loginForm.getUsername());
                    json = jsonResult.jsonSuccess(token);
                } else {
                    json = jsonResult.jsonFail("username or password incorrect");
                }
            } catch (Exception e) {
                e.printStackTrace();
                json = jsonResult.jsonFail("find all error");
            }
            response.getWriter().write(json);

        }else if(pathInfo.indexOf("/tokenIsValidate") == 0){
            String token = new Gson().fromJson(request.getReader(), String.class);
            if(token == null){
                json = jsonResult.jsonSuccess(false);
            }else {
                TokenService tokenService = new TokenService();
                String username = tokenService.getUsername(token);
                if(username != null){
                    json = jsonResult.jsonSuccess(true);
                }else {
                    json = jsonResult.jsonSuccess(false);
                }
            }
            response.getWriter().write(json);
        }else {
            response.sendError(404, "Url not support");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json;
        String pathInfo = request.getPathInfo();
        if(pathInfo.indexOf("/find-all")== 0){
            try {
                List<Admin> admins = adminService.findAll();
                json = jsonResult.jsonSuccess(admins);
            } catch (Exception e) {
                e.printStackTrace();
                json = jsonResult.jsonFail("find all error");
            }
            response.getWriter().write(json);
        }else{
            response.sendError(404, "Url not support");
        }
    }
}
