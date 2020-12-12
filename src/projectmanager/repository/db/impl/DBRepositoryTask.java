/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projectmanager.domain.Task;
import projectmanager.repository.db.DBConnectionFactory;
import projectmanager.repository.db.DBRepository;

/**
 *
 * @author Ema
 */
public class DBRepositoryTask implements DBRepository<Task>{

    @Override
    public List<Task> getAll() {
        try {
            String sql = "SELECT * FROM task";
            List<Task> tasks = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                tasks.add(task);
            }
            rs.close();
            statement.close();
            return tasks;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(Task param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Task param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Task param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Task getById(int id) throws Exception {
        try {
            String sql = "SELECT * FROM task WHERE id = ?";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Task task = new Task();
            if (rs.next()) {
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
            }
            rs.close();
            statement.close();
            return task;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Task doesn't exist");
        }
    }
    
}
