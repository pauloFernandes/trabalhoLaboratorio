/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.UsuarioEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderUsuario  extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.USUARIO);
    }

    @Override
    public void createEntity() {
        this.entity = new UsuarioEntity();
    }

}
