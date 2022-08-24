package com.revature.water_world.daos;


import com.revature.water_world.utils.custom_exceptions.*;
import com.revature.water_world.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.revature.water_world.models.*;

public class OrderDAO {

    public static void saveOrd(Order obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (orderID, accountID, datePlaced, expectedArrival, pCost, quantity, productID) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, obj.getOrderID());
            ps.setString(2, obj.getAccountID());
            ps.setString(3, obj.getDatePlaced());
            ps.setString(4, obj.getExpectedArrival());
            ps.setDouble(5,obj.getCost());
            ps.setInt(6,obj.getQuantity());
            ps.setString(7,obj.getProductID());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

    }


    public List<Order> getAllByAccountId(String id) {
        List<Order> orders = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE accountID = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(rs.getString("orderID"), rs.getString("accountID"), rs.getString("datePlaced"), rs.getString("expectedArrival"),rs.getDouble("pCost"),rs.getInt("quantity"),rs.getString("productID"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return orders;
    }
}
