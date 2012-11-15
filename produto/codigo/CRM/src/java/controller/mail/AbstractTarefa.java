package controller.mail;

import java.util.TimerTask;

public abstract class AbstractTarefa extends TimerTask {

	public abstract void preparaEmail(String emailPara, String assunto, String texto);
	
}
