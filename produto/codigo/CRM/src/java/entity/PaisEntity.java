/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PauloHenrique
 */
public class PaisEntity extends AbstractEntity {
    private int codpai;
    private String nompai;

    public int getCodpai() {
        return codpai;
    }

    public void setCodpai(int codpai) {
        this.codpai = codpai;
    }

    public String getNompai() {
        return nompai;
    }

    public void setNompai(String nompai) {
        this.nompai = nompai;
    }
    
    
}
