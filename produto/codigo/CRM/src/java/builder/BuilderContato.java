/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.ContatoEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderContato extends AbstractBuilder  {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.CONTATO);
    }

    @Override
    public void createEntity() {
        this.entity = new ContatoEntity();
    }
    
}
