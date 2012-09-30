/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.IEntity;
import entity.LembreteEntity;
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
public class DaoLembrete extends AbstractDao implements CrmIDao {
    public static final String[] PKS = {"Codfun", "Codati"};
    
    public DaoLembrete() {
        super("dao.Lembrete", DaoLembrete.PKS);        
    }

    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        LembreteEntity entity = new LembreteEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setCodati(resultSet.getInt("codati"));
                entity.setIdtiplem(resultSet.getString("idtiplem"));
                entity.setDatinilem(resultSet.getDate("datinilem"));
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
                LembreteEntity entity = new LembreteEntity();
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setCodati(resultSet.getInt("codati"));
                entity.setIdtiplem(resultSet.getString("idtiplem"));
                entity.setDatinilem(resultSet.getDate("datinilem"));
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
                LembreteEntity entity = new LembreteEntity();
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setCodati(resultSet.getInt("codati"));
                entity.setIdtiplem(resultSet.getString("idtiplem"));
                entity.setDatinilem(resultSet.getDate("datinilem"));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
