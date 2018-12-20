package gestioneannunci;

/**
 * Il manager della classe Annuncio si occupa della gestione degli annunci: della loro creazione, rimozione, 
 * modifica e della ricerca.
 * 
 *
 */

public class AnnuncioManager {

  

	/**Questo metodo crea un nuovo annuncio in database.
	 * 
	 * @param dipartimento selezionato dall'utente.
	 * @param titolo
	 * @param descrizione
	 * @param tipologia dell'annuncio (gruppo di studio/tutorato.
	 */
  public void creaAnnuncio(
      String dipartimento, String titolo, String descrizione, String tipologia) {
    
  }

   /**Questo metodo rimuove l'annuncio selezionato dal database.
   * 
   * @param a annuncio da rimuovere.
   */
  public void rimuoviAnnuncio(Annuncio a) {}
  
  /**Questo metodo modifica l'annuncio selezionato nel database.
   * 
   * @param a annuncio da modificare.
   * 
   */
  public void modificaAnnuncio(Annuncio a) {}
  
  
  /**Questo metodo cerca un annuncio nel database in base ai parametri inseriti.
   * 
   * 
   * @param dipartimento filtra gli annunci a seconda del dipartimento selezionato.
   * @param titolo filtra gli annunci in base alla seguente stringa
   * @param descrizione filtra gli annunci in base alla seguente stringa
   * @param tipologia filtra gli annunci in gruppo di studio o in tutorati.
   */
  public void cercaAnnuncio(
      String dipartimento, String titolo, String descrizione, String tipologia) {
    
  }
}
