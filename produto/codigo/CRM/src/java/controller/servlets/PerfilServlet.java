/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import dao.DaoEmpresa;
import entity.EmpresaEntity;
import entity.IEntity;
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

    private static final int CARREGAR_TELA = 1;
    
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
        
        if (tipoRequisicao == CARREGAR_TELA) {
            out.print(this.carregarTela());
        }
    }
    
    public JSONObject carregarTela() {        
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
                jSONObject.put("empusu", empresaEntity.getRazsoc());
            }
        } catch (JSONException ex) {
            Logger.getLogger(PerfilServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jSONObject;
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
