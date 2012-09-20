/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.FuncionarioEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderFuncionario extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.FUNCIONARIO);
    }

    @Override
    public void createEntity() {
        this.entity = new FuncionarioEntity();
    }
    
}
