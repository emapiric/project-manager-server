/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.operation.user;

import java.util.List;
import projectmanager.domain.User;
import projectmanager.operation.AbstractGenericOperation;

/**
 *
 * @author Ema
 */
public class GetAllUsers extends AbstractGenericOperation {
    
    List<User> users;

    @Override
    protected void preconditions(Object param) throws Exception {
         if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid user data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        users = repository.getAll((User) param);
    }

    public List<User> getUsers() {
        return users;
    }
    
}
