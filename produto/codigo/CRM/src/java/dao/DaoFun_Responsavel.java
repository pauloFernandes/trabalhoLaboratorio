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

    public static final String[] PKS = {"Codcli", "Codfun", "Codemp", "Datini"};
    
    public DaoFun_Responsavel() {
        super("dao.DaoFun_Responsavel", DaoFun_Responsavel.PKS);
    }
    
    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        Fun_ResponsavelEntity entity = new Fun_ResponsavelEntity();
        try {
            ResultSet resultSet = super.getEntityById(DaoFun_Responsavel.PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodcli(resultSet.getInt("codcli"));
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setCodemp(resultSet.getInt("codemp"));
                entity.setDatini(resultSet.getDate("datini"));
                entity.setDatfim(resultSet.getDate("datfim"));
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
                entity.setCodcli(resultSet.getInt("codcli"));
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setCodemp(resultSet.getInt("codemp"));
                entity.setDatini(resultSet.getDate("datini"));
                entity.setDatfim(resultSet.getDate("datfim"));
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
                entity.setCodcli(resultSet.getInt("codcli"));
                entity.setCodfun(resultSet.getInt("codfun"));
                entity.setCodemp(resultSet.getInt("codemp"));
                entity.setDatini(resultSet.getDate("datini"));
                entity.setDatfim(resultSet.getDate("datfim"));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
