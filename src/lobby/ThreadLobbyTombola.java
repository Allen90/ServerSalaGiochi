package lobby;

import java.util.ArrayList;

import partite.PartitaTombola;
import tombola.GiocatoreTombola;

public class ThreadLobbyTombola implements Runnable{
	private static ThreadLobbyTombola lt;
	private PartitaTombola task;
	private ArrayList<PartitaTombola> pt; 
	private ArrayList<GiocatoreTombola> giocatori;


	private ThreadLobbyTombola(){
		giocatori = new ArrayList<GiocatoreTombola>();
		pt = new ArrayList<PartitaTombola>();

	}

	public static ThreadLobbyTombola getIstance(){
		if(lt == null)
			lt = new ThreadLobbyTombola();
		return lt;
	}

	public int numUtentiLobby(){
		return giocatori.size();
	}

	public ArrayList<GiocatoreTombola> getUtenti(){
		return giocatori;
	}

	public void svuotaLobbyTombola(){
		for(int i = 0;i < giocatori.size();i++)
			giocatori.remove(i);
	}

	public void addUserLobbyTomb(GiocatoreTombola g){
		giocatori.add(g);
	}

	public boolean aggiornaVincite(String username,int numPartita,int tipoVittoria, int indiceCartella,int indiceRiga){
		boolean ok = false;
		ok = pt.get(numPartita).setVittoria(tipoVittoria);
		for(int i = 0;i< pt.get(numPartita).getGiocatori().size();i++)
			if(pt.get(numPartita).getGiocatori().get(i).getUtente().getUsername().equals(username))
				pt.get(numPartita).getGiocatori().get(i).getCartelle().get(indiceCartella).rigaVinta(indiceRiga);

		if(tipoVittoria == 6)
			pt.get(numPartita).setFinito();
		return ok;
	}

	public void run(){
		while(true){
			int n = numUtentiLobby();
			System.out.println(n);
			//System.out.println("utenti presenti:" + numUtentiLobby());
			if(n > 1){
				System.out.println("qui in lobby con piu' di una persona");
				//				try {
				//Thread.sleep(3000);
				System.out.println("sto per creare la partita");
				task = new PartitaTombola(getUtenti(),pt.size());
				Thread t = new Thread(task);
				pt.add(task);
				t.start();

				svuotaLobbyTombola();
				//				} catch (InterruptedException e) {
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//				}
			}
		}
	}

}
