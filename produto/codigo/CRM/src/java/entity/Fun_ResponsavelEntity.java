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
public class Fun_ResponsavelEntity extends AbstractEntity {
    private int codcli;
    private int codfun;
    private int codemp;
    private Date datini;
    private Date datfim;

    public int getCodcli() {
        return codcli;
    }

    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }

    public int getCodfun() {
        return codfun;
    }

    public void setCodfun(int codfun) {
        this.codfun = codfun;
    }

    public Date getDatini() {
        return datini;
    }

    public int getCodemp() {
        return codemp;
    }

    public void setCodemp(int codemp) {
        this.codemp = codemp;
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
