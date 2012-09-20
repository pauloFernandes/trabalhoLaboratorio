/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.PaisEntity;
import model.Pais;

/**
 *
 * @author PauloHenrique
 */
public class BuilderPais extends AbstractBuilder  {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.PAIS);
    }

    @Override
    public void createEntity() {
        this.entity = new PaisEntity();
    }
    
}
