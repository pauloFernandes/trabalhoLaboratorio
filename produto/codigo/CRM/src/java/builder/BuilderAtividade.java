/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.AtividadeEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderAtividade extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.ATIVIDADE);
    }

    @Override
    public void createEntity() {
        this.entity = new AtividadeEntity();
    }
    
}
