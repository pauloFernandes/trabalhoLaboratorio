/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import builder.BuilderEmpresa;
import builder.Director;
import dao.DaoEmpresa;
import entity.EmpresaEntity;
import entity.IEntity;
import java.util.List;

/**
 *
 * @author PauloHenrique
 */
public class GerenciarEmpresa {
    public List<IEntity> pesquisar(String InscricaoJuridica, String nomeFantasia, String razaoSocial) {
        BuilderEmpresa builderEmpresa = new BuilderEmpresa();
        Director director             = new Director(builderEmpresa);
        director.constroiObjeto();
        String where = GerenciarEmpresa.montaClausulaWhere(InscricaoJuridica, nomeFantasia, razaoSocial);
        
        DaoEmpresa daoEmpresa       = (DaoEmpresa) director.getDao();
        List<IEntity> lista         = daoEmpresa.obterEntidadeCondicaoWhere(where);
        
        return lista;
    }
    
    public static void incluir() {
        
    }
    
    public static void editar() {
        
    }
    
    public static void excluir() {
        
    }
    
    public static void alterarVendedor() {
        
    }
    
    private static String montaClausulaWhere(String InscricaoJuridica, String nomeFantasia, String razaoSocial) {
        String whereClause = " 1 = 1 ";
        
        if (!InscricaoJuridica.isEmpty()) {
            whereClause += " AND NUMINSJUR = '" + InscricaoJuridica + "' ";
        }
        
        if (!nomeFantasia.isEmpty()) {
            whereClause += " AND NOMFAN = '" + nomeFantasia + "' ";
        }
        
        if (!razaoSocial.isEmpty()) {
            whereClause += " AND RAZSOC = '" + razaoSocial + "' ";
        }
        
        return whereClause;
    }
    
}
