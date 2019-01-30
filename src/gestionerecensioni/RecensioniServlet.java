package gestionerecensioni;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneannunci.Annuncio;
import gestioneutenti.SessioneUtente;
import gestioneutenti.UtenteManager;

public class RecensioniServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  RecensioneManager recensioneManager;
  UtenteManager utenteManager;
  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    doGet(request, response);
    
    SessioneUtente sessione = (SessioneUtente) request.getSession().getAttribute("log");
    String usernameLog = sessione.getUsername();
    
    try {
      String azione = request.getParameter("azione");  
      
      if (azione == "stampaRecensioni") {
        int numPagina = Integer.parseInt(request.getParameter("numPagina"));
        String username = request.getParameter("username");
        ArrayList<Recensione> caso = stampaRecensioni(username, numPagina);
        request.getSession().setAttribute("Lista", caso);
        String luogo = request.getParameter("luogo");
        response.sendRedirect(request.getContextPath() + "/HTML/" + luogo + ".jsp");
      }

      if (azione == "rimuoviRecensione") {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("usernameUtente");
        if (sessione.getRuolo().equals("Gestore")) {
          rimuoviRecensione(id);
        } else {
          if (usernameLog.equals(username)) {
            rimuoviRecensione(id);
          }
        }
      }
      


      if (azione == "modificaRecensione") {
        int id = Integer.parseInt(request.getParameter("id"));
        int valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String descrizione = request.getParameter("descrizione");
        String username = request.getParameter("usernameUtente");
        if (usernameLog.equals(username)) {
          modificaRecensione(id, valutazione, descrizione);
        }
      }
      

      if (azione == "creaRecensione") {
        if (sessione.getRuolo().equals("Utente")) {
          int valutazione = Integer.parseInt(request.getParameter("valutazione"));
          String descrizione = request.getParameter("descrizione");
          creaRecensione(valutazione, descrizione);
        }
      }
    }


      

    catch (Exception exc) {

    }

    finally {};



  }




  /**
   * Questo metodo si occupa di restituire tutte le recensioni dell'utente passato come parametro.
   * @param username identificativo dell'utente.
   * @param numPagina il numero della pagina attualmente visualizzata dall'utente.
   * @throws SQLException 
   */
  private ArrayList<Recensione> stampaRecensioni(String username, int numPagina)
      throws SQLException {
    return recensioneManager.stampaRecensione(
        utenteManager.recuperaPerUsername(username), numPagina);
  }



  /**
   * Questo metodo si occupa di rimuovere una recensione dal database.
   * @param id l'id della recensione da rimuovere
   * @throws SQLException 
   */
  private void rimuoviRecensione(int id) throws SQLException {
    recensioneManager.recensionePerId(id);
  }


  /**
   * Questo metodo si occupa di modificare la recensione scelta all'interno del database.
   * @param id della recensione da modificare
   * @param descrizione nuova descrizione
   * @param valutazione nuovo valutazione
   */
  private void modificaRecensione(int id, int valutazione, String descrizione) {
    
  }


  /**
   * Questo metodo crea una recensione all'interno del database.
   * @param valutazione della nuova recensione
   * @param descrizione della nuova recensione
   * @throws SQLException 
   */
  private void creaRecensione(int valutazione, String descrizione) throws SQLException {
    Recensione temp = new Recensione(valutazione, descrizione);
    recensioneManager.creaRecensione(temp);
  }


}
