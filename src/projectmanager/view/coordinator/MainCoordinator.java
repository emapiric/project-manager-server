/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.view.coordinator;

import java.util.HashMap;
import java.util.Map;
import projectmanager.view.controller.MainController;
import projectmanager.view.form.FrmMain;

/**
 *
 * @author EMA
 */
public class MainCoordinator {
    private static MainCoordinator instance;

    private final MainController mainController;
    

    private MainCoordinator() {
        mainController = new MainController(new FrmMain());
    }

    public static MainCoordinator getInstance() {
        if (instance == null) {
            instance = new MainCoordinator();
        }
        return instance;
    }
     public void openMainForm() {
        mainController.openForm();
    }
}
