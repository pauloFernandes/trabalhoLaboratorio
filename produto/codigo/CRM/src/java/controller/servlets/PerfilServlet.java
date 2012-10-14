/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import controller.GerenciarFuncionario;
import controller.GerenciarUsuario;
import dao.DaoEmpresa;
import dao.DaoUsuario;
import entity.EmpresaEntity;
import entity.FuncionarioEntity;
import entity.IEntity;
import entity.TipoFuncionarioEntity;
import entity.UsuarioEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.org.json.JSONArray;
import util.org.json.JSONException;
import util.org.json.JSONObject;

/**
 *
 * @author PauloHenrique
 */
@WebServlet(name = "PerfilServlet", urlPatterns = {"/PerfilServlet"})
public class PerfilServlet extends HttpServlet {

    private static final int CARREGAR_TELA  = 1;
    private static final int SALVAR_PERFIL  = 2;
    private static final int EXCLUIR_PERFIL = 3;
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out    = response.getWriter();
        int tipoRequisicao = Integer.parseInt(request.getParameter("TIPO_REQUISICAO"));
        
        if (tipoRequisicao == CARREGAR_TELA) {
            if (Ambiente.validaPermissaoUsuario(Ambiente.PERMISSAO_USUARIO)) {
                out.print(this.carregarTela());
            }
        } else if (tipoRequisicao == SALVAR_PERFIL) {
            if (Ambiente.validaPermissaoUsuario(Ambiente.PERMISSAO_USUARIO)) {
                int codigo            = Integer.parseInt(request.getParameter("CODIGO"));
                String nome           = request.getParameter("NOME");
                String login          = request.getParameter("LOGIN");
                String senha          = request.getParameter("SENHA");
                String confirmarSenha = request.getParameter("CONFIRMAR_SENHA");
                int empresa           = Integer.parseInt(request.getParameter("EMPRESA"));

                out.println(this.salvarPerfil(codigo, nome, login, senha, confirmarSenha, empresa));
            }
        } else if (tipoRequisicao == EXCLUIR_PERFIL) {
            if (Ambiente.validaPermissaoUsuario(Ambiente.PERMISSAO_USUARIO)) {
                int codigo            = Integer.parseInt(request.getParameter("CODIGO"));
                this.excluirPerfil(codigo);
            }
        }
    }
    
    private JSONObject carregarTela() {        
        UsuarioEntity usuarioEntity = Ambiente.getInstance().getUsuarioEntity();
        EmpresaEntity empresaEntity = null;
        JSONObject jSONObject       = new JSONObject();
        DaoEmpresa daoEmpresa       = new DaoEmpresa();
        List<IEntity> lista         = daoEmpresa.obterTodasEntidades();
        JSONArray array             = new JSONArray();
        
        if (Ambiente.getInstance().getEmpresaEntity() != null) {
            empresaEntity = Ambiente.getInstance().getEmpresaEntity();
        }
        
        try {        
            for (IEntity e : lista) {
                EmpresaEntity entity = (EmpresaEntity) e;
                JSONObject aux       = new JSONObject();
                aux.put("codemp", entity.getCodemp());
                aux.put("razsoc", entity.getRazsoc());
                array.put(aux);
            }
            
            jSONObject.put("codusu", usuarioEntity.getCodusu());
            jSONObject.put("nomusu", usuarioEntity.getNomusu());
            jSONObject.put("logusu", usuarioEntity.getLogusu());
            jSONObject.put("empresas", array);
            if (empresaEntity != null) {
                jSONObject.put("empusu", empresaEntity.getCodemp());
            } else {
                jSONObject.put("empusu", "0");
            }
        } catch (JSONException ex) {
            Logger.getLogger(PerfilServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jSONObject;
    }

    private String salvarPerfil(int codigo, String nome, String login, String senha, String confirmarSenha, int empresa) {
        UsuarioEntity usuarioEntity         = Ambiente.getInstance().getUsuarioEntity();
        FuncionarioEntity funcionarioEntity = Ambiente.getInstance().getFuncionarioEntity();
        
        GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
        String validacao = this.validaSalvarPerfil(login, senha, confirmarSenha);
        if (validacao == null) {
            gerenciarUsuario.editar(codigo, nome, login, senha, UsuarioEntity.IDSITUATIV_ATIVO);
            if (funcionarioEntity == null || empresa != funcionarioEntity.getCodemp()) {
                GerenciarFuncionario gerenciarFuncionario = new GerenciarFuncionario();
                gerenciarFuncionario.incliur(usuarioEntity.getCodusu(), empresa, TipoFuncionarioEntity.FUNCIOARIO);
            }
            
            validacao = "Usuário salvo com sucesso";
        }
        
        return validacao;
    }
    
    private String validaSalvarPerfil(String login, String senha, String confirmarSenha) {
        if (!senha.equals(confirmarSenha)) {
            return "As senhas digitadas não coincidem.";
        }
        
        // Validacao de login de usuário: garante que dois usuários não tenham o mesmo login.
        DaoUsuario daoUsuario = new DaoUsuario();
        List<IEntity> lista = daoUsuario.obterEntidadeCondicaoWhere(" LOGUSU = '" + login +"'");
        UsuarioEntity usuario = (UsuarioEntity) lista.get(0);
        if (usuario.getLogusu() != null && !Ambiente.getInstance().getUsuarioEntity().getLogusu().equals(login)) {
            return "Login já existente. Tente outro.";
        }
        
        return null;
    } 
    
    private void excluirPerfil(int codigo) {
        GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
        gerenciarUsuario.excluir(codigo);
    }
        
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
