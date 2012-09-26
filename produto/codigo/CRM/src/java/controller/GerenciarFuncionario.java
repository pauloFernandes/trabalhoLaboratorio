/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import builder.BuilderFuncionario;
import builder.Director;
import controller.command.Receptor;
import dao.DaoFuncionario;
import entity.FuncionarioEntity;

/**
 *
 * @author PauloHenrique
 */
public class GerenciarFuncionario {
    public int incliur(int codusu, int codemp, int tipoFuncionario) {
        BuilderFuncionario builderFuncionario = new BuilderFuncionario();
        Director director                     = new Director(builderFuncionario);
        director.constroiObjeto();
        
        DaoFuncionario daoFuncionario = (DaoFuncionario) director.getDao();
        FuncionarioEntity entity      = (FuncionarioEntity) director.getEntity();
        Receptor receptor             = new Receptor(daoFuncionario);
        
        int codfun = util.Util.getNextValidKey("funcionario", "codfun");
        entity.setCodfun(codfun);
        entity.setCodusu(codusu);
        entity.setCodemp(codemp);
        entity.setCodtipfun(tipoFuncionario);
        
        receptor.call("incluir", entity);
        
        return codfun;
    }
}
