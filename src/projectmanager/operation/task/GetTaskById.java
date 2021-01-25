/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.operation.task;

import projectmanager.domain.Task;
import projectmanager.operation.AbstractGenericOperation;

/**
 *
 * @author Ema
 */
public class GetTaskById extends AbstractGenericOperation{
    
    Task task;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Task)) {
            throw new Exception("Invalid task data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        task = (Task) repository.getById((Task) param);
    }

    public Task getTask() {
        return task;
    }
    
}
