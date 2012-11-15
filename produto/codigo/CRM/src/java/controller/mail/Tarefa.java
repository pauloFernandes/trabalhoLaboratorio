package controller.mail;


import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import util.mail.MailSender;

public class Tarefa extends AbstractTarefa {

	private MailSender mailSender;
	
	
	public Tarefa() {
		try {
			this.mailSender = new MailSender();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.enviar();
	}	
    
	@Override
	public void preparaEmail(String emailPara, String assunto, String texto) 
	{
		this.mailSender.setMailTo(emailPara);
		this.mailSender.setAssunto(assunto);
		this.mailSender.setTexto(texto);
	}
	
	private boolean enviar() 
	{
		try {
			this.mailSender.send();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();			
			return false;
		}
	}

}