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
public class AlterarCommand extends Command {
    public AlterarCommand(CrmIDao dao) {
        super(dao);
    }

    @Override
    public Object execute(IEntity entity) {
//        return this.getClass().toString() + entity.getClass().toString();
        this.dao.persist(entity);
        return true;
    }
}
