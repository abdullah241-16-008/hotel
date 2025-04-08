/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author abdullah
 */
public class Tables {

    /**
     * Checks if a table exists in the database
     * @param con Database connection
     * @param tableName Name of the table to check
     * @return true if table exists, false otherwise
     */
    private static boolean tableExists(Connection con, String tableName) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
        return resultSet.next();
    }

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        try {
            con = Connectionprovider.getcon();
            st = con.createStatement();
            
            // Check if users table exists
            boolean usersTableExists = tableExists(con, "users");
            
            if (!usersTableExists) {
                // Create users table if it doesn't exist
                st.executeUpdate("CREATE TABLE users(name varchar(200), address varchar(200), " +
                                "phone varchar(20), email varchar(200), gender varchar(30), " +
                                "person varchar(50), id varchar(50), password varchar(50), status varchar(20))");
                JOptionPane.showMessageDialog(null, "Users table created successfully.");
            
            } 
            else {
                // If users table exists, create rooms table
                try {
                    boolean roomsTableExists = tableExists(con, "rooms");
                    
                    if (!roomsTableExists) {
                        st.executeUpdate("CREATE TABLE rooms(roomno varchar(20), roomtype varchar(20), " +
                                        "bed varchar(20), price varchar(20), status varchar(20))");

                        JOptionPane.showMessageDialog(null, "Rooms table created successfully.");
                    } else {
                        // Check if customer table exists
                        boolean customerTableExists = tableExists(con, "customer");
                        
                        if (!customerTableExists) {
                            st.executeUpdate("CREATE TABLE customer(id int, email varchar(200), checkIn varchar(20), " +
                                           "roomno varchar(20), bed varchar(20), roomType varchar(20), " +
                                           "price varchar(20), numberOfDaysStay int, totalAmount varchar(100), " +
                                           "checkOut varchar(50))");
                                           
                            JOptionPane.showMessageDialog(null, "Customer table created successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "All tables already exist.");
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error creating rooms table: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            }
        }
    }
}