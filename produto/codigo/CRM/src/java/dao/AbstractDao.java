/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DbStatement;
import entity.IEntity;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.OBJECT_NOT_EXIST;

/**
 *
 * @author PauloHenrique
 */
public abstract class AbstractDao {
    private String className;
    private String[] primaryKeys;
    private Class c;
    private Method[] m;
    
    public AbstractDao(String className, String[] primaryKeys) {
        className = className.replace("dao.Dao", "");
        
        try {
            this.className   = className;
            this.primaryKeys = primaryKeys;
            this.c           = Class.forName("entity."+className+"Entity");
            this.m           = c.getDeclaredMethods();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void persist(IEntity entidade) {
        try {
            if (entidade.isNewRegister()) {
                this.insert(entidade);
            } else {
                this.update(entidade);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoPais.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    protected void insert(IEntity entity) throws SQLException {
        String campos = "";
        String values = "";
        
        for (Method meth : this.m) {
            if (meth.toString().contains("get")) {
                //String nomeCampo = meth.toString().replace("get", "");
                String nomeCampo = meth.getName().replace("get", "");
                campos += nomeCampo + ", ";
                values += this.getValorComAspas("get" + nomeCampo, entity) + ", ";
            }
        }
        
        campos = campos.substring(0, campos.length() - 2);
        values = values.substring(0, values.length() - 2);
        String sql = "INSERT INTO " + this.className + "(" + campos +  ") " + "VALUES (" + values + ")";
        DbStatement.insert(sql);
    }
    
    protected void update(IEntity entity) throws SQLException {
        String sql = "UPDATE " + this.className.replace("dao.Dao", "") + " SET ";
        for (Method meth : m) {
            if (meth.getName().contains("get")) {
                String nomeCampo = meth.getName().replace("get", "");
                sql += nomeCampo + " = " + this.getValorComAspas("get"+nomeCampo, entity) + ", ";
            }
        }
        
        sql = sql.substring(0, sql.length() - 2) + " WHERE ";
        
        for (String pk : this.primaryKeys) {
            sql += pk + " = " + this.getValorComAspas("get"+pk, entity) + " AND ";
        }
        
        sql = sql.substring(0, sql.length() - 4);
        
        DbStatement.update(sql);        
    }
    
    protected ResultSet getEntityById(String[] pks, Object[] valorPks) throws SQLException {
        String select = "SELECT ";
        
        for (Method meth : this.m) {
            if (meth.toString().contains("get")) {
                select += meth.getName().replace("get", "") + ", ";
            }
        }
        
        select = select.substring(0, select.length() - 2);  
        select += " FROM " + this.className + " WHERE ";
        for (int i =0; i < pks.length; i++) {
            select += pks[i] + " = '" + valorPks[i] + "' AND ";
        }
        
        select = select.substring(0, select.length() - 4);
        return DbStatement.select(select);
    }
    
    protected ResultSet getAllEntities() {
        String select = "SELECT ";
        
        for (Method meth : this.m) {
            if (meth.toString().contains("get")) {
                select += meth.getName().replace("get", "") + ", ";
            }
        }
        
        select = select.substring(0, select.length() - 2);  
        select += " FROM " + this.className;
        
        try {
            return DbStatement.select(select);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    protected ResultSet getEntityByWhere(String whereClause) {
        String select = "SELECT ";
        
        for (Method meth : this.m) {
            if (meth.toString().contains("get")) {
                select += meth.getName().replace("get", "") + ", ";
            }
        }
        
        select = select.substring(0, select.length() - 2);  
        select += " FROM " + this.className + " WHERE " + whereClause;
        
        try {
            return DbStatement.select(select);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void delete(IEntity entidade) {
        String sql = "DELETE FROM " + this.className + " WHERE ";
        for (String pk : this.primaryKeys) {
            sql += pk + " = " + this.getValorComAspas("get"+pk, entidade) + " AND ";
        }
 
        sql = sql.substring(0, sql.length() - 4);
        
        try {
            DbStatement.delete(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    private String getValorComAspas(String nomeMetodo, IEntity entity) {
        try {
            /* As duas variáveis abaixo são usadas somente para segurança do 
             * método,que devem receber um parâmetro  dos respectivos tipos,mas
             * que aqui devem ser nulos.*/ 
            Object[] nullObject = null;
            Class[] nullClass   = null;
            Object returnType   = this.c.getMethod(nomeMetodo, nullClass).getReturnType();
            
            if (this.c.getMethod(nomeMetodo, nullClass).invoke(entity, nullObject) == null) {
                return "null";
            } else if (returnType.toString().replace("class ", "").equals("java.lang.String")) {
                return "'" + this.c.getMethod(nomeMetodo, nullClass).invoke(entity, nullObject) + "'";
            } else if (returnType.toString().replace("class ", "").equals("java.util.Date")) {
                return "'" + new SimpleDateFormat("dd/MM/yyyy").format(this.c.getMethod(nomeMetodo, nullClass)
                                .invoke(entity, nullObject)) + "'";
            } else {
                return "" + this.c.getMethod(nomeMetodo, nullClass).invoke(entity, nullObject);
            }
            
        } catch (Throwable e) {
            return e.getMessage();
        }
    }
    
}
