package com.revature.water_world.daos;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.water_world.utils.database.ConnectionFactory;
import com.revature.water_world.models.*;
import com.revature.water_world.utils.custom_exceptions.*;

public class ProductDAO {




    public static void updateInv(Product obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE products SET inventory = ? WHERE productID = ?");
            ps.setInt(1, obj.getInventory());
            ps.setString(2, obj.getProductID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred trying to update the table");
        }

    }


    public String getByProductId(String id) {
        String pName = "";
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT name FROM products WHERE productID = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pName = rs.getString("name");

            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
        return pName;
    }




    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product prod = new Product(rs.getString("productID"), rs.getString("productName"), rs.getInt("price"), rs.getString("description"), rs.getInt("inventory"), rs.getString("supplierID"));
                products.add(prod);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return products;
    }
}
