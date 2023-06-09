package com.shinntl.api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinntl.constant.SystemConstant;
import com.shinntl.model.Product;
import com.shinntl.model.User;
import com.shinntl.paging.PageRequest;
import com.shinntl.paging.Pageble;
import com.shinntl.services.impl.ProductService;
import com.shinntl.services.iservice.IProductService;
import com.shinntl.sorter.Sorter;
import com.shinntl.utils.FormUtil;
import com.shinntl.utils.HttpUtil;
import com.shinntl.utils.SessionUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api-products"})
public class ProductAPI extends HttpServlet {
//    @Inject
//    private IProductService productService;   Fix
    private IProductService productService;
    public ProductAPI() {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = FormUtil.toModel(Product.class, request);

        Pageble paging = new PageRequest(product.getPage(), product.getMaxPageItem(),
                    new Sorter(product.getSortName(), product.getSortBy()));
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");        // Báo cho client dữ liệu trả về là Json
        List< Product> list = productService.findAll(paging);
        objectMapper.writeValue(response.getOutputStream(), list);     // Hiển thị dữ liệu Json
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Product product = HttpUtil.of(request.getReader()).toModel(Product.class);
        product.setCreatedBy("admin1");
        product = productService.save(product);
        objectMapper.writeValue(response.getOutputStream(), product);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Product product = HttpUtil.of(request.getReader()).toModel(Product.class);
        productService.delete(product);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Product product = HttpUtil.of(request.getReader()).toModel(Product.class);
        productService.update(product);
    }
}
