/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.ConviteEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderConvite extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.CONVITE);
    }

    @Override
    public void createEntity() {
        this.entity = new ConviteEntity();
    }
    
}
