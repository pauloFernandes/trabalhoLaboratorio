/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.command.Receptor;
import dao.ArquivoExterno;
import dao.DaoCliente;
import dao.DaoPais;
import entity.ClienteEntity;
import entity.PaisEntity;
import entity.TipoFuncionarioEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.enterprise.event.Reception;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PauloHenrique
 */
@WebServlet(name = "ServletTeste", urlPatterns = {"/ServletTeste"})
public class ServletTeste extends HttpServlet {
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
        
        String nome               = request.getParameter("NOME");
        String login              = request.getParameter("LOGIN");
        String senha              = request.getParameter("SENHA");
        int tipoCadastro          = Integer.parseInt(request.getParameter("TIPOCADASTRO"));
        int empresaSelecionada    = Integer.parseInt(request.getParameter("EMPRESASELECIONADA"));
        String inscjur            = request.getParameter("INSCJUR");
        String nomfan             = request.getParameter("NOMFAN");
        String razsoc             = request.getParameter("RAZSOC");
        int tipoFuncioario        = TipoFuncionarioEntity.FUNCIOARIO;
        
        GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
        int codusu = gerenciarUsuario.incluir(nome, login, senha);
        if (tipoCadastro == 2) {
            tipoFuncioario                    = TipoFuncionarioEntity.DONO;
            GerenciarEmpresa gerenciarEmpresa = new GerenciarEmpresa();
            empresaSelecionada                = gerenciarEmpresa.incluir(inscjur, nomfan, razsoc);
        } 
        
        GerenciarFuncionario gerenciarFuncionario = new GerenciarFuncionario();
        gerenciarFuncionario.incliur(codusu, empresaSelecionada, tipoFuncioario);
        out.println("ok");
        
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
