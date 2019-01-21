package gestionesegnalazioni;

import gestioneannunci.Annuncio;
import gestionerecensioni.Recensione;

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
   * @param id nel database
   * @param descrizione Descrizione
   * @param motivazione della segnalazione scelto dall'utente.
   * @param idSegnalato id dell'oggetto segnalato (annuncio o recensione)
   * @param tipoSegnalazione <code>true</code> se è un annuncio.
   *                         <code>false</code> se è una recensione.
   * @param recensione segnalata, non null solo se tipo = false
   * @param annuncio segnalato, not null solo se tipo = true
   */
  
  public Segnalazione(int id, String descrizione, int motivazione,
      int idSegnalato, boolean tipoSegnalazione,
      Recensione recensione, Annuncio annuncio) {
    super();
    this.id = id;
    this.descrizione = descrizione;
    this.motivazione = motivazione;
    this.idSegnalato = idSegnalato;
    this.tipoSegnalazione = tipoSegnalazione;
    this.recensione = recensione;
    this.annuncio = annuncio;
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


  public Recensione getRecensione() {
    return recensione;
  }

  public void setRecensione(Recensione recensione) {
    this.recensione = recensione;
  }


  public Annuncio getAnnuncio() {
    return annuncio;
  }

  public void setAnnuncio(Annuncio annuncio) {
    this.annuncio = annuncio;
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
  private Recensione recensione;
  private Annuncio annuncio;
  
}
