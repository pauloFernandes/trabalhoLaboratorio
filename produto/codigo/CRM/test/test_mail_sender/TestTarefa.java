package test_mail_sender;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.mail.Tarefa;

/**
 * Teste da classe Tarefa
 * 
 * A classe tarefa é responsavel pelo agendamento do envio dos emails, para cada lembrete que
 * seja cadastrado. Inicialmente, a classe deveria ter somente apenas um metodo pulbico: preparaEmail,
 * que é responsavel por settar as informacoes do email a ser enviado. Mas, para que o teste seja executado,
 * o metodo de chamada ao envio de email deve ser publico, para que seja acessivel pela classe de teste. Apos
 * os testes, este metodo deve voltar ser privado (ele somente deve ser chamado pelo metodo run, quem realmente
 * deve fazer o envio de email).
 * 
 * @author PauloHenrique
 *
 */
public class TestTarefa {

	@Test
	public void test() {
		Tarefa tarefa = new Tarefa();
		tarefa.preparaEmail("paulo.phfp_26@yahoo.com.br", "teste de envio de email", "corpo do teste");
//		boolean enviado = tarefa.enviar();
//		assertTrue(enviado);
	}

}
