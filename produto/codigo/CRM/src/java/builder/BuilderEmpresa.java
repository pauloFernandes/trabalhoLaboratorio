/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.EmpresaEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderEmpresa extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.EMPRESA);
    }

    @Override
    public void createEntity() {
        this.entity = new EmpresaEntity();
    }
    
}
