package gestioneannunci;

/**
 * Un oggetto <code>Annuncio</code> rappresenta un annuncio creato da un utente. Ogni annuncio ha un id, una
 * una tipologia (gruppo di studio, tutorato), un titolo, una descrizione facoltativa, un 
 * dipartimento da selezionare ed il numero di segnalazioni ricevute.
 *
 */

public class Annuncio {
  
  /**
   * Inizializza un nuovo annuncio.
   * @param id
   * @param titolo dell'annuncio.
   * @param descrizione dell'annuncio.
   * @param tipologia dell'annuncio <code>true</code> se e' un annuncio di tutorato.
   *          <code>false</code> se e' un annuncio di gruppo di studio.
   * @param dipartimento relativo all'annuncio.
   */
  public Annuncio(int id, String titolo, String descrizione, boolean tipologia, String dipartimento) {
    this.id = id;
    this.titolo = titolo;
    this.descrizione = descrizione;
    this.tipologia = tipologia;
    this.dipartimento = dipartimento;
  }



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

  public String getDipartimento() {
    return dipartimento;
  }


  public int getId() {
    return id;
}

  public int getNumSegnalazioni() {
    return numSegnalazioni;
}



public void setTitolo(String titolo) {
    this.titolo = titolo;
  }


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


  public void setDipartimento(String dipartimento) {
    this.dipartimento = dipartimento;
  }
 
  

  public void setId(int id) {
    this.id = id;
}



public void setNumSegnalazioni(int numSegnalazioni) {
    this.numSegnalazioni = numSegnalazioni;
}



private String titolo;
  private String descrizione;
  private boolean tipologia;
  private String dipartimento;
  private int id;
  private int numSegnalazioni = 0;
}
