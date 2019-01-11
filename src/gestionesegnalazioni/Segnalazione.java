package gestionesegnalazioni;

/**
 * Un oggetto <code>Segnalazione</code> rappresenta una segnalazione. Una segnalazione puï¿½
 * riferirsi ad un annuncio o ad una recensione. Ogni segnalazione ha un id , una motivazione che
 * l'utente dovrÃ  selezionare ed una descrizione facoltativa.
 * 
 *
 */
public class Segnalazione {

  /**Inizializza una segnalazione.
   * 
   * @param descrizione Descrizione
   * @param motivo della segnalazione scelto dall'utente.
   * @param idSegnalato id dell'oggetto segnalato (annuncio o recensione)
   * @param tipoSegnalazione <code>true</code> se è un annuncio.
   *                         <code>false</code> se è una recensione.
   */
  public Segnalazione(int id, String descrizione, int motivo, int idSegnalato, 
      boolean tipoSegnalazione) {
    this.id = id;
    this.descrizione = descrizione;
    this.motivazione = motivo;
  }
  
  public Segnalazione() {}

  public String getDescrizione() {
    return descrizione;
  }

  public int getMotivazione() {
    return motivazione;
  }


  public int getId() {
    return id;
  }
  

  
  public int getIdSegnalato() {
    return idSegnalato;
  }

  public boolean isTipoSegnalazione() {
    return tipoSegnalazione;
  }

  public void setIdSegnalato(int idSegnalato) {
    this.idSegnalato = idSegnalato;
  }

  public void setTipoSegnalazione(boolean tipoSegnalazione) {
    this.tipoSegnalazione = tipoSegnalazione;
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
  private int idSegnalato;
  private boolean tipoSegnalazione;
}
