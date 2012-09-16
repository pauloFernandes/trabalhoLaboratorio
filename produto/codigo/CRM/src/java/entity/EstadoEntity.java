/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PauloHenrique
 */
public class EstadoEntity extends AbstractEntity {
    private int codest;
    private int codpai;
    private String nomest;

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

    public String getNomest() {
        return nomest;
    }

    public void setNomest(String nomest) {
        this.nomest = nomest;
    }
    
    
}
