/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.operation.project;

import projectmanager.domain.Project;
import projectmanager.operation.AbstractGenericOperation;

/**
 *
 * @author Ema
 */
public class EditProject extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Project)) {
            throw new Exception("Invalid project data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Project) param);
    }
    
}
