/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.repository;

import java.util.List;

/**
 *
 * @author Ema
 */
public interface Repository<T> {
    List<T> getAll();
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;
    T getById(T param) throws Exception;
    public List<T> getAll(T param) throws Exception;
}
