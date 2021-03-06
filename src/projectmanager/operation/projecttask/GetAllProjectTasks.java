/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.operation.projecttask;

import java.util.List;
import projectmanager.domain.ProjectTask;
import projectmanager.operation.AbstractGenericOperation;

/**
 *
 * @author Ema
 */
public class GetAllProjectTasks extends AbstractGenericOperation{
    
    List<ProjectTask> projectTasks;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof ProjectTask)) {
            throw new Exception("Invalid project task data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       projectTasks = repository.getAll((ProjectTask) param);
    }

    public List<ProjectTask> getProjectTasks() {
        return projectTasks;
    }
    
}
