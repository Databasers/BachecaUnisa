package gestioneutenti;

/**
 * Un oggetto <code>Utente</code> rappresenta un utente. Ogni utente ha un un nome, un cognome,
 * un sesso, un username, una password, una descrizione ed un numero annunci
 *
 */

public class Utente {

  /**
   * Inizializza un nuovo utente.
   * 
   * @param nome dell'utente
   * @param cognome dell'utente
   * @param sesso dell'utente
   * @param password dell'utente
   * @param descrizione  dell'utente
   * @param numAnnunci numero di annunci creati dall'utente
   */
  public Utente(String nome, String cognome, String sesso, 
      String password, String descrizione, int numAnnunci) {
    this.nome = nome;
    this.cognome = cognome;
    this.sesso = sesso;
    this.password = password;
    this.descrizione = descrizione;
    this.numAnnunci = numAnnunci;
  }



  /**
   * Restituisce il nome dell'utente.
   * 
   */
  public String getNome() {
    return nome;
  }

  /**
   * Restituisce il cognome dell'utente.
   * 
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Restituisce il sesso dell'utente.
   * 
   */
  public String getSesso() {
    return sesso;
  }

  /**
   * Restituisce la password dell'utente.
   * 
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * Restituisce la descrizione dell'utente.
   * 
   */
  public String getDescrizione() {
    return descrizione;
  }

  /**
   * Restituisce il numero di annunci dell'utente.
   * 
   */
  public int getNumAnnunci() {
    return numAnnunci;
  }
  
  /**
   * quet.
   * @param nome dell'utente
   */
  
  public void setNome(String nome) {
    //Sto continuando a usare lo stile degli altri commenti,
    //non so se ha senso fare cosi
    this.nome = nome;
  }
  /**
   * nonquet.
   * @param cognome dell'utente
   */
  
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }
  
  /**
   * pesce palla.
   * @param sesso dell'utente
   */
  
  public void setSesso(String sesso) {
    this.sesso = sesso;
  }
  /**
   * qualcon.
   * @param password dell'utente
   */
  
  public void setPassword(String password) {
    this.password = password;
  }
  /**
   * emp.
   * @param descrizione dell'utente
   */
  
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }
  
  /**
   * uthgi.
   * @param numAnnunci numero di annunci dell'utente
   */
  
  public void setNumAnnunci(int numAnnunci) {
    this.numAnnunci = numAnnunci;
  }




  private String nome;
  private String cognome;
  private String sesso;
  private String password;
  private String descrizione;
  private int numAnnunci;

}
