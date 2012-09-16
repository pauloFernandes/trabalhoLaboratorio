/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.AtividadeEntity;
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
public class DaoAtividade extends AbstractDao implements CrmIDao{

    public static final String[] PKS = {"Codativ"};
    
    public DaoAtividade() {
        super("dao.DaoAtividade", DaoPais.PKS);
    }

    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        AtividadeEntity entity = new AtividadeEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodati(resultSet.getInt(1));
                entity.setNomati(resultSet.getString(2));
                entity.setDesati(resultSet.getString(3));
                entity.setDatini(resultSet.getDate(4));
                entity.setDatfin(resultSet.getDate(5));
                entity.setCodtipati(resultSet.getInt(6));
                entity.setObsandati(resultSet.getString(7));
                entity.setStatus(resultSet.getString(8));
                entity.setCodfunres(resultSet.getInt(9));
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
                AtividadeEntity entity = new AtividadeEntity();
                entity.setCodati(resultSet.getInt(1));
                entity.setNomati(resultSet.getString(2));
                entity.setDesati(resultSet.getString(3));
                entity.setDatini(resultSet.getDate(4));
                entity.setDatfin(resultSet.getDate(5));
                entity.setCodtipati(resultSet.getInt(6));
                entity.setObsandati(resultSet.getString(7));
                entity.setStatus(resultSet.getString(8));
                entity.setCodfunres(resultSet.getInt(9));
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
                AtividadeEntity entity = new AtividadeEntity();
                entity.setCodati(resultSet.getInt(1));
                entity.setNomati(resultSet.getString(2));
                entity.setDesati(resultSet.getString(3));
                entity.setDatini(resultSet.getDate(4));
                entity.setDatfin(resultSet.getDate(5));
                entity.setCodtipati(resultSet.getInt(6));
                entity.setObsandati(resultSet.getString(7));
                entity.setStatus(resultSet.getString(8));
                entity.setCodfunres(resultSet.getInt(9));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
    


}
