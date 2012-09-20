/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.ClienteEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderCliente extends AbstractBuilder  {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.CLIENTE);
    }

    @Override
    public void createEntity() {
        this.entity = new ClienteEntity();
    }
    
}
