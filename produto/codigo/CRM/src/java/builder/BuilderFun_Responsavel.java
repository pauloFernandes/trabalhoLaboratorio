/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.Fun_ResponsavelEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderFun_Responsavel extends AbstractBuilder  {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.FUN_RESPONSAVEL);
    }

    @Override
    public void createEntity() {
        this.entity = new Fun_ResponsavelEntity();
    }
    
}
