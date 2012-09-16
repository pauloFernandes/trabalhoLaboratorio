/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.IEntity;
import entity.Tipo_AtividadeEntity;
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
public class DaoTipo_Atividade extends AbstractDao implements CrmIDao {

    public static final String[] PKS = {"Codtipativ"};
    
    public DaoTipo_Atividade() {
        super("dao.DaoTipo_Atividade", DaoTipo_Atividade.PKS);
    }
    
    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        Tipo_AtividadeEntity entity = new Tipo_AtividadeEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodtipati(resultSet.getInt(1));
                entity.setNomtipati(resultSet.getString(2));
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
                Tipo_AtividadeEntity entity = new Tipo_AtividadeEntity();
                entity.setCodtipati(resultSet.getInt(1));
                entity.setNomtipati(resultSet.getString(2));
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
                Tipo_AtividadeEntity entity = new Tipo_AtividadeEntity();
                entity.setCodtipati(resultSet.getInt(1));
                entity.setNomtipati(resultSet.getString(2));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
