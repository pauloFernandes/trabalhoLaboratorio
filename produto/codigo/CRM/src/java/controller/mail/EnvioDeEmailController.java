package controller.mail;

import java.util.Date;

import dao.DaoFuncionario;
import entity.FuncionarioEntity;
import entity.LembreteEntity;

public class EnvioDeEmailController {

	private String[] listaCaracteresInvalidos = {"!", "#", "$", "$", "¨", "&", "*", "(", ")", "+"};
	
	public boolean validaEmail(String email) {
		try {
			if (email.startsWith("@")) {
				throw new Exception("Email não possui usuario de email.");
			}
			
			if (email.endsWith("@")) {
				throw new Exception("Email não possui dominio.");
			}
			
			for (String caractereInvalido : listaCaracteresInvalidos) {
				if (email.contains(caractereInvalido)) {
					throw new Exception("O caractere " + caractereInvalido + " não pode ser usado " +
										"para endereço de email");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
	
	public boolean enviarEmail(LembreteEntity lembrete) {
		Agendador agendador           = new Agendador();
		Object[] pkFuncionario        = {lembrete.getCodfun()};
		DaoFuncionario daoFuncionario = new DaoFuncionario();
		FuncionarioEntity funcionario = (FuncionarioEntity) daoFuncionario.obterEntidade(pkFuncionario);
		
		try {
			this.validaEnvio(lembrete, funcionario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		if (lembrete.getDatfimlem() == null) {
			if (lembrete.getIdtiplem().equals(LembreteEntity.TIPO_LEMBRETE_EXCETO_FIM_DE_SEMANA)) {
				return agendador.enviarExcetoFimDeSemana(lembrete.getDatinilem(), funcionario.getMailfun(), lembrete.getTitlem(), lembrete.getTexlem());
			} else {				
				return agendador.enviarDia(
					lembrete.getDatinilem(), funcionario.getMailfun(), lembrete.getTitlem(), lembrete.getTexlem()
				);
			}
		} else {
			return agendador.enviarNoIntervalo (
				lembrete.getDatinilem(), lembrete.getDatfimlem(), 
				funcionario.getMailfun(), lembrete.getTitlem(), lembrete.getTexlem(),
				lembrete.getIdtiplem().equals(LembreteEntity.TIPO_LEMBRETE_EXCETO_FIM_DE_SEMANA)
			);
		}
	}
	
	public void validaEnvio(LembreteEntity lembrete, FuncionarioEntity funcionario) throws Exception {
		if (lembrete.getDatinilem() == null) {
			throw new Exception("A data de envio do email deve ser definida");
		}
		
		if (lembrete.getDatinilem().before(new Date())) {
			throw new Exception("A data inciial deve ser posterior a data de hoje.");
		}
		
		if (lembrete.getDatfimlem() != null && lembrete.getDatfimlem().before(new Date())) {
			throw new Exception("A data final deve ser uma data posterior a data inicial.");
		}
		
		if (lembrete.getTitlem() == null) {
			throw new Exception("Nenhum assunto foi informado para o email.");
		}
		
		if (lembrete.getTexlem() == null) {
			throw new Exception("Nenhum texto foi informado para o email.");
		}
		
		if (funcionario.getMailfun() == null) {
			throw new Exception("Nenhum email valido foi adicionado para o envio do email.");
		}
	}

}
