/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.ConvidadoEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderConvidado extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.CONVIDADO);
    }

    @Override
    public void createEntity() {
        this.entity = new ConvidadoEntity();
    }
    
}
