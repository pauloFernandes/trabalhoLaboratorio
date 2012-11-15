package controller.mail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

public class Agendador {

	private Timer timer;
	
	private static final String SABADO  = "Sáb";
	private static final String DOMINGO = "Dom";
	
	public Agendador() 
	{
		this.timer  = new Timer();
	}

	public boolean enviarDia(Date data, String email, String assunto, String texto) 
	{
		try {
			if (data.after(new Date())) {
				AbstractTarefa tarefa = new Tarefa(); 
				tarefa.preparaEmail(email, assunto, texto);
				this.timer.schedule(tarefa, data);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean enviarExcetoFimDeSemana(Date data, String email, String assunto, String texto) 
	{
		DateFormat format = new SimpleDateFormat("E");
		if (format.format(data).equals(Agendador.SABADO)) {
			// A linha abaixo retorna um dia antes da data do parametro.
			data = new Date(data.getTime() - 24 * 60 * 60 * 1000);
		} else if (format.format(data).equals(Agendador.DOMINGO)){
			// A linha abaixo retorna um dia antes da data do parametro.
			data = new Date(data.getTime() - 2 * 24 * 60 * 60 * 1000);
		}

		return this.enviarDia(data, email, assunto, texto);
	}
	
	public boolean enviarNoIntervalo(Date dataInicial, Date dataFinal, String email, String assunto, String texto, boolean excetoFimDeSemana) 
	{
		ArrayList<Date> datas = this.getIntervaloDatas(dataInicial, dataFinal);
		
		if (excetoFimDeSemana) {
			for (int i = 0; i < datas.size(); i++) {
				this.enviarExcetoFimDeSemana(datas.get(i), email, assunto, texto);
			}
			
			return true;
		} else {
			for (int i = 0; i < datas.size(); i++) {
				this.enviarDia(datas.get(i), email, assunto, texto);
			}
			
			return true;			
		}
	}
	
	private ArrayList<Date> getIntervaloDatas(Date dataInicio, Date dataFim) 
	{
		ArrayList<Date> intervaloDatas = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dataInicio);
		while(calendar.getTime().before(dataFim))
		{
			Date aux = calendar.getTime();
			intervaloDatas.add(aux);
			calendar.add(Calendar.DATE, 1);
		}
		
		intervaloDatas.add(dataFim);
		return intervaloDatas;
	}

}
