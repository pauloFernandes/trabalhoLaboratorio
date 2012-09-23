/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author PauloHenrique
 */
public class DaoFactory {
    public static final int ATIVIDADE        = 1;
    public static final int CLIENTE          = 2;
    public static final int CONTATO          = 3;
    public static final int CONVIDADO        = 4;
    public static final int LEMBRETE         = 5;
    public static final int EMPRESA          = 6;
    public static final int ESTADO           = 7;
    public static final int FUN_RESPONSAVEL  = 8;
    public static final int FUNCIONARIO      = 9;
    public static final int MUNICIPIO        = 10;
    public static final int PAIS             = 11;
    public static final int TIPO_ATIVIDADE   = 12;
    public static final int TIPO_FUNCIONARIO = 13;
    public static final int USUARIO          = 14;
    
    public static CrmIDao getInstance(int opcao) {
        CrmIDao daoInstance = null;
        
        switch(opcao) {
            case DaoFactory.ATIVIDADE:
                daoInstance = new DaoAtividade();
                break;
            case DaoFactory.CLIENTE:
                daoInstance = new DaoCliente();
                break;
            case DaoFactory.CONTATO:
                daoInstance = new DaoContato();
                break;
            case DaoFactory.CONVIDADO:
                daoInstance = new DaoConvidado();
                break;
            case DaoFactory.LEMBRETE:
                 daoInstance = new DaoLembrete();
                break;
            case DaoFactory.EMPRESA:
                daoInstance = new DaoEmpresa();
                break;
            case DaoFactory.ESTADO:
                daoInstance = new DaoEstado();
                break;
            case DaoFactory.FUNCIONARIO:
                daoInstance = new DaoFuncionario();
                break;
            case DaoFactory.FUN_RESPONSAVEL:
                daoInstance = new DaoFun_Responsavel();
                break;
            case DaoFactory.MUNICIPIO:
                daoInstance = new DaoMunicipio();
                break;
            case DaoFactory.PAIS:
                daoInstance = new DaoPais();
                break;
            case DaoFactory.TIPO_ATIVIDADE:
                daoInstance = new DaoTipo_Atividade();
                break;
            case DaoFactory.TIPO_FUNCIONARIO:
                daoInstance = new DaoTipofuncionario();
                break;
            case DaoFactory.USUARIO:
                daoInstance = new DaoUsuario();
                break;
        }
        
        return daoInstance;
    }
    
    
}
