/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.main;

import projectmanager.server.Server;


/**
 *
 * @author EMA
 */
public class Main {
    public static void main(String[] args) {
        new Server().startServer();
    }
}
