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
public class Director {
    private AbstractBuilder builder;
    private Object[]        params;
    
    public Director(AbstractBuilder builder) {
        this.builder = builder;
        this.params  = null;
    }
    
    public Director(AbstractBuilder builder, Object[] params) {
        this.builder = builder;
        this.params = params;
    }
    
    public IEntity getEntity() {
        return this.builder.getEntity();
    }
    
    public CrmIDao getDao() {
        
        return this.builder.getDao();
    }
    
    public void constroiObjeto() {
        this.builder.createDao();
        if (params == null) {
            this.builder.createEntity();
        } else {
            this.builder.createEntity(params);
        }
    }    
    
    
}
