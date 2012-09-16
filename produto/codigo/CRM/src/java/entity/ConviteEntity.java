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
public class ConviteEntity extends AbstractEntity {
    private int codcon;
    private int codati;
    private String descon;
    private String tipenvlem;
    private Date datenvlem;
    
    /**
     * Indica que os convites serao enviados somente no dia do evento.
     */
    public static final int TIPENVLEM_NO_DIA     = 1;
    /**
     * Indica que os convites serao enviados todos os dias, a partir de um 
     * dia ate o dia do evento.
     */
    private static final int TIPENVLEM_TODO_DIAS = 2;

    public int getCodcon() {
        return codcon;
    }

    public void setCodcon(int codcon) {
        this.codcon = codcon;
    }

    public int getCodati() {
        return codati;
    }

    public void setCodati(int codati) {
        this.codati = codati;
    }

    public String getDescon() {
        return descon;
    }

    public void setDescon(String descon) {
        this.descon = descon;
    }

    public String getTipenvlem() {
        return tipenvlem;
    }

    public void setTipenvlem(String tipenvlem) {
        this.tipenvlem = tipenvlem;
    }

    public Date getDatenvlem() {
        return datenvlem;
    }

    public void setDatenvlem(Date datenvlem) {
        this.datenvlem = datenvlem;
    }
    
    
}
