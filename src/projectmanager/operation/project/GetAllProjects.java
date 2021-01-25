/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.operation.project;

import java.util.List;
import projectmanager.domain.Project;
import projectmanager.operation.AbstractGenericOperation;

/**
 *
 * @author Ema
 */
public class GetAllProjects extends AbstractGenericOperation{

    List<Project> projects;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Project)) {
            throw new Exception("Invalid project data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        projects = repository.getAll((Project) param);
    }

    public List<Project> getProjects() {
        return projects;
    }
    
}
