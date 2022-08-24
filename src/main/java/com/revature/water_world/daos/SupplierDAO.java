package com.revature.water_world.daos;

import com.revature.water_world.models.*;
import com.revature.water_world.utils.custom_exceptions.InvalidSQLException;
import com.revature.water_world.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class SupplierDAO {


    public void saveSupplier(Supplier obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO suppliers (supplierID, name, contactName, phoneNumber, email) VALUES (?,?,?,?,?)");
            ps.setString(1, obj.getSupplierID());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getContactName());
            ps.setString(4, obj.getPhoneNumber());
            ps.setString(5,obj.getEmail());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

    }





    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM suppliers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Supplier sup = new Supplier(rs.getString("supplierID"), rs.getString("name"),rs.getString("contactName"),rs.getString("phoneNumber"),rs.getString("email"));
                suppliers.add(sup);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return suppliers;
    }
}
