/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Empresa;
import util.org.json.JSONArray;
import util.org.json.JSONException;
import util.org.json.JSONObject;

/**
 *
 * @author PauloHenrique
 */
@WebServlet(name = "GerenciarClientes", urlPatterns = {"/GerenciarClientes"})
public class GerenciarClientes extends HttpServlet {

    private static int INICIALIZAR_CLIENTE_GRID = 1;
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
        
        if (tipoRequisicao == INICIALIZAR_CLIENTE_GRID) {
            out.print(this.obterTodosClientes());
        }
        
    }
    
    private JSONObject obterTodosClientes() {
        int codemp = Ambiente.getInstance().getEmpresaEntity().getCodemp();
        ResultSet resultSet = Empresa.obterTodosClientes(codemp);
        JSONArray array = new JSONArray();
        
        try {
            while (resultSet.next()) {
                JSONObject aux = new JSONObject();
                aux.put("nomfan", resultSet.getString("NOMFAN"));
                aux.put("razsoc", resultSet.getString("RAZSOC"));
                aux.put("numinsjur", resultSet.getString("NUMINSJUR"));
                aux.put("CODFUN", resultSet.getString("CODFUN"));
                aux.put("NOMUSU", resultSet.getString("NOMUSU"));
                
                array.put(aux);
            }
            
            return new JSONObject().put(null, array);
        } catch (JSONException e) {
            Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
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
