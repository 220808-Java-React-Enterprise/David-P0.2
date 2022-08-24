package com.revature.water_world.daos;


import com.revature.water_world.models.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.water_world.utils.database.ConnectionFactory;
import com.revature.water_world.utils.custom_exceptions.*;

import java.util.List;
public class InfoDAO {

    public static void saveInfo(AccountInfo obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO accountInfo (infoID, name, email, phoneNumber, address, city, state, zip, accountID) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1,obj.getInfoID());
            ps.setString(2,obj.getName());
            ps.setString(3,obj.getEmail());
            ps.setString(4,obj.getPhoneNumber());
            ps.setString(5,obj.getAddress());
            ps.setString(6,obj.getCity());
            ps.setString(7,obj.getState());
            ps.setString(8,obj.getZip());
            ps.setString(9,obj.getAccountID());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }
    public List<AccountInfo> getInfoByAccountId(String id) {
        List<AccountInfo> info = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accountInfo WHERE accountID = ?");
            ps.setString(8, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AccountInfo inf = new AccountInfo(rs.getString("infoID"), rs.getString("name"), rs.getString("email"), rs.getString("phoneNumber"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getString("zip"),rs.getString("accountID"));
                info.add(inf);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return info;
    }

}
