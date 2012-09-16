/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Fun_ResponsavelEntity;
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
public class DaoFun_Responsavel extends AbstractDao implements CrmIDao {

    public static final String[] PKS = {"Codcli, Codfun, Datini"};
    
    public DaoFun_Responsavel() {
        super("dao.DaoFun_Responsavel", DaoPais.PKS);
    }
    
    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        Fun_ResponsavelEntity entity = new Fun_ResponsavelEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodcli(resultSet.getInt(1));
                entity.setCodfun(resultSet.getInt(2));
                entity.setDatini(resultSet.getDate(3));
                entity.setDatfim(resultSet.getDate(4));
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
                Fun_ResponsavelEntity entity = new Fun_ResponsavelEntity();
                entity.setCodcli(resultSet.getInt(1));
                entity.setCodfun(resultSet.getInt(2));
                entity.setDatini(resultSet.getDate(3));
                entity.setDatfim(resultSet.getDate(4));
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
                Fun_ResponsavelEntity entity = new Fun_ResponsavelEntity();
                entity.setCodcli(resultSet.getInt(1));
                entity.setCodfun(resultSet.getInt(2));
                entity.setDatini(resultSet.getDate(3));
                entity.setDatfim(resultSet.getDate(4));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
