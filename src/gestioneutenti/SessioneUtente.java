package gestioneutenti;

import java.io.Serializable;

/**
 * Un oggetto <code>SessioneUtente</code> rappresenta una sessione utente. Ogni sessione ha 
 * un utente e puo' avere un ruolo.
 */

public class SessioneUtente implements Serializable {

  private static final long serialVersionUID = 1L;
  
  /**
   * Crea una sessione utente.
   * @param u utente
   */
  public SessioneUtente(Utente u) {
    username = u.getUsername();
    nome = u.getNome();
    cognome = u.getCognome();
  }
  
  /**
   * Crea una sessione utente e si specifica il ruolo assunto dall'utente nella sessione.
   * @param u utente
   * @param ruolo assunto dall'utente nella sessione.
   */
  public SessioneUtente(Utente u, String ruolo) {
    username = u.getUsername();
    nome = u.getNome();
    cognome = u.getCognome();
    this.ruolo = ruolo;
  }
  
  public SessioneUtente() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNome() {
    return nome;
  }

  public String getRuolo() {
    return ruolo;
  }

  public void setRuolo(String ruolo) {
    this.ruolo = ruolo;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  private String username;
  private String nome;
  private String cognome;
  private String ruolo;

}
