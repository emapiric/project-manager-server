/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.controller;

import java.util.List;
import projectmanager.domain.Project;
import projectmanager.domain.ProjectTask;
import projectmanager.domain.Task;
import projectmanager.domain.User;
import projectmanager.repository.Repository;
import projectmanager.repository.db.DBRepository;
import projectmanager.repository.db.impl.DBRepositoryProject;
import projectmanager.repository.db.impl.DBRepositoryProjectTask;
import projectmanager.repository.db.impl.DBRepositoryTask;
import projectmanager.repository.db.impl.DBRepositoryUser;

/**
 *
 * @author EMA
 */
public class Controller {
    private static Controller controller;
    private final Repository<User> repositoryUser;
    private final Repository<Project> repositoryProject;
    private final Repository<ProjectTask> repositoryProjectTask;
    private final Repository<Task> repositoryTask;

    public Controller() {
        this.repositoryUser = new DBRepositoryUser();
        this.repositoryProject = new DBRepositoryProject();
        this.repositoryProjectTask = new DBRepositoryProjectTask();
        this.repositoryTask = new DBRepositoryTask();
    }
    
    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }
    
    public User login(String username, String password) throws Exception {
        List<User> users = repositoryUser.getAll();
        for (User user: users) {
            if (user.getUsername().equals(username)&&user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new Exception("Unknown user");
    }
    
    public List<User> getAllUsers() throws Exception{
        List<User> users=null;
        ((DBRepository)repositoryUser).connect();
        try{
            users = repositoryUser.getAll();
            ((DBRepository)repositoryUser).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryUser).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryUser).disconnect();
        }
        return users;
    }
    
    public User getUserById(int id) throws Exception {
        User user = repositoryUser.getById(id);
        if (user != null) return user;
        throw new Exception("Unknown user");
    }

    public List<Project> getAllProjects() throws Exception{
        List<Project> projects=null;
        ((DBRepository)repositoryProject).connect();
        try{
            projects = repositoryProject.getAll();
            ((DBRepository)repositoryProject).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProject).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProject).disconnect();
        }
        return projects;
    }
        
    public void addProject(Project project) throws Exception {
        ((DBRepository)repositoryProject).connect();
        try{
            repositoryProject.add(project);
            ((DBRepository)repositoryProject).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProject).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProject).disconnect();
        }
    }
        
    public void editProject(Project project) throws Exception {
        ((DBRepository)repositoryProject).connect();
        try{
            ((DBRepository)repositoryProject).edit(project);
            ((DBRepository)repositoryProject).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProject).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProject).disconnect();
        }
    }
    
    public void deleteProject(Project project) throws Exception {
       ((DBRepository)repositoryProject).connect();
        try{
            repositoryProject.delete(project);
            ((DBRepository)repositoryProject).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProject).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProject).disconnect();
        }
    }


    public List<ProjectTask> getAllProjectTasks() throws Exception{
        List<ProjectTask> projectTasks=null;
        ((DBRepository)repositoryProjectTask).connect();
        try{
            projectTasks = repositoryProjectTask.getAll();
            ((DBRepository)repositoryProjectTask).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProjectTask).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProjectTask).disconnect();
        }
        return projectTasks;
    }


    public void addProjectTask(ProjectTask projectTask) throws Exception{
        ((DBRepository)repositoryProjectTask).connect();
        try{
            repositoryProjectTask.add(projectTask);
            ((DBRepository)repositoryProjectTask).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProjectTask).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProjectTask).disconnect();
        }
    }

    public void editProjectTask(ProjectTask projectTask) throws Exception {
        ((DBRepository)repositoryProjectTask).connect();
        try{
            ((DBRepository)repositoryProjectTask).edit(projectTask);
            ((DBRepository)repositoryProjectTask).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProjectTask).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProjectTask).disconnect();
        }
    }
    
    public void deleteProjectTask(ProjectTask projectTask) throws Exception{
        ((DBRepository)repositoryProjectTask).connect();
        try{
            repositoryProjectTask.delete(projectTask);
            ((DBRepository)repositoryProjectTask).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProjectTask).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProjectTask).disconnect();
        }
    }

    public List<Task> getAllTasks() throws Exception{
         List<Task> tasks=null;
        ((DBRepository)repositoryProjectTask).connect();
        try{
            tasks = repositoryTask.getAll();
            ((DBRepository)repositoryProjectTask).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryProjectTask).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryProjectTask).disconnect();
        }
        return tasks;
    }
    
    public Task getTaskById(int id) throws Exception{
        Task task = repositoryTask.getById(id);
        if (task != null) return task;
        throw new Exception("Unknown task");
    }
    
}
