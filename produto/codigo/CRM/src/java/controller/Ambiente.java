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
    
    public static final int PERMISSAO_USUARIO       = 0;
    public static final int PERMISSAO_FUNCIONARIO   = 1;
    public static final int PERMISSAO_ADMINISTRADOR = 2;
    public static final int PERMISSAO_GERENTE       = 3;
    
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
                       " AND DATINI IS NOT NULL " + 
                       " AND DATFIM IS NULL";
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
    
    public static boolean validaPermissaoUsuario(int tipoPermissao) {
        int permissaoCorrente = 0;
        if (Ambiente.getInstance().getFuncionarioEntity() != null) {
            permissaoCorrente = Ambiente.getInstance().getFuncionarioEntity().getCodtipfun();
        }
        
        if (tipoPermissao == Ambiente.PERMISSAO_GERENTE) {
            // Validação de permissão do Gerente (dono da empresa). Somente ele pode acessar
            // funções de permissões dedicadas somente a ele.
            return permissaoCorrente == Ambiente.PERMISSAO_GERENTE;
        } else if (tipoPermissao == Ambiente.PERMISSAO_ADMINISTRADOR) {
            // Validação de permissão de administradores.
            return permissaoCorrente == Ambiente.PERMISSAO_ADMINISTRADOR ||
                   permissaoCorrente == Ambiente.PERMISSAO_GERENTE;
        } else if (tipoPermissao == Ambiente.PERMISSAO_FUNCIONARIO) {
            // Valida permissão para funcionários: Garante que qualquer um que seja
            // funcionário de determinada empresa tenham permissão de uso.
            return Ambiente.getInstance().getFuncionarioEntity() != null;
        }
        
        // Verifica se há um usuário logado.
        return Ambiente.getInstance().getUsuarioEntity() != null;
    }

    public static int getTipoPermissao() {
        if (Ambiente.getInstance().getFuncionarioEntity() == null) {
            return 0;
        }
        
        return Ambiente.getInstance().getFuncionarioEntity().getCodtipfun();
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
