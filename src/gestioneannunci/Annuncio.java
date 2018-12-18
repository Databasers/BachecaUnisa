package gestioneannunci;

/**
 * Un oggetto <code>Annuncio</code> rappresenta un annuncio creato da un utente. Ogni annuncio ha un
 * una tipologia (gruppo di studio, tutorato), un titolo, una descrizione facoltativa ed un 
 * dipartimento da selezionare
 *
 */

public class Annuncio {

	
	
	
	
	
	/**
	 * 
	 * @return il titolo dell'annuncio.
	 */
	public string getTitolo() {
		return titolo;
	}
	
	/**
	 * 
	 * @param titolo andrà ad aggiornare il titolo dell'annuncio.
	 */
	public void setTitolo(string titolo) {
		this.titolo = titolo;
	}
	public string getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(string descrizione) {
		this.descrizione = descrizione;
	}
	public boolean isTipologia() {
		return tipologia;
	}
	public void setTipologia(boolean tipologia) {
		this.tipologia = tipologia;
	}
	public string getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(string dipartimento) {
		this.dipartimento = dipartimento;
	}
	
	
	private string titolo;
	private string descrizione;
	private boolean tipologia;
	private string dipartimento;
}
