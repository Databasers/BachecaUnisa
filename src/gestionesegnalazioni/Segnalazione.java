package gestionesegnalazioni;


/**
 * Un oggetto <code>Segnalazione</code> rappresenta una segnalazione. Una segnalazione pu�
 * riferirsi ad un annuncio o ad una recensione. Ogni segnalazione ha un id , una motivazione che
 * l'utente dovra' selezionare ed una descrizione facoltativa.
 * 
 *
 */
public class Segnalazione {

  /**Inizializza una segnalazione.
   * @param id nel database
   * @param descrizione Descrizione
   * @param motivazione della segnalazione scelto dall'utente.
   * @param recensione segnalata 
   * @param annuncio segnalato
   * @param utente id dell'utente creatore della segnalazione.
   */
  
  public Segnalazione(int id, String descrizione, int motivazione,
      Integer recensione, Integer annuncio, String utente) {
    super();
    this.id = id;
    this.descrizione = descrizione;
    this.motivazione = motivazione;
    this.annuncio = annuncio;
    this.recensione = recensione;
    this.utente = utente;
  }
  
  public Segnalazione(Integer recensione, Integer annuncio) {
    this.recensione = recensione;
    this.annuncio = annuncio;
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
  
  /**
   * Restituisce l'id dell'entita' segnalata.
   * @return id dell'annuncio o della recensione segnalata.
   */
  public Integer getIdSegnalato() {
    if (isTipoSegnalazione()) {
      System.out.println("Stiamo segnalando un annuncio");
      return annuncio;
    } else {
      System.out.println("Stiamo segnalando una recensione");
      return recensione;
    }
  }

  /**
   * Definisce il tipo della segnalazione.
   * @return <code>true</code> Annuncio.
   *         <code>false</code> Recensione.
   */
  public boolean isTipoSegnalazione() {
    return annuncio != null;
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

  public void setAnnuncio(Integer annuncio) {
    this.annuncio = annuncio;
    recensione = null;
  }

  public void setRecensione(Integer recensione) {
    this.recensione = recensione;
    annuncio = null;
  }


  public String getUtente() {
    return utente;
  }

  public void setUtente(String utente) {
    this.utente = utente;
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
  private Integer annuncio;
  private Integer recensione;
  private String utente;
  
}
