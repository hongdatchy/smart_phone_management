package com.hongdatchy.controller;

import com.google.gson.Gson;
import com.hongdatchy.model.JsonResult;
import com.hongdatchy.model.Product;
import com.hongdatchy.service.ProductService;
import com.hongdatchy.serviceImpl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/api/v2/product/*")
public class ProductController extends HttpServlet {

    ProductService productService = new ProductServiceImpl();
    JsonResult jsonResult = new JsonResult();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        Product product = new Gson().fromJson(request.getReader(), Product.class);
        try {
            Product newProduct = productService.insertProduct(product);
            json = (newProduct == null) ? jsonResult.jsonSuccess("insert jail")
                    : jsonResult.jsonSuccess(newProduct);
        } catch (Exception e) {
            e.printStackTrace();
            json = jsonResult.jsonFail("insert error");
        }
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        String pathInfo = request.getPathInfo();
        if(pathInfo.indexOf("/find-all") == 0) {
            try {
                List<Product> products = productService.findAll();
                json = jsonResult.jsonSuccess(products);
            } catch (Exception e) {
                e.printStackTrace();
                json = jsonResult.jsonFail("find all error");
            }
            response.getWriter().write(json);
        } else if(pathInfo.indexOf("/sort-by-field") == 0){
            String field = request.getParameter("field");
            try {
                List <Product>products = productService.sortByField(field);
                json = jsonResult.jsonSuccess(products == null ? "sort field fail" : products);
            } catch (Exception e) {
                e.printStackTrace();
                json = jsonResult.jsonFail("sort field error");
            }
            response.getWriter().write(json);
        } else {
            response.sendError(404, "Url is not supported");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        try {
            Product product = new Gson().fromJson(request.getReader(), Product.class);
            json = jsonResult.jsonSuccess(productService.updateProduct(product));
        } catch (Exception e){
            e.getStackTrace();
            json = jsonResult.jsonFail("update category error");
        }
        response.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json;
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            json = jsonResult.jsonSuccess(productService.deleteProduct(id));
        }catch (Exception e){
            e.printStackTrace();
            json= jsonResult.jsonFail("delete error");
        }
        response.getWriter().write(json);
    }
}
