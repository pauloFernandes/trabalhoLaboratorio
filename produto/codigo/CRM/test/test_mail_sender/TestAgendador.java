package test_mail_sender;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import controller.mail.Agendador;

public class TestAgendador {
	
	private Agendador agendador;
	private Date dataInicial;
	private Date dataFinal;
	private String email;
	private String assunto;
	private String texto;
	
	@Before
	public void before() 
	{
		this.agendador = new Agendador();
		
		try {
			this.dataInicial = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2013 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dataFinal   = null;
		this.email       = "paulo.phfp_26@yahoo.com.br";
		this.assunto     = "Teste de Envio de E-mail";
		this.texto       = "Corpo do E-mail";
	}
	
	@Test
	public void testEnviarDia() 
	{
		// Testar caso normal, onde a data agendada é atingivel.
		boolean enviado = this.agendador.enviarDia(this.dataInicial, this.email, this.assunto, this.texto);
		assertTrue(enviado);
		
		// Testar casos onde a data já tenha passado, ou seja, o evento agendado não pode ser disparado. Isto é necessário
		// porque, a classe Timer, ao capturar uma data já passada para o evento, ele dispara este evento imediatamente.
		try {
			this.dataInicial = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("13/01/2010 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		enviado = this.agendador.enviarDia(this.dataInicial, this.email, this.assunto, this.texto);
		assertFalse(enviado);
	}
	
	@Test
	public void enviarExcetoFimDeSemana() 
	{
		try {
			// data para testar o caso de sábado.
			this.dataInicial = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("10/11/2012 00:00:00");
			this.agendador.enviarExcetoFimDeSemana(this.dataInicial, this.email, this.assunto, this.texto);
			
			// data para testar o caso de domingo.
			this.dataInicial = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("11/11/2012 00:00:00");
			this.agendador.enviarExcetoFimDeSemana(this.dataInicial, this.email, this.assunto, this.texto);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEnviarNoIntervalo() 
	{
		try {
			this.dataFinal = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("13/01/2013 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		boolean enviado = this.agendador.enviarNoIntervalo(
			this.dataInicial, this.dataFinal, this.email, this.assunto, this.texto, false
		);
		assertTrue(enviado);
	}
	
	@Test
	public void testEnviarNoIntervaloExcetoFimDeSemana() 
	{
		try {
			this.dataFinal = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("13/01/2013 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean enviado = this.agendador.enviarNoIntervalo(
			this.dataInicial, this.dataFinal, this.email, this.assunto, this.texto, true
		);
		assertTrue(enviado);
	}

}
