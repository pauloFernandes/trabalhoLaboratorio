/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import builder.BuilderUsuario;
import builder.Director;
import controller.command.Receptor;
import dao.DaoUsuario;
import entity.IEntity;
import entity.UsuarioEntity;
import java.util.List;
import util.Util;

/**
 *
 * @author PauloHenrique
 */
public class GerenciarUsuario {
    public boolean validarLogin(String login, String senha) {
        String where = "LOGUSU = '" + login + "' AND SENUSU = '" + senha + "'";
        
        BuilderUsuario builderUsuario = new BuilderUsuario();
        Director director             = new Director(builderUsuario);
        director.constroiObjeto();
        
        DaoUsuario daoUsuario         = (DaoUsuario) director.getDao();
        UsuarioEntity usuarioEntity   = null;
        List<IEntity> list            = daoUsuario.obterEntidadeCondicaoWhere(where);
        usuarioEntity                 = (UsuarioEntity) list.get(0); 
        boolean usuarioValido         = usuarioEntity.getCodusu() != 0;
        if (usuarioValido) {
            Ambiente.getInstance().inicializar(usuarioEntity);
        }
        
        return usuarioValido;
    }
    
    public int incluir(String nome, String login, String senha) {
        BuilderUsuario builderUsuario = new BuilderUsuario();
        Director director             = new Director(builderUsuario);
        director.constroiObjeto();
        
        DaoUsuario daoUsuario = (DaoUsuario) director.getDao();
        UsuarioEntity entity  = (UsuarioEntity) director.getEntity();
        Receptor receptor     = new Receptor(daoUsuario);
        
        int codusu = Util.getNextValidKey("USUARIO", "CODUSU");
        entity.setCodusu(codusu);
        entity.setNomusu(nome);
        entity.setLogusu(login);
        entity.setSenusu(senha);
        entity.setIdusuativ(UsuarioEntity.IDSITUATIV_ATIVO);
        receptor.call("incluir", entity);
        
        return codusu;
    }
    
    public void editar(int codigo, String nome, String login, String senha, String status) {
        Object[] pks = {codigo};
        BuilderUsuario builderUsuario = new BuilderUsuario();
        Director director = new Director(builderUsuario, pks);
        director.constroiObjeto();
        
        DaoUsuario daoUsuario = (DaoUsuario) director.getDao();
        UsuarioEntity entity  = (UsuarioEntity) director.getEntity();
        
        entity.setNomusu(nome);
        entity.setLogusu(login);
        entity.setSenusu(senha);
        if (status.equals(UsuarioEntity.IDSITUATIV_ATIVO) || status.equals(UsuarioEntity.IDSITUATIV_INATIVO)) {
            entity.setIdusuativ(status);
        }
        
        daoUsuario.persist(entity);
    }
    
    public void excluir(int codigo) {
        Object[] pks = {codigo};
        BuilderUsuario builderUsuario = new BuilderUsuario();
        Director director = new Director(builderUsuario, pks);
        director.constroiObjeto();
        
        DaoUsuario daoUsuario = (DaoUsuario) director.getDao();
        UsuarioEntity entity  = (UsuarioEntity) director.getEntity();
        
        daoUsuario.delete(entity);
    }
    
}
