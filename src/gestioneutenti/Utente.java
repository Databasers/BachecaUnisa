package gestioneutenti;

/**
 * Un oggetto <code>Utente</code> rappresenta un utente. Ogni utente ha un username, un nome, 
 * un cognome, un sesso, una password, una descrizione ed un numero annunci-
 *
 */

public class Utente {

  /**
   * Inizializza un nuovo utente.
   * @param username dell'utente
   * @param nome dell'utente
   * @param cognome dell'utente
   * @param sesso dell'utente
   * @param password dell'utente
   * @param descrizione  dell'utente
   * @param numAnnunci numero di annunci creati dall'utente.
   * @param gestore <code>true</code> se e' gestore.
   *                <code>false</code> se e' utente.
   */
  public Utente(String username, String nome, String cognome, String sesso, 
      String password, String descrizione, int numAnnunci, boolean gestore) {
    this.username = username;
    this.nome = nome;
    this.cognome = cognome;
    this.sesso = sesso;
    this.password = password;
    this.descrizione = descrizione;
    this.numAnnunci = numAnnunci;
    this.gestore = gestore;
  }


  public Utente() {
  }


  public String getNome() {
    return nome;
  }


  public String getCognome() {
    return cognome;
  }


  public String getSesso() {
    return sesso;
  }


  public String getPassword() {
    return password;
  }
  

  public String getDescrizione() {
    return descrizione;
  }

  public int getNumAnnunci() {
    return numAnnunci;
  }
  

  public String getUsername() {
    return username;
  }
  
  


  public boolean isGestore() {
    return gestore;
  }


  public void setGestore(boolean gestore) {
    this.gestore = gestore;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }
  

  
  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  
  public void setPassword(String password) {
    this.password = password;
  }

  
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public void setNumAnnunci(int numAnnunci) {
    this.numAnnunci = numAnnunci;
  }

 

  private String username;
  private String nome;
  private String cognome;
  private String sesso;
  private String password;
  private String descrizione;
  private int numAnnunci;
  private boolean gestore; //true e' un gestore

}
