package com.shinn.services.impl;

import com.shinn.dao.idao.IProductDAO;
import com.shinn.dao.impl.ProductDAO;
import com.shinn.model.Product;
import com.shinn.paging.Pageble;
import com.shinn.services.iservice.IProductService;

import java.sql.Timestamp;
import java.util.List;

public class ProductService implements IProductService {

//    @Inject
//    private IProductDAO productDAO;   Fix

    private IProductDAO productDAO;
    public ProductService() {
        productDAO = new ProductDAO();
    }
    @Override
    public Product save(Product product) {
        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        product.setCreatedBy("");
        Long productID = productDAO.insert(product);
        System.out.println("Hello");
        return productDAO.findOne(productID);
    }

    @Override
    public List<Product> findAll(Pageble paging) {
        return productDAO.findAll(paging);
    }

    @Override
    public void delete(Product product) {
         for(Long productID : product.getIds()) {
             productDAO.deleteOne(productID);
         }
    }

    @Override
    public void update(Product product) {
        product.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        product.setModifiedBy("");
        productDAO.update(product);
    }

    @Override
    public Integer count() {
        return productDAO.count();
    }
}
