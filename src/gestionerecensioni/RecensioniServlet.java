package gestionerecensioni;

import gestioneutenti.SessioneUtente;
import gestioneutenti.UtenteManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La Servlet della classe Recensione si occupa delle logiche applicative delle recensioni.
 */

public class RecensioniServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  RecensioneManager recensioneManager;
  UtenteManager utenteManager;
  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    doPost(request, response);
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    
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
          response.sendRedirect(request.getContextPath() + "/HTML/HomepageGestore");
        } else {
          if (usernameLog.equals(username)) {
            rimuoviRecensione(id);
            response.sendRedirect(request.getContextPath() + "/HTML/profilo_personale");
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
          response.sendRedirect(request.getContextPath() + "/HTML/profilo_personale");
        }
      }
      

      if (azione == "creaRecensione") {
        if (sessione.getRuolo().equals("Utente")) {
          int valutazione = Integer.parseInt(request.getParameter("valutazione"));
          String descrizione = request.getParameter("descrizione");
          String mittente = request.getParameter("mittente");
          String destinatario = request.getParameter("destinatario");
          creaRecensione(valutazione, descrizione, mittente, destinatario);
          response.sendRedirect(request.getContextPath() + "/HTML/profilo_personale");
        }
      }
    }    catch (SQLException e) {
      e.printStackTrace();
    }
  }




  /**
   * Questo metodo si occupa di restituire tutte le recensioni dell'utente passato come parametro.
   * @param username identificativo dell'utente.
   * @param numPagina il numero della pagina attualmente visualizzata dall'utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Recensione> stampaRecensioni(String username, int numPagina)
      throws SQLException {
    return recensioneManager.recuperaRecensioni(username, numPagina);
  }



  /**
   * Questo metodo si occupa di rimuovere una recensione dal database.
   * @param id l'id della recensione da rimuovere
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void rimuoviRecensione(int id) throws SQLException {
    Recensione recensione = recensioneManager.recuperaPerId(id);
    recensioneManager.rimuoviRecensione(recensione);
  }


  /**
   * Questo metodo si occupa di modificare la recensione scelta all'interno del database.
   * @param id della recensione da modificare
   * @param descrizione nuova descrizione
   * @param valutazione nuovo valutazione
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void modificaRecensione(int id, int valutazione, String descrizione) throws SQLException {
    Recensione recensione = recensioneManager.recuperaPerId(id);
    recensione.setDescrizione(descrizione);
    recensione.setValutazione(valutazione);
    recensioneManager.modificaRecensione(recensione);
  }


  /**
   * Questo metodo crea una recensione all'interno del database.
   * @param valutazione della nuova recensione
   * @param descrizione della nuova recensione
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void creaRecensione(int valutazione, String descrizione, 
      String mittente, String destinatario) throws SQLException {
    Recensione temp = new Recensione(valutazione, descrizione, mittente, destinatario);
    recensioneManager.creaRecensione(temp);
  }


}
