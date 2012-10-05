/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoEmpresa;
import dao.DaoFuncionario;
import entity.EmpresaEntity;
import entity.FuncionarioEntity;
import entity.IEntity;
import entity.UsuarioEntity;
import java.util.List;

/**
 *
 * @author PauloHenrique
 */
public class Ambiente {
    private UsuarioEntity     usuarioEntity;
    private FuncionarioEntity funcionarioEntity;
    private EmpresaEntity     empresaEntity;
    
    private static Ambiente instance;
    
    private Ambiente() {
    }
    
    public static Ambiente getInstance() {
        if (Ambiente.instance == null) {
            Ambiente.instance = new Ambiente();
        }
        
        return Ambiente.instance;
    }
    
    public void inicializar(UsuarioEntity entity) {
        this.usuarioEntity            = entity;
        DaoFuncionario daoFuncionario = new DaoFuncionario();
        String where = "CODUSU = " + this.usuarioEntity.getCodusu() +
                                 " AND DATINI < SYSDATE "
                               + " AND DATFIM IS NULL";
        List<IEntity> entidade        = daoFuncionario.obterEntidadeCondicaoWhere(where);
        if (entidade.size() > 0) {
            this.funcionarioEntity    = (FuncionarioEntity) entidade.get(0);
            DaoEmpresa daoEmpresa     = new DaoEmpresa();
            where                     = "CODEMP = " + this.funcionarioEntity.getCodemp();
            entidade                  = daoEmpresa.obterEntidadeCondicaoWhere(where);
            if (entidade.size() > 0) {
                this.empresaEntity    = (EmpresaEntity) entidade.get(0);
            }
        }
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public FuncionarioEntity getFuncionarioEntity() {
        return funcionarioEntity;
    }

    public EmpresaEntity getEmpresaEntity() {
        return empresaEntity;
    }
    
}
