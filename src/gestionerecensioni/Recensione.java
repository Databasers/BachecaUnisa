package gestionerecensioni;

import gestionesegnalazioni.Segnalabile;
import gestionesegnalazioni.Segnalazione;
import gestioneutenti.Utente;

/**
 * Un oggetto <code>Recensione</code> rappresenta una recensione 
 * rilasciata da un utente ad un tutor.
 * Ogni recensione ha parametro di valutazione (con valori da 1 a 5) ed una descrizione facoltativa.
 *
 */

public class Recensione implements Segnalabile {

  /**
   * Inizializza una nuova recensione.
   * @param valutazione della recensione
   * @param descrizione della recensione
   */
  public Recensione(int id, int valutazione, String descrizione) {
    this.id = id;
    this.valutazione = valutazione;
    this.descrizione = descrizione;
  }
  
  public Recensione(int valutazione, String descrizione) {
    this.valutazione = valutazione;
    this.descrizione = descrizione;
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



  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }




  public void setId(int id) {
    this.id = id;
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

  public Utente getMittente() {
    return mittente;
  }



  public void setMittente(Utente mittente) {
    this.mittente = mittente;
  }

  public Utente getDestinatario() {
    return destinatario;
  }



  public void setDestinatario(Utente destinatario) {
    this.destinatario = destinatario;
  }

  private int id;
  private int valutazione;
  private String descrizione;
  private Utente mittente;
  private Utente destinatario;
}
