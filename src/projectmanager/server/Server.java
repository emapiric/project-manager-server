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
            try {
                switch (request.getOperation()) {
                    case LOGIN:
                        User user = (User) request.getArgument();
                        response.setResult(Controller.getInstance().login(user.getUsername(), user.getPassword()));
                        break;
//                    case GET_ALL_MANUFACTURERS:
//                        response.setResult(Controller.getInstance().getAllManufactures());
//                        break;
//                    case GET_ALL_PRODUCTS:
//                        response.setResult(Controller.getInstance().getAllProducts());
//                        break;
//                    case ADD_PRODUCT:
//                        Product productInsert = (Product) request.getArgument();
//                        Controller.getInstance().addProduct(productInsert);
//                        break;
//                    case EDIT_PRODUCT:
//                        Product productEdit = (Product) request.getArgument();
//                        Controller.getInstance().editProduct(productEdit);
//                        break;
//                    case DELETE_PRODUCT:
//                        Product productDelete = (Product) request.getArgument();
//                        Controller.getInstance().deleteProduct(productDelete);
//                        break;
//                    case ADD_INVOICE:
//                        Invoice invoiceInsert = (Invoice) request.getArgument();
//                        Controller.getInstance().addInvoice(invoiceInsert);
//                        response.setResult(invoiceInsert);
//                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setException(e);
            }
            sender.send(response);
        }
    }
}
