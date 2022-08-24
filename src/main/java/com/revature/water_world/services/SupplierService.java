package com.revature.water_world.services;

import com.revature.water_world.daos.*;
import com.revature.water_world.models.*;

import java.util.List;

public class SupplierService {

    private final SupplierDAO supDAO;

    public SupplierService(SupplierDAO supDAO) {
        this.supDAO = supDAO;
    }

    public void newSupplier(Supplier ord) {
        supDAO.saveSupplier(ord);
    }

    public List<Supplier> listAllSuppliers() {
        return supDAO.getAllSuppliers();
    }

}
