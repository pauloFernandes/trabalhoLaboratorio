/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.FuncionarioEntity;
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
public class DaoFuncionario extends AbstractDao implements CrmIDao {

    public static final String[] PKS = {"Codfun"};
    
    public DaoFuncionario() {
        super("dao.Funcionario", DaoPais.PKS);        
    }
    
    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        FuncionarioEntity entity = new FuncionarioEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodfun(resultSet.getInt(1));
                entity.setCodusu(resultSet.getInt(2));
                entity.setCodemp(resultSet.getInt(3));
                entity.setCodtipfun(resultSet.getInt(4));
                entity.setDatini(resultSet.getDate(5));
                entity.setDatfim(resultSet.getDate(6));
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
                FuncionarioEntity entity = new FuncionarioEntity();
                entity.setCodfun(resultSet.getInt(1));
                entity.setCodusu(resultSet.getInt(2));
                entity.setCodemp(resultSet.getInt(3));
                entity.setCodtipfun(resultSet.getInt(4));
                entity.setDatini(resultSet.getDate(5));
                entity.setDatfim(resultSet.getDate(6));
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
                FuncionarioEntity entity = new FuncionarioEntity();
                entity.setCodfun(resultSet.getInt(1));
                entity.setCodusu(resultSet.getInt(2));
                entity.setCodemp(resultSet.getInt(3));
                entity.setCodtipfun(resultSet.getInt(4));
                entity.setDatini(resultSet.getDate(5));
                entity.setDatfim(resultSet.getDate(6));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
