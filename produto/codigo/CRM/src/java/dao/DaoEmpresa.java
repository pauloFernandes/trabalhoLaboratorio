/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.EmpresaEntity;
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
public class DaoEmpresa extends AbstractDao implements CrmIDao {

    public static final String[] PKS = {"Codemp"};
    
    public DaoEmpresa() {
        super("dao.DaoEmpresa", DaoEmpresa.PKS);
    }
    
    
    @Override
    public IEntity obterEntidade(Object[] valorPks) {
        EmpresaEntity entity = new EmpresaEntity();
        try {
            ResultSet resultSet = super.getEntityById(PKS, valorPks);
            while(resultSet.next()) {
                entity.setIdtipinsjur(resultSet.getString("idtipinsjur"));
                entity.setCodmun(resultSet.getInt("codmun"));
                entity.setCodest(resultSet.getInt("codest"));
                entity.setNuminsjur(resultSet.getString("numinsjur"));
                entity.setNomfan(resultSet.getString("nomfan"));
                entity.setRazsoc(resultSet.getString("razsoc"));
                entity.setTelemp(resultSet.getString("telemp"));
                entity.setCodpai(resultSet.getInt("codpai"));
                entity.setCodemp(resultSet.getInt("codemp"));
                entity.setCelemp(resultSet.getString("celemp"));
                entity.setDesend(resultSet.getString("desend"));
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
                EmpresaEntity entity = new EmpresaEntity();
                entity.setIdtipinsjur(resultSet.getString("idtipinsjur"));
                entity.setCodmun(resultSet.getInt("codmun"));
                entity.setCodest(resultSet.getInt("codest"));
                entity.setNuminsjur(resultSet.getString("numinsjur"));
                entity.setNomfan(resultSet.getString("nomfan"));
                entity.setRazsoc(resultSet.getString("razsoc"));
                entity.setTelemp(resultSet.getString("telemp"));
                entity.setCodpai(resultSet.getInt("codpai"));
                entity.setCodemp(resultSet.getInt("codemp"));
                entity.setCelemp(resultSet.getString("celemp"));
                entity.setDesend(resultSet.getString("desend"));
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
                EmpresaEntity entity = new EmpresaEntity();
                entity.setIdtipinsjur(resultSet.getString("idtipinsjur"));
                entity.setCodmun(resultSet.getInt("codmun"));
                entity.setCodest(resultSet.getInt("codest"));
                entity.setNuminsjur(resultSet.getString("numinsjur"));
                entity.setNomfan(resultSet.getString("nomfan"));
                entity.setRazsoc(resultSet.getString("razsoc"));
                entity.setTelemp(resultSet.getString("telemp"));
                entity.setCodpai(resultSet.getInt("codpai"));
                entity.setCodemp(resultSet.getInt("codemp"));
                entity.setCelemp(resultSet.getString("celemp"));
                entity.setDesend(resultSet.getString("desend"));
                entity.setNewRegister(false);
                lista.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
}
