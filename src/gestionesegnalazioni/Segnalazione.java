package gestionesegnalazioni;

/**
 * Un oggetto <code>Segnalazione</code> rappresenta una segnalazione. Una segnalazione può
 * riferirsi ad un annuncio o ad una recensione. Ogni segnalazione ha un motivazione che
 * l'utente dovrà selezionare ed una descrizione facoltativa.
 * 
 *
 */
public class Segnalazione {

  /**Inizializza una segnalazione.
   * 
   * @param descrizione Descrizione
   * @param motivo della segnalazione scelto dall'utente.
   */
  public Segnalazione(String descrizione, int motivo) {
    this.descrizione = descrizione;
    this.motivazione = motivo;
  }


  /**
   *  Restituisce la descrizione della segnalazione.
   *  
   */
  public String getDescrizione() {
    return descrizione;
  }

  /**
   * Restituisce la motivazione della descrizione.
   */
  public int getMotivazione() {
    return motivazione;
  }


  /**
   * Modifica la descrizione.
   * @param descrizione aggiornata.
   */
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }


  /**
   * Modifica la motivazione.
   * 
   * @param motivazione aggiornata
   */
  public void setMotivazione(int motivazione) {
    this.motivazione = motivazione;
  }



  private String descrizione;
  /**
   * da riempire.
   */
  public static final int MOTIVO1 = 1;
  /**
   * da riempire.
   */
  public static final int MOTIVO2 = 2;
  /**
   * da riempire.
   */
  public static final int MOTIVO3 = 3;
  private int motivazione;
}
