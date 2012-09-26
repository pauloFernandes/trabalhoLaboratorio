/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command;

import dao.CrmIDao;
import entity.IEntity;
import java.util.HashMap;

/**
 *
 * @author PauloHenrique
 */
public class Receptor {
    private CrmIDao dao;
    private HashMap<String, Command> map;
    
    public Receptor(CrmIDao dao) {
        this.dao = dao;
        this.map = new HashMap<String, Command>();
        this.init();
    }
    
    public void init() {
        this.map.put("incluir", new IncluirCommand(this.dao));
        this.map.put("excluir", new ExcluirCommand(this.dao));
        this.map.put("alterar", new AlterarCommand(this.dao));
    }
    
    public Object call(String nome, IEntity entity) {    
        Command c     = (Command) this.map.get(nome);
        Object result = c.execute(entity);
//        return result;
        return null;
    }
    
}
