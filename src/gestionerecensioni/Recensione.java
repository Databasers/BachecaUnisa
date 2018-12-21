package gestionerecensioni;

/**
 * Un oggetto <code>Recensione</code> rappresenta una recensione 
 * rilasciata da un utente ad un tutor.
 * Ogni recensione ha parametro di valutazione (con valori da 1 a 5) ed una descrizione facoltativa.
 *
 */

public class Recensione {

  /**
   * Inizializza una nuova recensione.
   * @param valutazione della recensione
   * @param descrizione della recensione
   */
  public Recensione(int valutazione, String descrizione) {
    this.valutazione = valutazione;
    this.descrizione = descrizione;
  }


  /**
   * Cosa.
   * @return la valutazione della recensione
   */
  public int getValutazione() {
    return valutazione;
  }

  /**
   * Due.
   * @return la descrizione della recensione
   */
  public String getDescrizione() {
    return descrizione;
  }

  /**
   * Tre.
   * @param descrizione aggiornata della recensione
   */
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }



  private int valutazione;
  private String descrizione;
}
