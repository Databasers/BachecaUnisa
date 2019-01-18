package gestioneutenti;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtenteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    doGet(request, response);
    try {
      String azione = request.getParameter("azione");  
      if (azione == "stampaUtenti") {
        int numPagina = Integer.parseInt(request.getParameter("numPagina"));
        stampaUtenti(numPagina);
      }

      if (azione == "rimuoviUtente") {
        String username = request.getParameter("username");
        rimuoviUtente(username);
      }

      if (azione == "modificaPassword") {
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        modificaPassword(username, oldPassword, newPassword);

      }
      
      if (azione == "modificaUtente") {
        String username = request.getParameter("username");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String descrizione = request.getParameter("descrizione");
        modificaUtente(username, nome, cognome, descrizione);

      }

      if (azione == "creaUtente") {
        String username = request.getParameter("username");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String sesso = request.getParameter("sesso");
        String password = request.getParameter("password");
        String descrizione = request.getParameter("descrizione");
        creaUtente(username, nome, cognome, sesso, password, descrizione);
      }
  }



    catch (Exception exc) {

    }

    finally {};



  }




  /**
   * Questo metodo si occupa di restituire tutti gli utenti.
   * @param numPagina il numero della pagina attualmente visualizzata dall'utente.
   */
  private ArrayList<Utente> stampaUtenti(int numPagina) {
    return null;

  }



  /**
   * Questo metodo si occupa di rimuovere una recensione dal database.
   * @param id l'id della recensione da rimuovere
   */
  private void rimuoviUtente(String username) {

  }


  /**
   * Questo metodo si occupa di modificare i dati dell'utente.
   * @param nome dell'utente modificato
   * @param cognome dell'utente modificato
   * @param descrizione dell'utente modificata
   */
  private void modificaUtente(String username, String nome, String cognome,
      String descrizione) {

  }
  
  /**
   * Questo metodo permette di modificare la password dell'utente.
   * @param password aggiornata
   */
  private void modificaPassword(String username, String oldPassword, String newPassword) {

  }


  /**
   * Inizializza un nuovo utente.
   * @param username dell'utente
   * @param nome dell'utente
   * @param cognome dell'utente
   * @param sesso dell'utente
   * @param password dell'utente
   * @param descrizione  dell'utente
   * @param numAnnunci numero di annunci creati dall'utente
   */
  private void creaUtente(String username, String nome, String cognome, String sesso, 
      String password, String descrizione) {
    
  }


}