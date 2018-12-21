package gestionerecensioni;

import gestioneutenti.Utente;

/**
 * Il manager della classe Recensione si occupa della gestione delle recensioni: della loro 
 * creazione e rimozione.
 * 
 */
public class RecensioneManager {
  
  
  /**
   * Questo metodo crea una recensione nel database.
   * 
   * @param descrizione Descrizione
   * @param utenteMittente utente che rilascia la recensione.
   * @param utenteDestinatario utente che riceve la recensione.
   */
  
  public void creaRecensione(
      String descrizione, Utente utenteMittente, Utente utenteDestinatario) {}
  
  
  /**
   * Questo metodo rimuove la recensione selezionata.
   * 
   * @param recensione da rimuovere.
   */
  public void rimuoviRecensione(Recensione recensione) {}
}
