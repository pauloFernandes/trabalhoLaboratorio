/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import dao.DaoEmpresa;
import entity.EmpresaEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.org.json.JSONException;
import util.org.json.JSONObject;

/**
 *
 * @author PauloHenrique
 */
@WebServlet(name = "GerenciarEmpresa", urlPatterns = {"/GerenciarEmpresa"})
public class GerenciarEmpresa extends HttpServlet {

    private static final int INICIALIZA_EMRPESA = 1;
    private static final int SALVAR_EMPRESA     = 2;
    private static final int REMOVER_EMPRESA    = 3;
    
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
        
        if (tipoRequisicao == INICIALIZA_EMRPESA) {
            out.print(this.obterEmpresa());
        } else if (tipoRequisicao == SALVAR_EMPRESA) {
            String nomfan = (request.getParameter("NOMFAN"));
            String razsoc = (request.getParameter("RAZSOC"));
            String telemp = (request.getParameter("TELEMP"));
            String celemp = (request.getParameter("CELEMP"));
            
            this.salvarEmpresa(nomfan, razsoc, telemp, celemp);
        } else if (tipoRequisicao == REMOVER_EMPRESA) {
            /*@todo metodo de exclusao de empresa*/
        }
            
    }

    private JSONObject obterEmpresa() {
        EmpresaEntity empresaEntity = Ambiente.getInstance().getEmpresaEntity();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nomfan", empresaEntity.getNomfan());
            jSONObject.put("razsoc", empresaEntity.getRazsoc());
            jSONObject.put("telemp", empresaEntity.getTelemp());
            jSONObject.put("celemp", empresaEntity.getCelemp());
            return jSONObject;
        } catch (JSONException ex) {
            Logger.getLogger(GerenciarEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void salvarEmpresa(String nomfan, String razsoc, String telemp, String celemp) {
        DaoEmpresa daoEmpresa = new DaoEmpresa();
        EmpresaEntity empresaEntity = Ambiente.getInstance().getEmpresaEntity();
        
        empresaEntity.setNomfan(nomfan);
        empresaEntity.setRazsoc(razsoc);
        empresaEntity.setTelemp(telemp);
        empresaEntity.setCelemp(celemp);
        daoEmpresa.persist(empresaEntity);
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
