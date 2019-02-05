package gestionesegnalazioni;


/**
 * Un oggetto <code>Segnalazione</code> rappresenta una segnalazione. Una segnalazione puï¿½
 * riferirsi ad un annuncio o ad una recensione. Ogni segnalazione ha un id , una motivazione che
 * l'utente dovrà  selezionare ed una descrizione facoltativa.
 * 
 *
 */
public class Segnalazione {

  /**Inizializza una segnalazione.
   * 
   * @param id nel database
   * @param descrizione Descrizione
   * @param motivazione della segnalazione scelto dall'utente.
   * @param recensione segnalata 
   * @param annuncio segnalato
   */
  
  public Segnalazione(int id, String descrizione, int motivazione,
      int recensione, int annuncio) {
    super();
    this.id = id;
    this.descrizione = descrizione;
    this.motivazione = motivazione;
    this.annuncio = annuncio;
    this.recensione = recensione;
  }

  public Segnalazione() {
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
  

  
  public int getIdSegnalato() {
    return annuncio;
  }

  /**
   * <code>true</code> Annuncio.
   * <code>false</code> Recensione.
   */
  public boolean isTipoSegnalazione() {
    if (annuncio != -1) {
      return true;
    } else {
      return false;
    }
    
  }

 

  public int getAnnuncio() {
    return annuncio;
  }

  public int getRecensione() {
    return recensione;
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

  public void setAnnuncio(int annuncio) {
    this.annuncio = annuncio;
  }

  public void setRecensione(int recensione) {
    this.recensione = recensione;
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
  private int annuncio;
  private int recensione;
  
}
