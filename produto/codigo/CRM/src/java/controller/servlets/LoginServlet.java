/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import controller.GerenciarEmpresa;
import controller.GerenciarUsuario;
import java.io.IOException;
import java.io.PrintWriter;
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
            out.println("cadastro");
        } else if (tipoRequisicao == LISTA_EMPRESA) {
            GerenciarEmpresa gerenciarEmpresa = new GerenciarEmpresa();
            out.println(gerenciarEmpresa.stringListaEmpresas());
        } else if (tipoRequisicao == CONTROLE_ACESSO) {
            out.print(Ambiente.getTipoPermissao());
        }
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
