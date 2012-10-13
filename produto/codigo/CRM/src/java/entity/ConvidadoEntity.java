/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PauloHenrique
 */
public class ConvidadoEntity extends AbstractEntity {
    private int codcon;
    private int codfun;
    private int codati;
    private String idconace;

    public static final String CONVITE_ACEITO     = "S";
    public static final String CONVITE_NAO_ACEITO = "N";
    
    public int getCodati() {
        return codati;
    }

    public void setCodati(int codati) {
        this.codati = codati;
    }

    public String getIdconace() {
        return idconace;
    }

    public void setIdconace(String idconace) {
        this.idconace = idconace;
    }

    public int getCodcon() {
        return codcon;
    }

    public void setCodcon(int codcon) {
        this.codcon = codcon;
    }

    public int getCodfun() {
        return codfun;
    }

    public void setCodfun(int codfun) {
        this.codfun = codfun;
    }
    
    
}
