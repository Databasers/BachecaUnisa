package gestionerecensioni;

/**
 * Un oggetto <code>Recensione</code> rappresenta una recensione 
 * rilasciata da un utente ad un tutor.
 * Ogni recensione ha parametro di valutazione (con valori da 1 a 5) ed una descrizione facoltativa.
 *
 */

public class Recensione {
	
	
	public Recensione(int valutazione, String descrizione) {
		this.valutazione = valutazione;
		this.descrizione = descrizione;
	}
	
	
	
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	private int valutazione;
	private String descrizione;
}
