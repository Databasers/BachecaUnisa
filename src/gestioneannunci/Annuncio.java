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
   *          <code>false</code> se e' un annuncio di gruppo di studio.
   * @param dipartimento relativo all'annuncio.
   */
  public Annuncio(String titolo, String descrizione, boolean tipologia, String dipartimento) {
    this.titolo = titolo;
    this.descrizione = descrizione;
    this.tipologia = tipologia;
    this.dipartimento = dipartimento;
  }


  /**
   *  Restituisce il titolo dell'annuncio.
   *  
   */
  public String getTitolo() {
    return titolo;
  }

  /**
   * Restituisce la descrizione dell'annuncio.
   * 
   */
  public String getDescrizione() {
    return descrizione;
  }
  
  /**
   * Check per la classe di annuncio.
   * @return  <code>true</code> annuncio di tutorato.
   *          <code>false</code> annuncio di gruppo di studio.
   */
  public boolean isTipologia() {
    return tipologia;
  }

  /**
   * Restituisce il dipartimento dell'annuncio.
   * 
   */
  public String getDipartimento() {
    return dipartimento;
  }

  /**
   * Modifica il titolo dell'annuncio.
   * @param titolo aggiornato dell'annuncio.
   */
  public void setTitolo(String titolo) {
    this.titolo = titolo;
  }

  /**
   * Modifica la descrizione dell'annuncio.
   * @param descrizione aggiornata dell'annuncio.
   */
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * Modifica la tiologia dell'annuncio.
   * @param tipologia aggiornata dell'annuncio.<code>true</code> se e' un annuncio di tutorato.
   *          <code>false</code> se e' un annuncio di gruppo di studio.
   */
  public void setTipologia(boolean tipologia) {
    this.tipologia = tipologia;
  }

  /**
   * Modifica il dipartimento dell'annuncio.
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
