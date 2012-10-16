/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlets;

import controller.Ambiente;
import dao.DaoAtividade;
import dao.DaoConvidado;
import dao.DaoLembrete;
import entity.AtividadeEntity;
import entity.ConvidadoEntity;
import entity.IEntity;
import entity.LembreteEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import model.Atividade;
import model.Cliente;
import model.Tipo_Atividade;
import util.org.json.JSONArray;
import util.org.json.JSONException;
import util.org.json.JSONObject;

/**
 *
 * @author PauloHenrique
 */
@WebServlet(name = "GerenciarAtividade", urlPatterns = {"/GerenciarAtividade"})
public class GerenciarAtividade extends HttpServlet {

    private static final int CARREGAR_TIPO_ATIVIDADE = 1;
    private static final int CARREGAR_ATIVIDADES     = 2;
    private static final int SALVAR_ATIVIDADE        = 3;
    private static final int CARREGAR_CLIENTES       = 4;
    private static final int SALVAR_CONVITES         = 5;
    private static final int ACEITAR_CONVITE         = 6;
    
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
            if (tipoRequisicao == CARREGAR_TIPO_ATIVIDADE) {
                out.print(this.obterTipoAtividade());
            } else if (tipoRequisicao == CARREGAR_ATIVIDADES) {
                int codemp    = Ambiente.getInstance().getEmpresaEntity().getCodemp();
                int codfunres = Ambiente.getInstance().getFuncionarioEntity().getCodfun();
                int codati    = Integer.parseInt(request.getParameter("CODATI"));
                int codcli    = Integer.parseInt(request.getParameter("CODCLI"));
                String nomati = request.getParameter("NOMATI");
                String status = request.getParameter("STATUS");
                String datini = request.getParameter("DATINI");
                String datfim = request.getParameter("DATFIM");

                out.print(this.obterAtividades(codemp, codfunres, codati, codcli, nomati, status, datini, datfim));
            } else if (tipoRequisicao == SALVAR_ATIVIDADE) {
                int codati       = Integer.parseInt(request.getParameter("CODATI"));
                String nomati    = request.getParameter("NOMATI");
                int codtipati    = Integer.parseInt(request.getParameter("CODTIPATI"));
                int codcli       = Integer.parseInt(request.getParameter("CODCLI"));
                String status    = request.getParameter("STATUS");
                String desati    = request.getParameter("DESATI");
                String datini    = request.getParameter("DATINI");
                String datfin    = request.getParameter("DATFIN");
                String obsandati = request.getParameter("OBSANDATI");

                this.salvarAtividade(codati, nomati, codtipati, codcli, status, desati, datini, datfin, obsandati);
            } else if (tipoRequisicao == CARREGAR_CLIENTES) {
                 out.print(this.obterClientes());
            } else if (tipoRequisicao == SALVAR_CONVITES) {
                int codati        = Integer.parseInt(request.getParameter("CODATI"));
                String descon     = request.getParameter("DESCON");
                String idtiplem   = request.getParameter("IDTIPLEM");
                String datinilem  = request.getParameter("DATINILEM");
                String convidados = request.getParameter("CONVIDADOS");
                String[] split    = convidados.split(",");

                this.enviarConvites(codati, descon, idtiplem, datinilem, split);
            } else if (tipoRequisicao == ACEITAR_CONVITE) {
                int codati = Integer.parseInt(request.getParameter("CODATI"));
                int codfun = Ambiente.getInstance().getFuncionarioEntity().getCodfun();
                this.aceitarConvite(codati, codfun);
            }
        }
    }
    
    private JSONObject obterTipoAtividade() {
        try {
            ResultSet resultSet = Tipo_Atividade.obterTipoAtividade();
            JSONArray array = new JSONArray();
            
            while (resultSet.next()) {
                JSONObject aux = new JSONObject();
                aux.put("codtipati", resultSet.getString("CODTIPATI"));
                aux.put("nomtipati", resultSet.getString("NOMTIPATI"));
                
                array.put(aux);
            }
            
            return new JSONObject().put("tipos", array);
        } catch (JSONException ex) {
            Logger.getLogger(GerenciarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private JSONObject obterAtividades(int codemp, int codfunres, int codati, int codcli, String nomati,
				       String status, String datini, String datfim) {
        try {
            ResultSet rs  = Atividade.obterAtividades(codemp, codfunres, codati, codcli, nomati, status, datini, datfim);
            JSONArray array = new JSONArray();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            while (rs.next()) {
                JSONObject aux = new JSONObject();
                
                aux.put("codati", rs.getString("CODATI"));
                aux.put("nomati", rs.getString("NOMATI"));
                aux.put("codtipati", rs.getString("CODTIPATI"));
                aux.put("nomtipati", rs.getString("NOMTIPATI"));
                aux.put("convite_aceito", rs.getString("CONVITE_ACEITO"));
                aux.put("status", rs.getString("STATUS"));
                aux.put("codfunres", rs.getString("CODFUNRES"));
                aux.put("nomusu", rs.getString("NOMUSU"));
                aux.put("desati", rs.getString("DESATI"));
                aux.put("codcli", rs.getString("CODCLI"));
                aux.put("nomcli", rs.getString("RAZSOC"));
                
                Date auxDate = rs.getDate("DATINI");
                String date = (auxDate == null) ? null : dateFormat.format(auxDate);
                aux.put("datini", date);
                
                auxDate = rs.getDate("DATFIN");
                date = (auxDate == null) ? null : dateFormat.format(auxDate);
                aux.put("datfin", date);
                aux.put("obsandati", rs.getString("OBSANDATI"));

                array.put(aux);
            }

            return new JSONObject().put("atividades", array);
        } catch (JSONException ex) {
            Logger.getLogger(GerenciarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void salvarAtividade(int codati, String nomati, int codtipati, int codcli, String status, String desati, 
                                 String datini, String datfin, String obsandati) {
        DaoAtividade daoAtividade       = new DaoAtividade();
        AtividadeEntity atividadeEntity = null;
        
        if (codati != 0 ) {
            Object[] pks = {codati};
            atividadeEntity = (AtividadeEntity) daoAtividade.obterEntidade(pks);
        } else {
            codati = util.Util.getNextValidKey("ATIVIDADE", "CODATI");
            atividadeEntity = new AtividadeEntity();
        }
        
        atividadeEntity.setCodati(codati);
        atividadeEntity.setNomati(nomati);
        atividadeEntity.setCodtipati(codtipati);
        atividadeEntity.setStatus(status);
        atividadeEntity.setDesati(desati);
        try {
            atividadeEntity.setDatini(new SimpleDateFormat("dd/MM/yyyy").parse(datini));
            atividadeEntity.setDatfin(new SimpleDateFormat("dd/MM/yyyy").parse(datfin));
        } catch (ParseException ex) {
            Logger.getLogger(GerenciarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
        atividadeEntity.setObsandati(obsandati);
        atividadeEntity.setCodemp(Ambiente.getInstance().getEmpresaEntity().getCodemp());
        atividadeEntity.setCodfunres(Ambiente.getInstance().getFuncionarioEntity().getCodfun());
        daoAtividade.persist(atividadeEntity);
    }
    
    private JSONObject obterClientes() {
        int codemp = Ambiente.getInstance().getEmpresaEntity().getCodemp();
        try {
            ResultSet rs    = Cliente.obterClientes(codemp);
            JSONArray array = new JSONArray();
            
            while (rs.next()) {
                JSONObject aux = new JSONObject();
                aux.put("codcli", rs.getString("CODCLI"));
                aux.put("razsoc", rs.getString("RAZSOC"));
                
                array.put(aux);
            }
            
            return new JSONObject().put("clientes", array);
        } catch (JSONException ex) {
            Logger.getLogger(GerenciarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void enviarConvites(int codati, String descon, String idtiplem, String datinilem, String[] convidados) {
         this.setConviteAtividade(codati, descon);
         for (String convidado: convidados) {
             this.salvarConvidado(Integer.parseInt(convidado), codati);
             this.salvarDadosLembrete(Integer.parseInt(convidado), codati, idtiplem, datinilem);
         }
    }
    
    private void setConviteAtividade(int codati, String convite) {
        Object[] pk = {codati};
        DaoAtividade daoAtividade       = new DaoAtividade();
        AtividadeEntity atividadeEntity = (AtividadeEntity) daoAtividade.obterEntidade(pk);
        atividadeEntity.setDescon(convite);
        daoAtividade.persist(atividadeEntity);        
    }
    
    private void salvarConvidado(int codfun, int codati) {
        DaoConvidado daoConvidado       = new DaoConvidado();
        ConvidadoEntity convidadoEntity = new ConvidadoEntity();
        
        convidadoEntity.setCodcon(util.Util.getNextValidKey("CONVIDADO", "CODCON"));
        convidadoEntity.setCodfun(codfun);
        convidadoEntity.setCodati(codati);
        convidadoEntity.setIdconace(ConvidadoEntity.CONVITE_NAO_ACEITO);
        daoConvidado.persist(convidadoEntity);
    }
    
    private void salvarDadosLembrete(int codfun, int codati, String idtiplem, String datinilem) {
        DaoLembrete daoLembrete       = new DaoLembrete();
        LembreteEntity lembreteEntity = new LembreteEntity();
        
        lembreteEntity.setCodfun(codfun);
        lembreteEntity.setCodati(codati);
        lembreteEntity.setIdtiplem(idtiplem);
        try {
            lembreteEntity.setDatinilem(new SimpleDateFormat("dd/MM/yyyy").parse(datinilem));
        } catch (ParseException ex) {
            Logger.getLogger(GerenciarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        daoLembrete.persist(lembreteEntity);
    }
    
    private void aceitarConvite(int codati, int codfun) {
        DaoConvidado daoConvidado = new DaoConvidado();
        List<IEntity> lista = daoConvidado.obterEntidadeCondicaoWhere(
            " CODATI = " + codati + " AND CODFUN = " + codfun
        );
        
        if (!lista.isEmpty()) {
            ConvidadoEntity convidadoEntity = new ConvidadoEntity();
            convidadoEntity.setIdconace(ConvidadoEntity.CONVITE_ACEITO);
            daoConvidado.persist(convidadoEntity);
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
