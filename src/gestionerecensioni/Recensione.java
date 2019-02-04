package gestionerecensioni;

import gestionesegnalazioni.Segnalabile;
import gestionesegnalazioni.Segnalazione;

/**
 * Un oggetto <code>Recensione</code> rappresenta una recensione 
 * rilasciata da un utente ad un tutor.
 * Ogni recensione ha parametro di valutazione (con valori da 1 a 5) ed una descrizione facoltativa.
 *
 */

public class Recensione implements Segnalabile {
  
  /**
   * Inizializza una nuova recensione.
   * @param id della recensione
   * @param valutazione della recensione
   * @param descrizione della recensione
   * @param mittente username dell'utente che ha creato la recensione.
   * @param destinatario username dell'utente che ha ricevuto la recensione.
   */
  public Recensione(int id, int valutazione, String descrizione, 
      String mittente, String destinatario) {
    this.id = id;
    this.valutazione = valutazione;
    this.descrizione = descrizione;
    this.mittente = mittente;
    this.destinatario = destinatario;
  }
  
  /**
   * Inizializza una nuova recensione.
   * @param valutazione della recensione
   * @param descrizione della recensione
   * @param mittente username dell'utente che ha creato la recensione.
   * @param destinatario username dell'utente che ha ricevuto la recensione.
   */
  public Recensione(int valutazione, String descrizione, 
      String mittente, String destinatario) {
    this.valutazione = valutazione;
    this.descrizione = descrizione;
    this.mittente = mittente;
    this.destinatario = destinatario;
  }


  public Recensione() {
  }



  public int getValutazione() {
    return valutazione;
  }


  public String getDescrizione() {
    return descrizione;
  }
  


  public int getId() {
    return id;
  }

  public String getMittente() {
    return mittente;
  }

  public String getDestinatario() {
    return destinatario;
  }

  public void setMittente(String mittente) {
    this.mittente = mittente;
  }

  public void setDestinatario(String destinatario) {
    this.destinatario = destinatario;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }


  public void setId(int id) {
    this.id = id;
  }
  
  public void setValutazione(int valutazione) {
    this.valutazione = valutazione;
  }




  /**
  * La segnalazione viene aggiunta alla lista delle segnalazioni.
  * @param a la segnalazione da inserire nella lista delle segnalazioni.
  */
  public void addSegnalazione(Segnalazione a) {
    lista.add(a);
  }



  /**La segnalazione, il cui id � passato come parametro, 
   * viene rimossa dalla lista delle segnalazioni.
   * @param id della segnalazione da rimuovere dalla lista delle segnalazioni.
   */
  public void deleteSegnalazione(int id) {
    lista.remove(id);

  }

  /**Ritorna la segnalazione che ha come indice il parametro inserito.
   * @param indice indice della segnalazione da cercare.
   * 
   */
  public Segnalazione leggiSegnalazione(int indice) {
    return lista.get(indice);
      
  }




  private int id;
  private int valutazione;
  private String descrizione;
  private String mittente;
  private String destinatario;
}
