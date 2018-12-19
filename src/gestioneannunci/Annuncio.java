package gestioneannunci;

/**
 * Un oggetto <code>Annuncio</code> rappresenta un annuncio creato da un utente. Ogni annuncio ha un
 * una tipologia (gruppo di studio, tutorato), un titolo, una descrizione facoltativa ed un 
 * dipartimento da selezionare
 *
 */

public class Annuncio {
	
	/**
	 * Inizializza un nuovo annuncio.
	 * @param titolo dell'annuncio.
	 * @param descrizione dell'annuncio.
	 * @param tipologia dell'annuncio <code>true</code> se e' un annuncio di tutorato.
	 * 								  <code>false</code> se e' un annuncio di gruppo di studio.
	 * @param dipartimento relativo all'annuncio.
	 */
	public Annuncio(String titolo, String descrizione, boolean tipologia, String dipartimento) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.tipologia = tipologia;
		this.dipartimento = dipartimento;
	}
	

	/**
	 *
	 * @return il titolo dell'annuncio.
	 */
	public String getTitolo() {
		return titolo;
	}
	
	/**
	 * 
	 * @return la descrizione dell'annuncio.
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * 
	 * @return  <code>true</code> se e' un annuncio di tutorato.
	 * 			<code>false</code> se e' un annuncio di gruppo di studio.
	 */
	public boolean isTipologia() {
		return tipologia;
	}
	
	/**
	 * 
	 * @return il dipartimento relativo all'annuncio.
	 */
	public String getDipartimento() {
		return dipartimento;
	}
	
	/**
	 * 
	 * @param titolo aggiornato dell'annuncio.
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	/**
	 * 
	 * @param descrizione aggiornata dell'annuncio.
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * 
	 * @param tipologia aggiornata dell'annuncio.<code>true</code> se e' un annuncio di tutorato.
	 * 											 <code>false</code> se e' un annuncio di gruppo di studio.
	 */
	public void setTipologia(boolean tipologia) {
		this.tipologia = tipologia;
	}
	
	/**
	 * 
	 * @param dipartimento aggiornato relativo all'annuncio.
	 */
	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}
	
	
	private String titolo;
	private String descrizione;
	private boolean tipologia;
	private String dipartimento;
}
