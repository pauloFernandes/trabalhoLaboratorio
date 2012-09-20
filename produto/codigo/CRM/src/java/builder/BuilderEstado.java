/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.EstadoEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderEstado extends AbstractBuilder  {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.ESTADO);
    }

    @Override
    public void createEntity() {
        this.entity = new EstadoEntity();
    }
    
}
