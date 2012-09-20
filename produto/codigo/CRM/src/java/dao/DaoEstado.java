/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.EstadoEntity;
import entity.IEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PauloHenrique
 */
public class DaoEstado extends AbstractDao implements CrmIDao {

    public static final String[] PKS = {"Codest"};
    
    public DaoEstado() {
        super("dao.DaoEstado", DaoEstado.PKS);
    }
    
    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        EstadoEntity entity = new EstadoEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodest(resultSet.getInt(1));
                entity.setCodpai(resultSet.getInt(2));
                entity.setNomest(resultSet.getString(3));
            }
            entity.setNewRegister(false);
        }
        catch (SQLException e) {
            e.getMessage();
        }
        
        return entity;
    }

    @Override
    public List<IEntity> obterTodasEntidades() {
        List<IEntity> lista = new LinkedList<IEntity>();
        ResultSet resultSet = super.getAllEntities();
        
        try {
            while(resultSet.next()) {
                EstadoEntity entity = new EstadoEntity();
                entity.setCodest(resultSet.getInt(1));
                entity.setCodpai(resultSet.getInt(2));
                entity.setNomest(resultSet.getString(3));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }

    @Override
    public List<IEntity> obterEntidadeCondicaoWhere(String whereClause) {
        List<IEntity> lista = new LinkedList<IEntity>();
        ResultSet resultSet = super.getEntityByWhere(whereClause);
        
        try {
            while(resultSet.next()) {
                EstadoEntity entity = new EstadoEntity();
                entity.setCodest(resultSet.getInt(1));
                entity.setCodpai(resultSet.getInt(2));
                entity.setNomest(resultSet.getString(3));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
