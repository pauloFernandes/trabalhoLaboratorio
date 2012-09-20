/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ClienteEntity;
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
public class DaoCliente extends AbstractDao implements CrmIDao{

    public static final String[] PKS = {"Codcli"};
    
    public DaoCliente() {
        super("dao.DaoCliente", DaoCliente.PKS);
    }

    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        ClienteEntity entity = new ClienteEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodcli(resultSet.getInt(1));
                entity.setCodemp(resultSet.getInt(2));
                entity.setNuminsjur(resultSet.getString(3));
                entity.setNomfan(resultSet.getString(4));
                entity.setRazsoc(resultSet.getString(5));
                entity.setTelemp(resultSet.getString(6));
                entity.setCelemp(resultSet.getString(7));
                entity.setDesend(resultSet.getString(8));
                entity.setCodmun(resultSet.getInt(9));
                entity.setCodest(resultSet.getInt(10));
                entity.setCodpai(resultSet.getInt(11));
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
                ClienteEntity entity = new ClienteEntity();
                entity.setCodcli(resultSet.getInt(1));
                entity.setCodemp(resultSet.getInt(2));
                entity.setNuminsjur(resultSet.getString(3));
                entity.setNomfan(resultSet.getString(4));
                entity.setRazsoc(resultSet.getString(5));
                entity.setTelemp(resultSet.getString(6));
                entity.setCelemp(resultSet.getString(7));
                entity.setDesend(resultSet.getString(8));
                entity.setCodmun(resultSet.getInt(9));
                entity.setCodest(resultSet.getInt(10));
                entity.setCodpai(resultSet.getInt(11));
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
                ClienteEntity entity = new ClienteEntity();
                entity.setCodcli(resultSet.getInt(1));
                entity.setCodemp(resultSet.getInt(2));
                entity.setNuminsjur(resultSet.getString(3));
                entity.setNomfan(resultSet.getString(4));
                entity.setRazsoc(resultSet.getString(5));
                entity.setTelemp(resultSet.getString(6));
                entity.setCelemp(resultSet.getString(7));
                entity.setDesend(resultSet.getString(8));
                entity.setCodmun(resultSet.getInt(9));
                entity.setCodest(resultSet.getInt(10));
                entity.setCodpai(resultSet.getInt(11));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
    
    
}
