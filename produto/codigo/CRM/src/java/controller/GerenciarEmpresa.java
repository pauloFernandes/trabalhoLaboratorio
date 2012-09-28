/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import builder.BuilderEmpresa;
import builder.Director;
import controller.command.Receptor;
import dao.DaoEmpresa;
import entity.EmpresaEntity;
import entity.IEntity;
import java.util.List;
import util.Util;

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
    
    public int incluir(String numinsjur, String nomfan, String razsoc) {
        BuilderEmpresa builderEmpresa = new BuilderEmpresa();
        Director director             = new Director(builderEmpresa);
        director.constroiObjeto();
        
        DaoEmpresa daoEmpresa = (DaoEmpresa) director.getDao();
        EmpresaEntity entity  = (EmpresaEntity) director.getEntity();
        Receptor receptor     = new Receptor(daoEmpresa);
        
        int codemp = Util.getNextValidKey("empresa", "codemp");
        entity.setCodemp(codemp);
        if (numinsjur.length() == 14) {
            entity.setIdtipinsjur(EmpresaEntity.IDTIPINSJUR_JURIDICA);
        } else {
            entity.setIdtipinsjur(EmpresaEntity.IDTIPINSJUR_FISICA);
        }
        entity.setNuminsjur(numinsjur);
        entity.setNomfan(nomfan);
        entity.setRazsoc(razsoc);
        entity.setCodmun(1);
        entity.setCodest(1);
        entity.setCodpai(1);
        receptor.call("incluir", entity);
        
        return codemp;
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
