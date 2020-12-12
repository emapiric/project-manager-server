/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.repository.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import projectmanager.domain.User;
import projectmanager.repository.db.DBConnectionFactory;
import projectmanager.repository.db.DBRepository;

/**
 *
 * @author EMA
 */
public class DBRepositoryUser implements DBRepository<User>{

    @Override
    public List<User> getAll() {
        try {
            String sql = "SELECT * FROM user";  
            List<User> users = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            rs.close();
            statement.close();
            return users;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
       
    }

    @Override
    public void add(User param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getById(int id) throws Exception {
        try {
            String sql = "SELECT * FROM user WHERE id = ?";  
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            User user = new User();
            if(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
            rs.close();
            statement.close();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("User doesn't exist");
        }
    }

    @Override
    public void edit(User param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
