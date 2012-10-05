/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author PauloHenrique
 */
public abstract class AbstractEntity implements IEntity {
    public boolean newRegister;
    
    public AbstractEntity() {
        this.newRegister = true;
    }

    public boolean isNewRegister() {
        return newRegister;
    }

    public void setNewRegister(boolean newRegister) {
        this.newRegister = newRegister;
    }
    
    
}
