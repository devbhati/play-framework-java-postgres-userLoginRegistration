package Utility;

import models.*;
import java.sql.*;

public class DBUtility {

    private static Connection conn;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_database?user=postgres&password=root");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String insertIntoTable(User user){
        if(getUserByEmail(user.getEmail())==null){
            try {
                PreparedStatement statement = conn.prepareStatement("INSERT INTO userinfo(email,firstname,lastname,password) VALUES('" +
                        user.getEmail()+"','" + user.getFirstName()+"','"+user.getLastName()+"','"+user.getPassword()+"')");
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "SUCCESS";
        }else
            return "User Exists";
    }

    public static boolean getFromTable(Login login){
        if(login!=null && login.getEmail()!=null) {
            User loggedInUser = getUserByEmail(login.getEmail());
            if(loggedInUser!=null) {
                if(loggedInUser.getPassword().trim().equals(login.getPassword().trim())) return true;
            } else return false;
        }
        return false;
    }

    public static User getUserByEmail(String email){
        User user = null;
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM userinfo where email = '"+email+"'");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
