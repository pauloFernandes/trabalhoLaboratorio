/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.IEntity;
import entity.MunicipioEntity;
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
public class DaoMunicipio extends AbstractDao implements CrmIDao {

    public static final String[] PKS = {"Codmun"};
    
    public DaoMunicipio() {
        super("dao.DaoMunicipio", DaoMunicipio.PKS);
    }
    
    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        MunicipioEntity entity = new MunicipioEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setCodmun(resultSet.getInt("codmun"));
                entity.setCodest(resultSet.getInt("codest"));
                entity.setCodpai(resultSet.getInt("codpai"));
                entity.setNommun(resultSet.getString("nommun"));
                
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
                MunicipioEntity entity = new MunicipioEntity();
                entity.setCodmun(resultSet.getInt("codmun"));
                entity.setCodest(resultSet.getInt("codest"));
                entity.setCodpai(resultSet.getInt("codpai"));
                entity.setNommun(resultSet.getString("nommun"));
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
                MunicipioEntity entity = new MunicipioEntity();
                entity.setCodmun(resultSet.getInt("codmun"));
                entity.setCodest(resultSet.getInt("codest"));
                entity.setCodpai(resultSet.getInt("codpai"));
                entity.setNommun(resultSet.getString("nommun"));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
