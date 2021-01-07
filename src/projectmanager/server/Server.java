/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.server;

import java.net.ServerSocket;
import java.net.Socket;
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
 * @author EMA
 */
public class Server {
     public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Waiting for connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Connected!");
            handleClient(socket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void handleClient(Socket socket) throws Exception {
        Sender sender = new Sender(socket);
        Receiver receiver = new Receiver(socket);

        while (true) {
            Request request = (Request) receiver.receive();
            Response response = new Response();
            User user;
            Project project;
            ProjectTask projectTask;
            Task task;
            int id;
            try {
                switch (request.getOperation()) {
                    case LOGIN:
                        user = (User) request.getArgument();
                        response.setResult(Controller.getInstance().login(user.getUsername(), user.getPassword()));
                        break;
                    case GET_ALL_USERS:
                        response.setResult(Controller.getInstance().getAllUsers());
                        break;
                    case GET_USER_BY_ID:
                        id = (int) request.getArgument();
                        response.setResult(Controller.getInstance().getUserById(id));
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
                    case GET_TASK_BY_ID:
                        id = (int) request.getArgument();
                        response.setResult(Controller.getInstance().getTaskById(id));
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setException(e);
            }
            sender.send(response);
        }
    }
}
