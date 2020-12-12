/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import projectmanager.controller.Controller;
import projectmanager.domain.Project;
import projectmanager.domain.User;
import projectmanager.repository.db.DBConnectionFactory;
import projectmanager.repository.db.DBRepository;

/**
 *
 * @author Ema
 */
public class DBRepositoryProject implements DBRepository<Project>{

    @Override
    public List<Project> getAll() {
        try {
            String sql = "SELECT * FROM project INNER JOIN user ON (project.userId = user.id)";
            List<Project> projects = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Project project = new Project();
                project.setId(rs.getInt("project.id"));
                project.setName(rs.getString("project.name"));
                project.setDescription(rs.getString("project.description"));
                User user = new User();
                user.setId(rs.getInt("user.id"));
                user.setUsername(rs.getString("user.username"));
                user.setPassword(rs.getString("user.password"));
                user.setEmail(rs.getString("user.email"));
                user.setFirstname(rs.getString("user.firstname"));
                user.setLastname(rs.getString("user.lastname"));
                project.setOwner(user);
                projects.add(project);
            }
            rs.close();
            statement.close();
            return projects;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(Project project) throws Exception {
        try {
            String sql = "INSERT INTO project (name, description, userId) VALUES (?,?,?)";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setInt(3, project.getOwner().getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Project can't be saved");
        }
    }

    @Override
    public void delete(Project project) throws Exception {
        try {
            String sql = "DELETE FROM project WHERE id = ?";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, project.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Project can't be deleted");
        }
    }

    @Override
    public Project getById(int id) throws Exception {
        try {
            String sql = "SELECT * FROM project INNER JOIN user ON (project.userId = user.id) WHERE project.id = ?";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            Project project = new Project();
            if (rs.next()){
                project.setId(rs.getInt("project.id"));
                project.setName(rs.getString("project.name"));
                project.setDescription(rs.getString("project.description"));
                User user = new User();
                user.setId(rs.getInt("user.id"));
                user.setUsername(rs.getString("user.username"));
                user.setPassword(rs.getString("user.password"));
                user.setEmail(rs.getString("user.email"));
                user.setFirstname(rs.getString("user.firstname"));
                user.setLastname(rs.getString("user.lastname"));
                project.setOwner(user);
            }
            rs.close();
            statement.close();
            return project;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Project doesn't exist.");
        }
    }

    @Override
    public void edit(Project project) throws Exception {
        try {
            String sql = "UPDATE project SET name=?, description=? WHERE id = ?";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setInt(3, project.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Project can't be saved");
        }
    }

}
