/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.threads;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmanager.communication.Receiver;
import projectmanager.communication.Request;
import projectmanager.communication.Response;
import projectmanager.communication.Sender;
import projectmanager.controller.Controller;
import projectmanager.domain.Project;
import projectmanager.domain.ProjectTask;
import projectmanager.domain.Task;
import projectmanager.domain.User;

/**
 *
 * @author Korisnik
 */
public class ProcessRequests extends Thread {
    
    Socket socket;
    Sender sender;
    Receiver receiver;
    ServerSocket serverSocket;
    boolean run;
    
    public ProcessRequests(Socket socket, ServerSocket serverSocket) {
        this.socket = socket;
        sender=new Sender(socket);
        receiver=new Receiver(socket);
        this.serverSocket = serverSocket;
        this.run = true;
    }

    @Override
    public void run() {
        while (run) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                User user;
                Project project;
                ProjectTask projectTask;
                Task task;
                int id;
                try {
                if (serverSocket.isClosed()) {
                    System.out.println("server socket is closed");
                    run = false;
                    throw new SocketException("Server socket closed");
                }
                    switch (request.getOperation()) {
                        case LOGIN:
                            user = (User) request.getArgument();
                            response.setResult(Controller.getInstance().login(user.getUsername(), user.getPassword()));
                            break;
                        case GET_ALL_USERS:
                            response.setResult(Controller.getInstance().getAllUsers());
                            break;
                        case GET_ALL_PROJECTS:
                            response.setResult(Controller.getInstance().getAllProjects());
                            break;
                        case ADD_PROJECT:
                            project = (Project) request.getArgument();
                            Controller.getInstance().addProject(project);
                            response.setResult(project);
                            break;
                        case EDIT_PROJECT:
                            project = (Project) request.getArgument();
                            Controller.getInstance().editProject(project);
                            break;
                        case DELETE_PROJECT:
                            project = (Project) request.getArgument();
                            Controller.getInstance().deleteProject(project);
                            break;
                        case GET_ALL_PROJECT_TASKS:
                            project = (Project) request.getArgument();
                            response.setResult(Controller.getInstance().getAllProjectTasks(project));
                            break;
                        case ADD_PROJECT_TASK:
                            projectTask = (ProjectTask) request.getArgument();
                            Controller.getInstance().addProjectTask(projectTask);
                            response.setResult(projectTask);
                            break;
                        case EDIT_PROJECT_TASK:
                            projectTask = (ProjectTask) request.getArgument();
                            Controller.getInstance().editProjectTask(projectTask);
                            response.setResult(projectTask);
                            break;
                        case DELETE_PROJECT_TASK:
                            projectTask = (ProjectTask) request.getArgument();
                            Controller.getInstance().deleteProjectTask(projectTask);
                            break;
                        case GET_ALL_TASKS:
                            response.setResult(Controller.getInstance().getAllTasks());
                            break;
                        case GET_PROJECT_TASK_BY_ID:
                            projectTask = (ProjectTask) request.getArgument();
                            response.setResult(Controller.getInstance().getProjectTaskById(projectTask));
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                run = false;
                Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
    
    
    
    
    
    
}
