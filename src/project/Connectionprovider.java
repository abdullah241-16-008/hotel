/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author abdullah
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Connectionprovider {
    public static Connection getcon()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","241-16-008");
           return con;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
}
