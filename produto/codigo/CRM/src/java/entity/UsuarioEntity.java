/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PauloHenrique
 */
public class UsuarioEntity extends AbstractEntity {
    private int codusu;
    private String nomusu;
    private String logusu;
    private String senusu;
    private String idusuativ;
    
    public static final String IDSITUATIV_ATIVO   = "A";
    public static final String IDSITUATIV_INATIVO = "I";

    public int getCodusu() {
        return codusu;
    }

    public void setCodusu(int codusu) {
        this.codusu = codusu;
    }

    public String getNomusu() {
        return nomusu;
    }

    public void setNomusu(String nomusu) {
        this.nomusu = nomusu;
    }

    public String getLogusu() {
        return logusu;
    }

    public void setLogusu(String logusu) {
        this.logusu = logusu;
    }

    public String getSenusu() {
        return senusu;
    }

    public void setSenusu(String senusu) {
        this.senusu = senusu;
    }

    public String getIdusuativ() {
        return idusuativ;
    }

    public void setIdusuativ(String idusuativ) {
        this.idusuativ = idusuativ;
    }
    
    
}
