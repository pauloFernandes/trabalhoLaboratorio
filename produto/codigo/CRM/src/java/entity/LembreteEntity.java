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
public class LembreteEntity extends AbstractEntity {
    private int codfun;
    private int codati;
    private String idtiplem;
    private Date datinilem;

    public int getCodfun() {
        return codfun;
    }

    public void setCodfun(int codfun) {
        this.codfun = codfun;
    }

    public int getCodati() {
        return codati;
    }

    public void setCodati(int codati) {
        this.codati = codati;
    }

    public String getIdtiplem() {
        return idtiplem;
    }

    public void setIdtiplem(String idtiplem) {
        this.idtiplem = idtiplem;
    }

    public Date getDatinilem() {
        return datinilem;
    }

    public void setDatinilem(Date datinilem) {
        this.datinilem = datinilem;
    }
    
    
}
