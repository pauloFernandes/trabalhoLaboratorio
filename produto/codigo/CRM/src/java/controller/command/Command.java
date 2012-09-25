/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command;

import dao.CrmIDao;
import entity.IEntity;

/**
 *
 * @author PauloHenrique
 */
public abstract class Command {
    protected CrmIDao dao;
    
    public Command(CrmIDao dao) {
        this.dao = dao;
    }
    
    public abstract Object execute(IEntity entity); 
    
}
