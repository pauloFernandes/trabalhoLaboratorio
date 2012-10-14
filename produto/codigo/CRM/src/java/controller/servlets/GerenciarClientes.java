/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import dao.DaoCliente;
import dao.DaoFun_Responsavel;
import entity.ClienteEntity;
import entity.Fun_ResponsavelEntity;
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
import model.Empresa;
import model.Fun_Responsavel;
import model.Funcionario;
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
    private static int HISTORICO_VENDEDORES     = 2;
    private static int PESQUISAR_CLIENTES       = 3;
    private static int LISTA_VENDEDORES         = 4;
    private static int SALVAR_CLIENTE           = 5;
    
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
        
        if (Ambiente.validaPermissaoUsuario(Ambiente.PERMISSAO_FUNCIONARIO)) {
            if (tipoRequisicao == INICIALIZAR_CLIENTE_GRID) {
                    out.print(this.obterTodosClientes());
            } else if (tipoRequisicao == HISTORICO_VENDEDORES) {
                int codcli = Integer.parseInt(request.getParameter("CODCLI"));
                out.print(this.obterHistoricoVendedores(codcli));
            } else if (tipoRequisicao == PESQUISAR_CLIENTES) {
                String nomfan  = (request.getParameter("NOMFAN"));
                String razsoc  = (request.getParameter("RAZSOC"));
                String  insjur = (request.getParameter("INSJUR"));
                String nomven  = (request.getParameter("NOMVEN"));

                 out.print(this.obterClientes(nomfan, razsoc, insjur, nomven));
            } else if (tipoRequisicao == LISTA_VENDEDORES) {
                out.print(this.listaVendedores());
            } else if (tipoRequisicao == SALVAR_CLIENTE) {
                int codcli    = Integer.parseInt(request.getParameter("CODCLI"));
                int codemp    = Ambiente.getInstance().getEmpresaEntity().getCodemp();
                String nomfan = request.getParameter("NOMFAN");
                String razsoc = request.getParameter("RAZSOC");
                String insjur = request.getParameter("INSJUR");
                String telemp = request.getParameter("TELEMP");
                String celemp = request.getParameter("CELEMP");
                int vendedor  = Integer.parseInt(request.getParameter("VENDEDOR"));

                this.salvar(codcli, codemp, nomfan, razsoc, insjur, telemp, celemp, vendedor);
            }
        }
    }
    
    private JSONObject obterTodosClientes() {
        int codemp = Ambiente.getInstance().getEmpresaEntity().getCodemp();
        ResultSet resultSet = Empresa.obterTodosClientes(codemp);
        return this.constroiObjeto(resultSet);
    }

    private JSONObject obterClientes(String nomfan, String razsoc, String insjur, String nomven) {
        int codemp = Ambiente.getInstance().getEmpresaEntity().getCodemp();
        ResultSet resultSet = Empresa.obterClientes(codemp, nomfan, razsoc, insjur, nomven);
        return this.constroiObjeto(resultSet);
    }
    
    private JSONObject obterHistoricoVendedores(int codcli) {
        int codemp                  = Ambiente.getInstance().getEmpresaEntity().getCodemp();
        ResultSet resultSet         = Fun_Responsavel.obterHistoricoVendedores(codcli, codemp);
        JSONArray array             = new JSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            while(resultSet.next()) {
                JSONObject aux = new JSONObject();
                aux.put("nomven", resultSet.getString("NOMUSU"));
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
            
            return new JSONObject().put("vendedores", array);
        } catch (JSONException ex) {
            Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private JSONObject constroiObjeto(ResultSet resultSet) {
        JSONArray array = new JSONArray();
        
        try {
            while (resultSet.next()) {
                JSONObject aux = new JSONObject();
                aux.put("codcli", resultSet.getString("CODCLI"));
                aux.put("nomfan", resultSet.getString("NOMFAN"));
                aux.put("razsoc", resultSet.getString("RAZSOC"));
                aux.put("numinsjur", resultSet.getString("NUMINSJUR"));
                aux.put("codven", resultSet.getString("CODFUN"));
                aux.put("nomven", resultSet.getString("NOMUSU"));
                aux.put("telemp", resultSet.getString("TELEMP"));
                aux.put("celemp", resultSet.getString("CELEMP"));
                
                array.put(aux);
            }
            
            return new JSONObject().put("clientes", array);
        } catch (JSONException e) {
            String test = e.getMessage();
            Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
    }
    
    private JSONObject listaVendedores() {
        int codemp = Ambiente.getInstance().getEmpresaEntity().getCodemp();
        ResultSet resultSet = Funcionario.obterTodosVendedores(codemp);
        JSONArray array = new JSONArray();
        try {
                while (resultSet.next()) {
                JSONObject aux = new JSONObject();
                aux.put("codfun", resultSet.getInt("CODFUN"));
                aux.put("nomusu", resultSet.getString("NOMUSU"));
                
                array.put(aux);
            }
            
            return new JSONObject().put("vendedores", array);
        } catch (JSONException ex) {
            Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void salvar(int codcli, int codemp, String nomfan, String razsoc, String insjur, 
                               String telemp, String celemp, int vendedor) {
        codcli = this.salvarCliente(codcli, codemp, nomfan, razsoc, insjur, telemp, celemp);
        this.salvarFuncResponsavel(codcli, codemp, vendedor);
    }
    
    private int salvarCliente(int codcli, int codemp, String nomfan, String razsoc, String insjur, 
                               String telemp, String celemp) {
        DaoCliente daoCliente = new DaoCliente();
        ClienteEntity clienteEntity = null;
        if (codcli != 0) {
            Object[] valorPks = {codcli};
            clienteEntity = (ClienteEntity) daoCliente.obterEntidade(valorPks);
        } else {
            codcli = util.Util.getNextValidKey("CLIENTE", "CODCLI");
            clienteEntity = new ClienteEntity();
        }
        
        clienteEntity.setCodcli(codcli);
        clienteEntity.setCodemp(codemp);
        clienteEntity.setNomfan(nomfan);
        clienteEntity.setRazsoc(razsoc);
        clienteEntity.setNuminsjur(insjur);
        clienteEntity.setTelemp(telemp);
        clienteEntity.setCelemp(celemp);
        daoCliente.persist(clienteEntity);
        return codcli;
    } 
    
    private void salvarFuncResponsavel(int codcli, int codemp, int vendedor) {
        String where = " CODCLI = " + codcli + " AND CODEMP = " + codemp + " AND DATINI IS NOT NULL AND DATFIM IS NULL";
        DaoFun_Responsavel daoFun_Responsavel       = new DaoFun_Responsavel();
        List<IEntity> list            = daoFun_Responsavel.obterEntidadeCondicaoWhere(where);
        
        if (!list.isEmpty()) {
            Fun_ResponsavelEntity fun_ResponsavelEntity = (Fun_ResponsavelEntity) list.get(0);
            fun_ResponsavelEntity.setDatfim(new Date());
            daoFun_Responsavel.persist(fun_ResponsavelEntity);
        }
        
        Fun_ResponsavelEntity fun_ResponsavelEntity = new Fun_ResponsavelEntity();
        fun_ResponsavelEntity.setCodcli(codcli);
        fun_ResponsavelEntity.setCodfun(vendedor);
        fun_ResponsavelEntity.setCodemp(codemp);
        fun_ResponsavelEntity.setDatini(new Date());
        daoFun_Responsavel.persist(fun_ResponsavelEntity);
        
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
