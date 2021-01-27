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
import projectmanager.operation.AbstractGenericOperation;
import projectmanager.operation.project.AddProject;
import projectmanager.operation.project.DeleteProject;
import projectmanager.operation.project.EditProject;
import projectmanager.operation.project.GetAllProjects;
import projectmanager.operation.projecttask.AddProjectTask;
import projectmanager.operation.projecttask.DeleteProjectTask;
import projectmanager.operation.projecttask.EditProjectTask;
import projectmanager.operation.projecttask.GetAllProjectTasks;
import projectmanager.operation.projecttask.GetProjectTaskById;
import projectmanager.operation.task.GetAllTasks;
import projectmanager.operation.user.GetAllUsers;

/**
 *
 * @author EMA
 */
public class Controller {
    private static Controller controller;

    public Controller() {
    }
    
    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }
    
    public User login(String username, String password) throws Exception {
        List<User> users = getAllUsers();
        System.out.println(users);
        for (User user: users) {
            if (user.getUsername().equals(username)&&user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new Exception("Unknown user");
    }
    
    public List<User> getAllUsers() throws Exception{
        AbstractGenericOperation operation = new GetAllUsers();
        operation.execute(new User());
        return ((GetAllUsers)operation).getUsers();
    }

    public List<Project> getAllProjects() throws Exception{
        AbstractGenericOperation operation = new GetAllProjects();
        operation.execute(new Project());
        return ((GetAllProjects)operation).getProjects();
    }
        
    public void addProject(Project project) throws Exception {
        AbstractGenericOperation operation = new AddProject();
        operation.execute(project);
    }
        
    public void editProject(Project project) throws Exception {
        AbstractGenericOperation operation = new EditProject();
        operation.execute(project);
    }
    
    public void deleteProject(Project project) throws Exception {
       AbstractGenericOperation operation = new DeleteProject();
        operation.execute(project);
    }


    public List<ProjectTask> getAllProjectTasks(Project project) throws Exception{
        AbstractGenericOperation operation = new GetAllProjectTasks();
        operation.execute(new ProjectTask(project));
        return ((GetAllProjectTasks)operation).getProjectTasks();
    }


    public void addProjectTask(ProjectTask projectTask) throws Exception{
        AbstractGenericOperation operation = new AddProjectTask();
        operation.execute(projectTask);
    }

    public void editProjectTask(ProjectTask projectTask) throws Exception {
        AbstractGenericOperation operation = new EditProjectTask();
        operation.execute(projectTask);
    }
    
    public void deleteProjectTask(ProjectTask projectTask) throws Exception{
        AbstractGenericOperation operation = new DeleteProjectTask();
        operation.execute(projectTask);
    }

    public List<Task> getAllTasks() throws Exception{
        AbstractGenericOperation operation = new GetAllTasks();
        operation.execute(new Task());
        return ((GetAllTasks)operation).getTasks();
    }
    
    public ProjectTask getProjectTaskById(ProjectTask projectTask) throws Exception{
        AbstractGenericOperation operation = new GetProjectTaskById();
        operation.execute(projectTask);
        return ((GetProjectTaskById)operation).getProjectTask();
    }
    
}
