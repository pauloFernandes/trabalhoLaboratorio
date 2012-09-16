/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ContatoEntity;
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
public class DaoContato extends AbstractDao implements CrmIDao{

    public static final String[] PKS = {"Codcon"};
    
    public DaoContato() {
        super("dao.DaoContato", DaoPais.PKS);
    }

    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        ContatoEntity entity = new ContatoEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodcon(resultSet.getInt(1));
                entity.setCodcli(resultSet.getInt(2));
                entity.setNomcon(resultSet.getString(3));
                entity.setDatnas(resultSet.getDate(4));
                entity.setTelcon(resultSet.getString(5));
                entity.setCelcon(resultSet.getString(6));
                entity.setMailcon(resultSet.getString(7));
                
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
                ContatoEntity entity = new ContatoEntity();
                entity.setCodcon(resultSet.getInt(1));
                entity.setCodcli(resultSet.getInt(2));
                entity.setNomcon(resultSet.getString(3));
                entity.setDatnas(resultSet.getDate(4));
                entity.setTelcon(resultSet.getString(5));
                entity.setCelcon(resultSet.getString(6));
                entity.setMailcon(resultSet.getString(7));
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
                ContatoEntity entity = new ContatoEntity();
                entity.setCodcon(resultSet.getInt(1));
                entity.setCodcli(resultSet.getInt(2));
                entity.setNomcon(resultSet.getString(3));
                entity.setDatnas(resultSet.getDate(4));
                entity.setTelcon(resultSet.getString(5));
                entity.setCelcon(resultSet.getString(6));
                entity.setMailcon(resultSet.getString(7));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
    
    
}
