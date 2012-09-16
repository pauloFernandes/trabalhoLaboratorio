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
public class ContatoEntity extends AbstractEntity {
    private int codcon;
    private int codcli;
    private String nomcon;
    private Date datnas;
    private String telcon;
    private String celcon;
    private String mailcon;

    public int getCodcon() {
        return codcon;
    }

    public void setCodcon(int codcon) {
        this.codcon = codcon;
    }

    public int getCodcli() {
        return codcli;
    }

    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }

    public String getNomcon() {
        return nomcon;
    }

    public void setNomcon(String nomcon) {
        this.nomcon = nomcon;
    }

    public Date getDatnas() {
        return datnas;
    }

    public void setDatnas(Date datnas) {
        this.datnas = datnas;
    }

    public String getTelcon() {
        return telcon;
    }

    public void setTelcon(String telcon) {
        this.telcon = telcon;
    }

    public String getCelcon() {
        return celcon;
    }

    public void setCelcon(String celcon) {
        this.celcon = celcon;
    }

    public String getMailcon() {
        return mailcon;
    }

    public void setMailcon(String mailcon) {
        this.mailcon = mailcon;
    }
    
    
}
