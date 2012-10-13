/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ConvidadoEntity;
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
public class DaoConvidado extends AbstractDao implements CrmIDao{

    public static final String[] PKS = {"Codcon", "Codfun"};
    
    public DaoConvidado() {
        super("dao.DaoConvidado", DaoConvidado.PKS);
    }

    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        ConvidadoEntity entity = new ConvidadoEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodcon(resultSet.getInt("codcon"));
                entity.setCodati(resultSet.getInt("codati"));
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setIdconace(resultSet.getString("idconace"));
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
                ConvidadoEntity entity = new ConvidadoEntity();
                entity.setCodcon(resultSet.getInt("codcon"));
                entity.setCodati(resultSet.getInt("codati"));
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setIdconace(resultSet.getString("idconace"));
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
                ConvidadoEntity entity = new ConvidadoEntity();
                entity.setCodcon(resultSet.getInt("codcon"));
                entity.setCodati(resultSet.getInt("codati"));
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setIdconace(resultSet.getString("idconace"));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }    
    
    
}
