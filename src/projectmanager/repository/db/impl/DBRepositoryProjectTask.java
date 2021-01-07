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
import java.util.Date;
import java.util.List;
import projectmanager.controller.Controller;
import projectmanager.domain.Project;
import projectmanager.domain.ProjectTask;
import projectmanager.domain.Status;
import projectmanager.domain.Task;
import projectmanager.domain.User;
import projectmanager.repository.db.DBConnectionFactory;
import projectmanager.repository.db.DBRepository;

/**
 *
 * @author Ema
 */
public class DBRepositoryProjectTask implements DBRepository<ProjectTask>{

    public List<ProjectTask> getAll(Object param) {
         try {
            Project project = (Project) param;
            String sql = "SELECT * FROM project_task WHERE projectId=?";
            List<ProjectTask> projectTasks = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, project.getId());
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()){
                ProjectTask projectTask = new ProjectTask();
                projectTask.setId(rs.getInt("id"));
                projectTask.setProject(project);
                projectTask.setCreatedOn(rs.getDate("createdOn"));
                projectTask.setDescription(rs.getString("description"));
                Task task = Controller.getInstance().getTaskById(rs.getInt("taskId"));
                projectTask.setTask(task);
                User assignee = Controller.getInstance().getUserById(rs.getInt("assigneeId"));
                projectTask.setAssignee(assignee);
                projectTask.setStatus(Status.values()[rs.getInt("statusId")]);
                User author = Controller.getInstance().getUserById(rs.getInt("authorId"));
                projectTask.setAuthor(author);
                projectTasks.add(projectTask);
            }
            rs.close();
            statement.close();
            return projectTasks;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(ProjectTask projectTask) throws Exception {
        try {
            String sql = "INSERT INTO project_task (projectId, createdOn, description, taskId, assigneeProjectId, assigneeId, statusId, authorId) VALUES (?,?,?,?,?,?,?,?)";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, projectTask.getProject().getId());
            statement.setDate(2, new java.sql.Date(new Date().getTime()));
            statement.setString(3,projectTask.getDescription());
            statement.setInt(4,projectTask.getTask().getId());
            statement.setInt(5,projectTask.getProject().getId());
            statement.setInt(6,projectTask.getAssignee().getId());
            statement.setInt(7, projectTask.getStatus().ordinal());
            statement.setInt(8, projectTask.getAuthor().getId());
            System.out.println(statement.toString());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Project task can't be saved");
        }
    }

    @Override
    public void edit(ProjectTask projectTask) throws Exception {
        try {
            String sql = "UPDATE project_task SET description=?, taskId = ?, assigneeId=?, statusId = ? WHERE id = ?";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,projectTask.getDescription());
            statement.setInt(2,projectTask.getTask().getId());
            statement.setInt(3,projectTask.getAssignee().getId());
            statement.setInt(4, projectTask.getStatus().ordinal());
            statement.setInt(5, projectTask.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Project task can't be saved");
        }
    }

    @Override
    public void delete(ProjectTask projectTask) throws Exception {
        try {
            String sql = "DELETE FROM project_task WHERE id = ?";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, projectTask.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Project task can't be deleted");
        }
    }

    @Override
    public ProjectTask getById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProjectTask> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
