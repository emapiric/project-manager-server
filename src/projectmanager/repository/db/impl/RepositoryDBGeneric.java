/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import projectmanager.domain.GenericEntity;
import projectmanager.repository.db.DBConnectionFactory;
import projectmanager.repository.db.DBRepository;

public class RepositoryDBGeneric implements DBRepository<GenericEntity> {

    @Override
    public void add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                int id = rsKey.getInt(1);
                entity.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(GenericEntity param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericEntity getById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GenericEntity> getAll(Object param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
