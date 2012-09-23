/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.LembreteEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderLembrete extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.LEMBRETE);
    }

    @Override
    public void createEntity() {
        this.entity = new LembreteEntity();
    }
    
}
