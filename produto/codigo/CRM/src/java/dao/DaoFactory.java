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
    private static final int ATIVIDADE = 1;
    private static final int CLIENTE = 2;
    private static final int CONTATO = 3;
    private static final int CONVIDADO = 4;
    private static final int CONVITE = 5;
    private static final int EMPRESA = 6;
    private static final int ESTADO = 7;
    private static final int FUN_RESPONSAVEL = 8;
    private static final int FUNCIONARIO = 9;
    private static final int MUNICIPIO = 10;
    private static final int PAIS = 11;
    private static final int TIPO_ATIVIDADE = 12;
    private static final int TIPO_FUNCIONARIO = 13;
    private static final int USUARIO = 14;
    
    public static CrmIDao getInstance(int opcao) {
        CrmIDao daoInstance = null;
        
        switch(opcao) {
//            case DaoFactory.ATIVIDADE:
//                daoInstance = new DaoAtividade();
//                break;
//            case DaoFactory.CLIENTE:
//                daoInstance = new DaoCliente();
//                break;
//            case DaoFactory.CONTATO:
//                daoInstance = new DaoContato();
//                break;
//            case DaoFactory.CONVIDADO:
//                daoInstance = new DaoConvidado();
//                break;
//            case DaoFactory.CONVITE:
//                daoInstance = new DaoConvite();
//                break;
//            case DaoFactory.EMPRESA:
//                daoInstance = new DaoEmpresa();
//                break;
//            case DaoFactory.ESTADO:
//                daoInstance = new DaoEstado();
//                break;
//            case DaoFactory.FUNCIONARIO:
//                daoInstance = new DaoFuncionario();
//                break;
//            case DaoFactory.FUN_RESPONSAVEL:
//                daoInstance = new DaoFun_Responsavel();
//                break;
//            case DaoFactory.MUNICIPIO:
//                daoInstance = new DaoMunicipio();
//                break;
            case DaoFactory.PAIS:
                daoInstance = new DaoPais();
                break;
//            case DaoFactory.TIPO_ATIVIDADE:
//                daoInstance = new DaoTipo_Atividade();
//                break;
//            case DaoFactory.TIPO_FUNCIONARIO:
//                daoInstance = new DaoTipofuncionario();
//                break;
//            case DaoFactory.USUARIO:
//                daoInstance = new DaoUsuario();
//                break;
        }
        
        return daoInstance;
    }
    
    
}
