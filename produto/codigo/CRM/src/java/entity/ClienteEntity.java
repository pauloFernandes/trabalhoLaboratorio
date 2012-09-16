/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PauloHenrique
 */
public class ClienteEntity extends AbstractEntity {
    private int codcli;
    private int codemp;
    private String numinsjur;
    private String nomfan;
    private String razsoc;
    private String telemp;
    private String celemp;
    private String desend;
    private int codmun;
    private int codest;
    private int codpai;

    public int getCodcli() {
        return codcli;
    }

    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }

    public int getCodemp() {
        return codemp;
    }

    public void setCodemp(int codemp) {
        this.codemp = codemp;
    }

    public String getNuminsjur() {
        return numinsjur;
    }

    public void setNuminsjur(String numinsjur) {
        this.numinsjur = numinsjur;
    }

    public String getNomfan() {
        return nomfan;
    }

    public void setNomfan(String nomfan) {
        this.nomfan = nomfan;
    }

    public String getRazsoc() {
        return razsoc;
    }

    public void setRazsoc(String razsoc) {
        this.razsoc = razsoc;
    }

    public String getTelemp() {
        return telemp;
    }

    public void setTelemp(String telemp) {
        this.telemp = telemp;
    }

    public String getCelemp() {
        return celemp;
    }

    public void setCelemp(String celemp) {
        this.celemp = celemp;
    }

    public String getDesend() {
        return desend;
    }

    public void setDesend(String desend) {
        this.desend = desend;
    }

    public int getCodmun() {
        return codmun;
    }

    public void setCodmun(int codmun) {
        this.codmun = codmun;
    }

    public int getCodest() {
        return codest;
    }

    public void setCodest(int codest) {
        this.codest = codest;
    }

    public int getCodpai() {
        return codpai;
    }

    public void setCodpai(int codpai) {
        this.codpai = codpai;
    }
    
}
