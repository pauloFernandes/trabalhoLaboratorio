/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import dao.DaoFactory;
import entity.Tipo_AtividadeEntity;

/**
 *
 * @author PauloHenrique
 */
public class BuilderTipo_Atividade  extends AbstractBuilder {

    @Override
    public void createDao() {
        this.dao = DaoFactory.getInstance(DaoFactory.TIPO_ATIVIDADE);
    }

    @Override
    public void createEntity() {
        this.entity = new Tipo_AtividadeEntity();
    }
    
}
