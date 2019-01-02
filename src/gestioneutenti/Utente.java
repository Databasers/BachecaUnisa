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
  

  public void setNome(String nome) {
    //Sto continuando a usare lo stile degli altri commenti,
    //non so se ha senso fare cosi
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




  private String nome;
  private String cognome;
  private String sesso;
  private String password;
  private String descrizione;
  private int numAnnunci;

}
