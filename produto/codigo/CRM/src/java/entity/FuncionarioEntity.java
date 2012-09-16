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
public class FuncionarioEntity extends AbstractEntity {
    private int codfun;
    private int codusu;
    private int codemp;
    private int codtipfun;
    private Date datini;
    private Date datfim;

    public int getCodfun() {
        return codfun;
    }

    public void setCodfun(int codfun) {
        this.codfun = codfun;
    }

    public int getCodusu() {
        return codusu;
    }

    public void setCodusu(int codusu) {
        this.codusu = codusu;
    }

    public int getCodemp() {
        return codemp;
    }

    public void setCodemp(int codemp) {
        this.codemp = codemp;
    }

    public int getCodtipfun() {
        return codtipfun;
    }

    public void setCodtipfun(int codtipfun) {
        this.codtipfun = codtipfun;
    }

    public Date getDatini() {
        return datini;
    }

    public void setDatini(Date datini) {
        this.datini = datini;
    }

    public Date getDatfim() {
        return datfim;
    }

    public void setDatfim(Date datfim) {
        this.datfim = datfim;
    }
    
    
}
