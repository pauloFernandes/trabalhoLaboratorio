/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import dao.DaoFuncionario;
import entity.FuncionarioEntity;
import entity.IEntity;
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

    private static final int INICIALIZA_TELA    = 1;
    private static final int FILTRAR_TELA       = 2;
    private static final int APROVAR_VINCULO    = 3;
    private static final int REPROVAR_VINCULO   = 4;
    private static final int ATRIBUIR_PERMISSAO = 5;
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
        
        if (tipoRequisicao == INICIALIZA_TELA) {
            out.print(this.obterTodosItensPermissao());
        } else if (tipoRequisicao == FILTRAR_TELA) {
            String nome         = request.getParameter("NOME");
            String datini       = request.getParameter("DATINI");
            String datfim       = request.getParameter("DATFIM");
            String solicitante  = request.getParameter("SOLICITANTE");
            
            out.print(this.obterTodosItensPermissao(nome, datini, datfim, solicitante));
        } else if (tipoRequisicao == APROVAR_VINCULO) {
            int codusu = Integer.parseInt(request.getParameter("CODUSU"));
            this.aprovarVinculo(codusu);
        } else if (tipoRequisicao == REPROVAR_VINCULO) {
            int codusu = Integer.parseInt(request.getParameter("CODUSU"));
            this.reporvarVinculo(codusu);
        } else if (tipoRequisicao == ATRIBUIR_PERMISSAO) {
            int codusu          = Integer.parseInt(request.getParameter("CODUSU"));
            int tipoFuncionario = Integer.parseInt(request.getParameter("TIPO_FUNCIONARIO"));
            this.atribuirPermissao(codusu, tipoFuncionario);
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
    
    private void reporvarVinculo(int codusu) {
        DaoFuncionario daoFuncionario = new DaoFuncionario();
        String sql = "CODUSU = " + codusu + " AND CODEMP = " + Ambiente.getInstance().getEmpresaEntity().getCodemp();
        FuncionarioEntity funcionarioEntity = (FuncionarioEntity) daoFuncionario.obterEntidadeCondicaoWhere(sql).get(0);
        daoFuncionario.delete(funcionarioEntity);
    }
    
    private void atribuirPermissao(int codusu, int tipoFuncioario) {
        String where = "CODUSU = " + codusu + " AND CODEMP = " + Ambiente.getInstance().getEmpresaEntity().getCodemp() ;
        DaoFuncionario daoFuncionario = new DaoFuncionario();
        FuncionarioEntity funcionarioEntity = (FuncionarioEntity) daoFuncionario.obterEntidadeCondicaoWhere(where).get(0);
        
        funcionarioEntity.setCodtipfun(tipoFuncioario);
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
