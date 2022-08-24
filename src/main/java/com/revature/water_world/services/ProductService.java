package com.revature.water_world.services;
import com.revature.water_world.daos.ProductDAO;
import com.revature.water_world.models.*;

import java.util.List;
public class ProductService {

    private final ProductDAO prodDAO;

    public ProductService(ProductDAO prodDAO) {
        this.prodDAO = prodDAO;
    }

    public void updateInventory(Product p){
        prodDAO.updateInv(p);

    }

    public String getName(String p){
        return prodDAO.getByProductId(p);
    }




    public List<Product> listAllProducts() {
        return prodDAO.getAllProducts();
    }
}
