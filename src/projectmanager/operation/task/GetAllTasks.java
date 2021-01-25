/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.operation.task;

import java.util.List;
import projectmanager.domain.Task;
import projectmanager.operation.AbstractGenericOperation;

/**
 *
 * @author Ema
 */
public class GetAllTasks extends AbstractGenericOperation{
    
    List<Task> tasks;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Task)) {
            throw new Exception("Invalid task data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       tasks = repository.getAll((Task) param);
    }

    public List<Task> getTasks() {
        return tasks;
    }
    
}
