/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PauloHenrique
 */
public class Tipo_AtividadeEntity extends AbstractEntity {
    private int codtipati;
    private String nomtipati;

    public int getCodtipati() {
        return codtipati;
    }

    public void setCodtipati(int codtipati) {
        this.codtipati = codtipati;
    }

    public String getNomtipati() {
        return nomtipati;
    }

    public void setNomtipati(String nomtipati) {
        this.nomtipati = nomtipati;
    }
    
    
}
