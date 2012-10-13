/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author PauloHenrique
 */
public class AtividadeEntity extends AbstractEntity {
    private int codati;
    private String nomati;
    private String desati;
    private int codemp;
    private int codcli;
    private Date datini;
    private Date datfin;
    private int codtipati;
    private String obsandati;
    private String status;
    private String descon;
    private int codfunres;

    public int getCodati() {
        return codati;
    }

    public void setCodati(int codati) {
        this.codati = codati;
    }

    public String getNomati() {
        return nomati;
    }

    public void setNomati(String nomati) {
        this.nomati = nomati;
    }

    public String getDesati() {
        return desati;
    }

    public void setDesati(String desati) {
        this.desati = desati;
    }

    public Date getDatini() {
        return datini;
    }

    public void setDatini(Date datini) {
        this.datini = datini;
    }

    public Date getDatfin() {
        return datfin;
    }

    public void setDatfin(Date datfin) {
        this.datfin = datfin;
    }

    public int getCodtipati() {
        return codtipati;
    }

    public void setCodtipati(int codtipati) {
        this.codtipati = codtipati;
    }

    public String getObsandati() {
        return obsandati;
    }

    public void setObsandati(String obsandati) {
        this.obsandati = obsandati;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCodfunres() {
        return codfunres;
    }

    public void setCodfunres(int codfunres) {
        this.codfunres = codfunres;
    }

    public int getCodemp() {
        return codemp;
    }

    public void setCodemp(int codemp) {
        this.codemp = codemp;
    }

    public int getCodcli() {
        return codcli;
    }

    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }

    public String getDescon() {
        return descon;
    }

    public void setDescon(String descon) {
        this.descon = descon;
    }
   
}
