package test_mail_sender;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import controller.mail.EnvioDeEmailController;

import entity.LembreteEntity;

public class TestEnvioDeEmailController {
	private EnvioDeEmailController enviador;
	private DateFormat format;
	
	@Before
	public void before() {
		 this.enviador = new EnvioDeEmailController();
		 this.format   = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Test
	public void testValidaEmail() {
		// Testando um e-mail válido.
		String email = "paulo.phfp_26@yahoo.com.br";
		boolean emailValido = enviador.validaEmail(email);
		assertTrue(emailValido);
		
		// Teste de um e-mail inválido por não possui nome de usuário.
		email = "@yahoo.com.br";
		emailValido = enviador.validaEmail(email);
		assertFalse(emailValido);
		
		// Teste de e-mail inválido por não possui dominio.
		email = "paulo.phfp_26@";
		emailValido = enviador.validaEmail(email);
		assertFalse(emailValido);
		
		// Teste de email com caracteres inválidos.
		email = "paulo***@yahoo.com";
		emailValido = enviador.validaEmail(email);
		assertFalse(emailValido);
	}

	@Test
	public void testEnviarEmailNoDia() {
		// Teste de um caso de sucesso, onde o e-mail pode ser enviado.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		lembrete.setDatfimlem(null);
		try {
			lembrete.setDatinilem(this.format.parse("25/12/2012 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertTrue(lembreteEnviado);
	}
	
	@Test
	public void testEnviarEmailNoDiaDataDeEnvioAnterior() {
		// Teste de um caso de falha, onde a data de envio é de um dia anterior a hoje.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		lembrete.setDatfimlem(null);
		try {
			lembrete.setDatinilem(this.format.parse("01/01/2011 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertFalse(lembreteEnviado);
	}
	
	@Test
	public void testEnviarEmailNoDiaSemDataDeEnvio() {
		// Teste de um caso de falha, onde está faltando o e-mail do destinatário.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		lembrete.setDatfimlem(null);
		lembrete.setDatinilem(null);
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertFalse(lembreteEnviado);
	}
	
	@Test
	public void testEnviarEmailNoDiaSemDestinatario() {
		// Teste de um caso de falha, onde está faltando o e-mail do destinatário.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(5);
		lembrete.setDatfimlem(null);
		try {
			lembrete.setDatinilem(this.format.parse("25/12/2012 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertFalse(lembreteEnviado);
		
	}
	
	@Test
	public void testEnviarEmailNoDiaSemAssunto() {
		// Teste de um caso de falha, onde está faltando o assunto do e-mail.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		lembrete.setDatfimlem(null);
		try {
			lembrete.setDatinilem(this.format.parse("25/12/2012 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem(null);
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertFalse(lembreteEnviado);
	}	
	
	@Test
	public void testEnviarEmailNoDiaSemTexto() {
		// Teste de um caso de falha, onde está faltando o texto do e-mail.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		lembrete.setDatfimlem(null);
		try {
			lembrete.setDatinilem(this.format.parse("25/12/2012 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem(null);
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertFalse(lembreteEnviado);
	}
	
	@Test
	public void testEnviarEmailNoIntervalo() {
		// Teste de sucesso.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		try {
			lembrete.setDatinilem(this.format.parse("25/12/2012 00:00:00"));
			lembrete.setDatfimlem(this.format.parse("31/12/2012 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertTrue(lembreteEnviado);
	}
	
	@Test
	public void testEnviarEmailNoIntervaloDataUltrapassada() {
		// Teste de falha: A data incial já foi ultrapassada.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		try {
			lembrete.setDatinilem(this.format.parse("25/12/2011 00:00:00"));
			lembrete.setDatfimlem(this.format.parse("31/12/2012 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		System.err.println(lembreteEnviado);
		assertFalse(lembreteEnviado);
	}
	
	@Test
	public void testEnviarEmailNoIntervaloDataFinalUltrapassada() {
		// Teste de falha: A data final é anterior a data inicial. O objetivo é 
		// verificar se é um intervalo possivel de ser coberto.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		try {
			lembrete.setDatinilem(this.format.parse("25/12/2012 00:00:00"));
			lembrete.setDatfimlem(this.format.parse("31/12/2011 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_COMUM);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertFalse(lembreteEnviado);
	}
	
	@Test
	public void testEnviarEmailExcetoFimDeSemana() {
		// Teste de sucesso.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		lembrete.setDatfimlem(null);
		try {
			lembrete.setDatinilem(this.format.parse("25/12/2012 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_EXCETO_FIM_DE_SEMANA);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertTrue(lembreteEnviado);
	}
	
	@Test
	public void testEnviarEmailNoIntervaloExcetoFimDeSemana() {
		// Teste de sucesso.
		LembreteEntity lembrete= new LembreteEntity();
		lembrete.setCodati(1);
		lembrete.setCodfun(1);
		try {
			lembrete.setDatfimlem(this.format.parse("31/12/2012 00:00:00"));
			lembrete.setDatinilem(this.format.parse("25/12/2012 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lembrete.setIdtiplem(LembreteEntity.TIPO_LEMBRETE_EXCETO_FIM_DE_SEMANA);
		lembrete.setTexlem("teste de envio de email.");
		lembrete.setTitlem("Teste de envio de email.");
		boolean lembreteEnviado = this.enviador.enviarEmail(lembrete);
		assertTrue(lembreteEnviado);
	}
	
}
