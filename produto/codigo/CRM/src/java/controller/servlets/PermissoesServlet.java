/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import controller.GerenciarFuncionario;
import dao.DaoFuncionario;
import entity.FuncionarioEntity;
import entity.IEntity;
import entity.TipoFuncionarioEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import util.org.json.JSONArray;
import util.org.json.JSONException;
import util.org.json.JSONObject;

/**
 *
 * @author PauloHenrique
 */
@WebServlet(name = "PermissoesServlet", urlPatterns = {"/PermissoesServlet"})
public class PermissoesServlet extends HttpServlet {

    private static final int INICIALIZA_TELA = 1;
    private static final int FILTRAR_TELA    = 2;
    private static final int APROVAR_VINCULO = 3;
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
        int TIPO_REQUISICAO = Integer.parseInt(request.getParameter("TIPO_REQUISICAO"));
        
        if (TIPO_REQUISICAO == INICIALIZA_TELA) {
            out.print(this.obterTodosItensPermissao());
        } else if (TIPO_REQUISICAO == FILTRAR_TELA) {
            String nome         = request.getParameter("NOME");
            String datini       = request.getParameter("DATINI");
            String datfim       = request.getParameter("DATFIM");
            String solicitante  = request.getParameter("SOLICITANTE");
            
            out.print(this.obterTodosItensPermissao(nome, datini, datfim, solicitante));
        } else if (TIPO_REQUISICAO == APROVAR_VINCULO) {
            int codusu = Integer.parseInt(request.getParameter("CODUSU"));
            this.aprovarVinculo(codusu);
        }
    }
    
    
    
    private JSONObject obterTodosItensPermissao() {
        ResultSet resultSet = Funcionario.obterTodosItensPermissao();
        return this.processarDadosGrid(resultSet);
    }
    
    private JSONObject obterTodosItensPermissao(String nome, String datini, String datfim, String solicitante) {
        ResultSet resultSet = Funcionario.obterTodosItensPermissao(nome, datini, datfim, solicitante);
        return this.processarDadosGrid(resultSet);
    }
    
    private JSONObject processarDadosGrid(ResultSet resultSet) {
        JSONArray array     = new JSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            while (resultSet.next()) {
                JSONObject aux = new JSONObject();
                aux.put("codusu", resultSet.getString("CODUSU"));
                aux.put("nome", resultSet.getString("NOMUSU"));
                aux.put("tipo", resultSet.getString("NOMTIPFUN"));
                aux.put("solicitante", resultSet.getString("SOLICITANTE"));
                Date auxDate = null;
                // Preparando e settando o valor de datini.
                auxDate = resultSet.getDate("DATINI");
                String date = (auxDate == null) ? null : dateFormat.format(auxDate);
                aux.put("datini", date);
                
                // Preparando e settando o valor de datfim.
                auxDate = resultSet.getDate("DATFIM");
                date = (auxDate == null) ? null : dateFormat.format(auxDate);
                aux.put("datfim", date);
                
                array.put(aux);
            }
            
            return new JSONObject().put("permissoes", array);
        } catch (JSONException ex) {
            Logger.getLogger(PermissoesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PermissoesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private void aprovarVinculo(int codusu) {
        String sql = " CODUSU = " + codusu + " AND DATINI IS NOT NULL AND DATFIM IS NULL";
        DaoFuncionario daoFuncionario = new DaoFuncionario();
        List<IEntity> result = daoFuncionario.obterEntidadeCondicaoWhere(sql);
        if (result.size() > 0) {
            FuncionarioEntity funcionarioAnterior = (FuncionarioEntity) result.get(0);
            funcionarioAnterior.setDatfim(new Date());
            daoFuncionario.persist(funcionarioAnterior);
        }
        
        sql = "CODUSU = " + codusu + " AND CODEMP = " + Ambiente.getInstance().getEmpresaEntity().getCodemp();
        FuncionarioEntity funcionarioEntity = (FuncionarioEntity) daoFuncionario.obterEntidadeCondicaoWhere(sql).get(0);
        funcionarioEntity.setDatini(new Date());
        daoFuncionario.persist(funcionarioEntity);
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
