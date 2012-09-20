/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.ArquivoExterno;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
