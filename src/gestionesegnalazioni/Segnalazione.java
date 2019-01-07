package gestionesegnalazioni;

/**
 * Un oggetto <code>Segnalazione</code> rappresenta una segnalazione. Una segnalazione pu�
 * riferirsi ad un annuncio o ad una recensione. Ogni segnalazione ha un id , una motivazione che
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
  public Segnalazione(int id, String descrizione, int motivo) {
    this.id = id;
    this.descrizione = descrizione;
    this.motivazione = motivo;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public int getMotivazione() {
    return motivazione;
  }


  public int getId() {
    return id;
  }

  
  public void setId(int id) {
    this.id = id;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }


  public void setMotivazione(int motivazione) {
    this.motivazione = motivazione;
  }


  private int id;
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
