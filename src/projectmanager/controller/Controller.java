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
import projectmanager.repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author EMA
 */
public class Controller {
    private static Controller controller;
    private final Repository repositoryGeneric;

    public Controller() {
        this.repositoryGeneric = new RepositoryDBGeneric();
    }
    
    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }
    
    public User login(String username, String password) throws Exception {
        List<User> users = repositoryGeneric.getAll(new User());
        System.out.println(users);
        for (User user: users) {
            if (user.getUsername().equals(username)&&user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new Exception("Unknown user");
    }
    
    public List<User> getAllUsers() throws Exception{
        List<User> users=null;
        ((DBRepository)repositoryGeneric).connect();
        try{
            users = repositoryGeneric.getAll(new User());
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
        return users;
    }
    
    public User getUserById(int id) throws Exception {
        User user = (User) repositoryGeneric.getById(new User(id));
        if (user != null) return user;
        throw new Exception("Unknown user");
    }

    public List<Project> getAllProjects() throws Exception{
        List<Project> projects=null;
        ((DBRepository)repositoryGeneric).connect();
        try{
            projects = repositoryGeneric.getAll(new Project());
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
        return projects;
    }
        
    public void addProject(Project project) throws Exception {
        ((DBRepository)repositoryGeneric).connect();
        try{
            repositoryGeneric.add(project);
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
    }
        
    public void editProject(Project project) throws Exception {
        ((DBRepository)repositoryGeneric).connect();
        try{
            ((DBRepository)repositoryGeneric).edit(project);
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
    }
    
    public void deleteProject(Project project) throws Exception {
       ((DBRepository)repositoryGeneric).connect();
        try{
            repositoryGeneric.delete(project);
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
    }


    public List<ProjectTask> getAllProjectTasks(Project project) throws Exception{
        List<ProjectTask> projectTasks=null;
        ((DBRepository)repositoryGeneric).connect();
        try{
            projectTasks = repositoryGeneric.getAll(new ProjectTask(project));
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
        return projectTasks;
    }


    public void addProjectTask(ProjectTask projectTask) throws Exception{
        ((DBRepository)repositoryGeneric).connect();
        try{
            repositoryGeneric.add(projectTask);
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
    }

    public void editProjectTask(ProjectTask projectTask) throws Exception {
        ((DBRepository)repositoryGeneric).connect();
        try{
            ((DBRepository)repositoryGeneric).edit(projectTask);
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
    }
    
    public void deleteProjectTask(ProjectTask projectTask) throws Exception{
        ((DBRepository)repositoryGeneric).connect();
        try{
            repositoryGeneric.delete(projectTask);
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
    }

    public List<Task> getAllTasks() throws Exception{
         List<Task> tasks=null;
        ((DBRepository)repositoryGeneric).connect();
        try{
            tasks = repositoryGeneric.getAll(new Task());
            ((DBRepository)repositoryGeneric).commit();
        }catch(Exception e){
            e.printStackTrace();
            ((DBRepository)repositoryGeneric).rollback();
            throw e;
        }finally{
            ((DBRepository)repositoryGeneric).disconnect();
        }
        return tasks;
    }
    
    public Task getTaskById(int id) throws Exception{
        Task task = (Task) repositoryGeneric.getById(new Task(id));
        if (task != null) return task;
        throw new Exception("Unknown task");
    }
    
}
