/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PauloHenrique
 */
public class MunicipioEntity extends AbstractEntity {
    private int codmun;
    private int codest;
    private int codpai;
    private String nommun;

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

    public String getNommun() {
        return nommun;
    }

    public void setNommun(String nommun) {
        this.nommun = nommun;
    }
    
    
}
