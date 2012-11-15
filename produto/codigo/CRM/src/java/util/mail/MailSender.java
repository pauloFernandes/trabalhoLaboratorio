package util.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Classe para envio de e-mail
 * 
 * @see http://www.rgagnon.com/javadetails/java-0570.html
 * 
 * 
 * @author PauloHenrique
 *
 */
public class MailSender {

	// Constantes para conexao ao Servidor de e-mail.
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT    = 465;
    private static final String SMTP_AUTH_USER = "phfernandespereira@gmail.com";
    private static final String SMTP_AUTH_PWD  = "15071850";
    
    // Objetos necessários para o envio de e-mail.
    private Properties properties;
    private Session session;
    private Transport transport;
    private MimeMessage message;

    private String mailTo;
    private String assunto;
    private String texto;
    
    /**
     * 
     * @throws NoSuchProviderException
     */
    public MailSender() throws NoSuchProviderException 
    {
    	this.prepareProperties();
    	this.prepareSendSession();
    }

    /**
     * 
     * @param mailTo
     * @throws NoSuchProviderException
     */
    public MailSender(String mailTo) throws NoSuchProviderException {
    	this();
    	this.mailTo = mailTo;
    }
    
    /**
     * Metodo auxiliar do construtor. Inicializa o objeto de properties.
     */
    private void prepareProperties() 
    {
    	this.properties = new Properties();
    	this.properties.put("mail.transport.protocol", "smtps");
    	this.properties.put("mail.smtps.host", SMTP_HOST_NAME);
    	this.properties.put("mail.smtps.auth", "true");
    }
    
    /**
     * Metodo auxiliar do construtor: Inicializa a sessão usada para o envio do email.
     * 
     * @throws NoSuchProviderException
     */
    private void prepareSendSession() throws NoSuchProviderException 
    {
    	this.session   = Session.getDefaultInstance(properties);
    	this.transport = this.session.getTransport();
    	this.message   = new MimeMessage(session);
    }

    /**
     * 
     * @return
     */
	public String getMailTo() {
		return mailTo;
	}

	/**
	 * 
	 * @param mailTo
	 */
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	/**
	 * 
	 * @return
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * 
	 * @param assunto
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/**
	 * 
	 * @return
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * 
	 * @param texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * 
	 * @throws MessagingException
	 */
	public void send() throws MessagingException 
	{
		this.message.setSubject(this.getAssunto());
		this.message.setText(this.getTexto());
		this.message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.getMailTo()));
		this.transport.connect(
			MailSender.SMTP_HOST_NAME, MailSender.SMTP_HOST_PORT, MailSender.SMTP_AUTH_USER, MailSender.SMTP_AUTH_PWD
		);
		this.transport.sendMessage(this.message, message.getRecipients(Message.RecipientType.TO));
		this.transport.close();
	}

	/**
	 * 
	 * @param args
	 * @throws MessagingException
	 */
//	public static void main(String[] args) throws MessagingException 
//	{
//		MailSender sender = new MailSender();
//		sender.setMailTo("paulo.phfp_26@yahoo.com.br");
//		sender.setAssunto("Teste de envio de email");
//		sender.setTexto("TEste de envio de email");
//		sender.send();
//		System.out.println("Mensagem enviada com sucesso.");
//	}
	
}