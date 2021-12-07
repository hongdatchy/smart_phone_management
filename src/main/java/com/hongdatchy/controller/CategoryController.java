package com.hongdatchy.controller;

import com.google.gson.Gson;
import com.hongdatchy.model.Category;
import com.hongdatchy.model.JsonResult;
import com.hongdatchy.service.CategoryService;
import com.hongdatchy.serviceImpl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/api/v2/category/*")
public class CategoryController extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();
    private JsonResult jsonResult = new JsonResult();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        Category category = new Gson().fromJson(request.getReader(), Category.class);
        try {
            Category newCategory = categoryService.insertCategory(category.getName());
            json = (newCategory == null) ? jsonResult.jsonSuccess("insert jail")
                    : jsonResult.jsonSuccess(newCategory);
        } catch (Exception e) {
            e.printStackTrace();
            json = jsonResult.jsonFail("insert error");
        }
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        String pathInfo = request.getPathInfo();
        if(pathInfo.indexOf("/find-all")== 0){
            try {
                List<Category> categories = categoryService.findAll();
                json = jsonResult.jsonSuccess(categories);
            } catch (Exception e) {
                e.printStackTrace();
                json = jsonResult.jsonFail("find all error");
            }
            response.getWriter().write(json);
        }else{
            response.sendError(404, "Url not support");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        Category category = new Gson().fromJson(request.getReader(), Category.class);
        try {
            boolean bool =  categoryService.updateCategory(category);
            json = jsonResult.jsonSuccess(bool);
        } catch (Exception e) {
            e.printStackTrace();
            json = jsonResult.jsonFail("update error");
        }
        response.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            boolean bool = categoryService.deleteCategory(id);
            json = jsonResult.jsonSuccess(bool);
        } catch (Exception e) {
            e.printStackTrace();
            json = jsonResult.jsonFail("delete error");
        }
        response.getWriter().write(json);
    }
}
