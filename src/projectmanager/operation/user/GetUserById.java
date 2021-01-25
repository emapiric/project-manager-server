/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.operation.user;

import projectmanager.domain.User;
import projectmanager.operation.AbstractGenericOperation;

/**
 *
 * @author Ema
 */
public class GetUserById extends AbstractGenericOperation {
    User user;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid user data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        user = (User) repository.getById((User) param);
    }

    public User getUser() {
        return user;
    }
    
}
