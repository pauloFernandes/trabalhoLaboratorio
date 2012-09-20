/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.CrmIDao;
import entity.IEntity;

/**
 *
 * @author PauloHenrique
 */
public abstract class AbstractBuilder {
    protected CrmIDao dao;
    protected IEntity entity;
    
    public abstract void createDao();
    public abstract void createEntity();
    
    public void createEntity(Object[] params) {
        if (this.dao == null) {
            this.createDao();
        }
        
        this.entity = this.getDao().obterEntidade(params);
    }
    
    public CrmIDao getDao() {
        return this.dao;
    }
    
    public IEntity getEntity() {
        return this.entity;
    }
}
