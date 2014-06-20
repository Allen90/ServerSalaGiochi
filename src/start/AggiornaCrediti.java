package start;

import eccezioni.EccezioneClassificaVuota;
import interfacciaDB.ConnessioneDB;

public class AggiornaCrediti implements Runnable{
	private ConnessioneDB db;
	private boolean continua;
	public AggiornaCrediti(){
		db = ConnessioneDB.getInstance();
		continua = true;
	}
	
	public void chiudi(){
		continua = false;
	}
	
	public void run() {
		while(continua){
		try {
			Thread.sleep(3600000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.aggiornaPeriodico();
		} catch (EccezioneClassificaVuota e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
