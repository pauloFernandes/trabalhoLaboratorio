package controller.mail;

public class TarefaMock extends AbstractTarefa {

	@Override
	public void preparaEmail(String emailPara, String assunto, String texto) {
		System.out.println(emailPara);
		System.out.println(assunto);
		System.out.println(texto);
	}

	@Override
	public void run() {
		System.out.println("executando o metodo mock.");
	}

	

}
