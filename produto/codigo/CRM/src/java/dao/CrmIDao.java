/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.IEntity;
import java.util.List;

/**
 *
 * @author PauloHenrique
 */
public interface CrmIDao {
    public void persist(IEntity entidade);
    public IEntity obterEntidade(Object[] pk);
    public List<IEntity> obterTodasEntidades();
    public List<IEntity> obterEntidadeCondicaoWhere(String whereClause);
    public void delete(IEntity entidade);
}
