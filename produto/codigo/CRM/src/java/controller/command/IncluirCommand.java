/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command;

import dao.ArquivoExterno;
import dao.CrmIDao;
import db.DbStatement;
import entity.IEntity;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PauloHenrique
 */
public class IncluirCommand extends Command {

    public IncluirCommand(CrmIDao dao) {
        super(dao);
    }
    
    @Override
    public Object execute(IEntity entity) {
        this.dao.persist(entity);
        return true;
    }
    
}
