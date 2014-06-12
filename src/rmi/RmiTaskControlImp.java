package rmi;



import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import eccezioni.EccezioneClassificaVuota;
import eccezioni.EccezioneUtente;
import rmiServer.RmiTaskControl;
import rubamazzo.Mossa;
import rubamazzo.SituazioneRubamazzo;
import slot.Rollata;
import taskController.TaskController;
import tombola.SituazioneTombola;
import userModel.EntryClassifica;
import userModel.InfoHome;
import userModel.Utente;


public class RmiTaskControlImp extends UnicastRemoteObject implements RmiTaskControl,Runnable{
	private Utente utente;
	private Boolean continua;
	private TaskController tc;
	
	
	public RmiTaskControlImp(Utente utente) throws RemoteException{
		this.utente = utente;
		continua = true;
		tc = new TaskController();
	}
	
	@Override
	public void run() {
		while(continua){
			
		}
		
	}

	@Override
	public Rollata rolla() {
		return tc.rolla(utente);
	}

	@Override
	public SituazioneTombola aggTombola() {
		return tc.aggTombola(utente);
	}

	@Override
	public SituazioneRubamazzo aggRubaMazzo() {
		return tc.aggRubamazzo(utente);
	}

	@Override
	public ArrayList<EntryClassifica> aggClass() throws EccezioneClassificaVuota {
		return tc.aggClass(utente,false);
		
	}

	@Override
	public ArrayList<EntryClassifica> aggClassGiorn() throws EccezioneClassificaVuota {
		return tc.aggClass(utente,true);
	}

	@Override
	public void termina() {
		continua = tc.termina();
	}
	
	public boolean giocoTombola(int numCartelle) throws EccezioneUtente{
		return tc.giocoTombola(utente, numCartelle);
		
	}
	
	public boolean giocoRubamazzo() throws EccezioneUtente{
		return tc.giocoRubamazzo(utente);
	}
	
	public boolean mossaRubamazzo(Mossa m,int numPartita){
		return tc.mossaRubaMazzo(utente, m, numPartita);
	}
	
	public boolean vintoTombola(int numPartita,int tipoVittoria,int indiceCartella, int indiceRiga){
		return tc.vintoTombola(utente, numPartita, tipoVittoria, indiceCartella, indiceRiga);
	}

	
	public InfoHome getInfoHome() throws EccezioneUtente{
		int posizione = tc.getPosizione(utente.getUsername());
		InfoHome ih = new InfoHome(utente.getNome(),utente.getCognome(),utente.getCrediti(),posizione);
		return ih;
	}

	

}
