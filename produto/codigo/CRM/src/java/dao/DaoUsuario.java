/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.IEntity;
import entity.UsuarioEntity;
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
public class DaoUsuario extends AbstractDao implements CrmIDao {
    
    public static final String[] PKS = {"Codusu"};
    
    public DaoUsuario() {
        super("dao.DaoUsuario", DaoUsuario.PKS);
    }

    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        UsuarioEntity entity = new UsuarioEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setIdusuativ(resultSet.getString("idusuativ"));
                entity.setSenusu(resultSet.getString("senusu"));
                entity.setCodusu(resultSet.getInt("codusu"));
                entity.setNomusu(resultSet.getString("nomusu"));
                entity.setLogusu(resultSet.getString("logusu"));
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
                UsuarioEntity entity = new UsuarioEntity();
                entity.setIdusuativ(resultSet.getString("idusuativ"));
                entity.setSenusu(resultSet.getString("senusu"));
                entity.setCodusu(resultSet.getInt("codusu"));
                entity.setNomusu(resultSet.getString("nomusu"));
                entity.setLogusu(resultSet.getString("logusu"));
                entity.setNewRegister(false);
                lista.add(entity);
            }
            
            if (lista.isEmpty()) {
                lista.add(new UsuarioEntity());
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
                UsuarioEntity entity = new UsuarioEntity();
                entity.setIdusuativ(resultSet.getString("idusuativ"));
                entity.setSenusu(resultSet.getString("senusu"));
                entity.setCodusu(resultSet.getInt("codusu"));
                entity.setNomusu(resultSet.getString("nomusu"));
                entity.setLogusu(resultSet.getString("logusu"));
                entity.setNewRegister(false);
                lista.add(entity);
            }
            
            if (lista.isEmpty()) {
                lista.add(new UsuarioEntity());
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
