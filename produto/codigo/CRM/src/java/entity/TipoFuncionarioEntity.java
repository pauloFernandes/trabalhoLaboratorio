/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PauloHenrique
 */
public class TipoFuncionarioEntity extends AbstractEntity {
    private int codtipfun;
    private String nomtipfun;

    public int getCodtipfun() {
        return codtipfun;
    }

    public void setCodtipfun(int codtipfun) {
        this.codtipfun = codtipfun;
    }

    public String getNomtipfun() {
        return nomtipfun;
    }

    public void setNomtipfun(String nomtipfun) {
        this.nomtipfun = nomtipfun;
    }

    
}
