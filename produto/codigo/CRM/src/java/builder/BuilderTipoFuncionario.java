/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.CrmIDao;
import dao.DaoFactory;
import entity.IEntity;
import entity.TipoFuncionarioEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderTipoFuncionario extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.TIPO_FUNCIONARIO);
    }

    @Override
    public void createEntity() {
        this.entity = new TipoFuncionarioEntity();
    }
    
}
