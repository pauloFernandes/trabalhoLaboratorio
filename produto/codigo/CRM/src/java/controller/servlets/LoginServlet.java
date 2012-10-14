/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import controller.GerenciarEmpresa;
import controller.GerenciarUsuario;
import dao.DaoEmpresa;
import dao.DaoFuncionario;
import dao.DaoUsuario;
import entity.EmpresaEntity;
import entity.FuncionarioEntity;
import entity.TipoFuncionarioEntity;
import entity.UsuarioEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PauloHenrique
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final int LOGIN           = 1;
    private static final int CADASTRO        = 2;
    private static final int LISTA_EMPRESA   = 3;
    private static final int CONTROLE_ACESSO = 4;
    
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
        PrintWriter out = response.getWriter();
        int tipoRequisicao = Integer.parseInt(request.getParameter("TIPO_REQUISICAO"));
        
        if (tipoRequisicao == LOGIN) {
            String login    = request.getParameter("LOGIN");
            String senha    = request.getParameter("SENHA");
            GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
            boolean result = gerenciarUsuario.validarLogin(login, senha);
            if (result) {
                out.println(result);
            }
        } else if (tipoRequisicao == CADASTRO){
            String nome            = request.getParameter("NOME");
            String login           = request.getParameter("LOGIN");
            String senha           = request.getParameter("SENHA");
            String tipoCadastro    = request.getParameter("TIPOCADASTRO");
            int empresaSelecionada = Integer.parseInt(request.getParameter("EMPRESASELECIONADA"));
            String inscjur         = request.getParameter("INSCJUR");
            String nomfan          = request.getParameter("NOMFAN");
            String razsoc          = request.getParameter("RAZSOC");
            
            this.salvarDadosUsuario(nome, login, senha, tipoCadastro, empresaSelecionada, inscjur, nomfan, razsoc);
            out.print("Cadastro efetuado com sucesso.");
        } else if (tipoRequisicao == LISTA_EMPRESA) {
            GerenciarEmpresa gerenciarEmpresa = new GerenciarEmpresa();
            out.println(gerenciarEmpresa.stringListaEmpresas());
        } else if (tipoRequisicao == CONTROLE_ACESSO) {
            out.print(Ambiente.getTipoPermissao());
        }
    }
    
    private void salvarDadosUsuario(String nomusu, String logusu, String senusu, String tipoCadastro, 
                                    int codemp, String inscjur, String nomfan, String razsoc) {
        DaoUsuario daoUsuario       = new DaoUsuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        
        int codusu = util.Util.getNextValidKey("USUARIO", "CODUSU");
        usuarioEntity.setCodusu(codusu);
        usuarioEntity.setNomusu(nomusu);
        usuarioEntity.setLogusu(logusu);
        usuarioEntity.setSenusu(senusu);
        usuarioEntity.setIdusuativ(UsuarioEntity.IDSITUATIV_ATIVO);
        daoUsuario.persist(usuarioEntity);
        
        if (tipoCadastro.equals("1")) {
            // associar-se a empreja ja existente.
            this.associarEmpresa(codusu, codemp, TipoFuncionarioEntity.FUNCIOARIO);
        } else {
            // criar uma nova empresa.
            this.criarNovaEmpresa(codusu, inscjur, nomfan, razsoc);
        }
    }
    
    public void associarEmpresa(int codusu, int codemp, int tipoPermissao) {
        DaoFuncionario daoFuncionario       = new DaoFuncionario();
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        
        funcionarioEntity.setCodfun(util.Util.getNextValidKey("FUNCIONARIO", "CODFUN"));
        funcionarioEntity.setCodusu(codusu);
        funcionarioEntity.setCodemp(codemp);
        funcionarioEntity.setCodtipfun(TipoFuncionarioEntity.FUNCIOARIO);
        
        if (tipoPermissao == TipoFuncionarioEntity.DONO) {
            funcionarioEntity.setCodtipfun(TipoFuncionarioEntity.DONO);
            funcionarioEntity.setDatini(new Date());
        }
        daoFuncionario.persist(funcionarioEntity);
    }
    
    public void criarNovaEmpresa(int codusu, String inscjur, String nomfan, String razsoc) {
        DaoEmpresa daoEmpresa       = new DaoEmpresa();
        EmpresaEntity empresaEntity = new EmpresaEntity();
        
        int codemp = util.Util.getNextValidKey("EMPRESA", "CODEMP");
        empresaEntity.setCodemp(codemp);
        empresaEntity.setIdtipinsjur("J");
        empresaEntity.setNuminsjur(inscjur);
        empresaEntity.setNomfan(nomfan);
        empresaEntity.setRazsoc(razsoc);
        daoEmpresa.persist(empresaEntity);
        
        this.associarEmpresa(codusu, codemp, TipoFuncionarioEntity.DONO);
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
