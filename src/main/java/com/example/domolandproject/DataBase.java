package com.example.domolandproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    private static Connection connect = null;
    public static Connection connectDb(){
        if (connect != null) {
            return connect;
        }
       try{
           Class.forName("com.mysql.jdbc.Driver");
           // root is the default username
           // empty is the default password
           Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/domoland", "root","");
           return connect;

       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
       // now let's open our xampp
    }
}
