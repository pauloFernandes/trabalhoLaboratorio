/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.MunicipioEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderMunicipio extends AbstractBuilder  {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.MUNICIPIO);
    }

    @Override
    public void createEntity() {
        this.entity = new MunicipioEntity();
    }
    
}
