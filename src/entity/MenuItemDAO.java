/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author Gokhan
 */
public class MenuItemDAO implements DAO<MenuItem>
{
    public MenuItemDAO() {

    }
    List<MenuItem> menuItems;
    /**
     * Get a single order entity as an order object
     * @param id
     * @return
     */
    @Override
    public Optional<MenuItem> get(int id) {
        DB_old db = DB_old.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM HD_MenuItem WHERE Item_Name = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            MenuItem menuItem = null;
            while (rs.next()) {
                menuItem = new MenuItem(rs.getString("Item_Name"), rs.getString("Options").split(","), rs.getInt("Price"));
            }
            return Optional.ofNullable(menuItem);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Get all order entities as a List
     * @return
     */
    @Override
    public List<MenuItem> getAll() {
        DB_old db = DB_old.getInstance();
        ResultSet rs = null;
        menuItems = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HD_MenuItem";
            rs = db.executeQuery(sql);
            MenuItem menuItem = null;
            while (rs.next()) {
                menuItem = new MenuItem(rs.getString("Item_Name"), rs.getString("Options").split(","), rs.getInt("Price"));
                menuItems.add(menuItem);
            }
            return menuItems;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Insert an order object into order table
     * @param order
     */
    @Override
    public void insert(MenuItem menuItem)
    {
        DB_old db = DB_old.getInstance();
        try {
            String sql = "INSERT INTO HD_MenuItem(Item_Name, Options, Price) VALUES (?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, menuItem.getItemName());
            stmt.setString(2, String.join(",", menuItem.getOptions()));
            stmt.setInt(3, menuItem.getPrice());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new order was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Update an order entity in database if it exists using an order object
     * @param order
     */
    @Override
    public void update(MenuItem menuItem) {
        DB_old db = DB_old.getInstance();
        try {
            String sql = "UPDATE HD_MenuItem SET Item_Name=?, Options=?, Price=? WHERE Item_Name=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, menuItem.getItemName());
            stmt.setString(2, String.join(",", menuItem.getOptions()));
            stmt.setInt(3, menuItem.getPrice());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing menu item was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Delete an order from order table if the entity exists
     * @param order
     */
    @Override
    public void delete(MenuItem menuItem) {
        DB_old db = DB_old.getInstance();
        try {
            String sql = "DELETE FROM HD_MenuItem WHERE Item_Name = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, menuItem.getItemName());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A menu item was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Get all column names in a list array
     * @return
     */
    @Override
    public List<String> getColumnNames() {
        DB_old db = DB_old.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HD_Order WHERE Order_ID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
}
