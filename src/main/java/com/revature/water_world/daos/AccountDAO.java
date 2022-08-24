package com.revature.water_world.daos;

import com.revature.water_world.models.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.water_world.utils.database.ConnectionFactory;
import com.revature.water_world.utils.custom_exceptions.*;

public class AccountDAO{

    public static void saveAccount(Account obj) { //Saves a new account to the database
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO accounts (accountID, username, password, privilege) VALUES (?,?,?,?)");
            ps.setString(1, obj.getAccountID());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setInt(4, obj.getPrivilege());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Account ac = new Account(rs.getString("accountID"),rs.getString("username"), rs.getString("password"), rs.getInt("privilege"), rs.getString("name"), rs.getString("phoneNumber"), rs.getString("email"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getString("zip"));
                //accounts.add(ac);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return accounts;
    }

    public static Account getUserByUsernameAndPassword(String username, String password) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE username = ? AND password = ?");

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            return new Account (rs.getString("accountID"),rs.getString("username"), rs.getString("password"), rs.getInt("privilege"));
        }
        catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    public String getUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM accounts WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("username");

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }
}
