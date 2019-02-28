package gestionerecensioni;

import gestioneutenti.SessioneUtente;
import gestioneutenti.UtenteManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La Servlet della classe Recensione si occupa delle logiche applicative delle recensioni.
 */
@WebServlet("/RecensioniServlet")
public class RecensioniServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  RecensioneManager recensioneManager = new RecensioneManager();
  UtenteManager utenteManager = new UtenteManager();
  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    doPost(request, response);
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    
    SessioneUtente sessione = (SessioneUtente) request.getSession().getAttribute("Utente");
    String usernameLog = sessione.getUsername();
    
    try {
      String azione = request.getParameter("azione");  
      
      if (azione.equalsIgnoreCase("stampaRecensioni")) {
        String username = request.getParameter("username");
        ArrayList<Recensione> caso = stampaRecensioni(username);
        request.getSession().setAttribute("Lista", caso);
        String luogo = request.getParameter("luogo");
        response.sendRedirect(request.getContextPath() + "/" + luogo + ".jsp");
      }

      if (azione.equalsIgnoreCase("rimuoviRecensione")) {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("usernameUtente");
        if (sessione.getRuolo().equalsIgnoreCase("Gestore")) {
          rimuoviRecensione(id);
          response.sendRedirect(request.getContextPath() + "/HomepageGestore.jsp");
        } else {
          if (usernameLog.equalsIgnoreCase(username)) {
            rimuoviRecensione(id);
            response.sendRedirect(request.getContextPath() + "/ProfiloPersonale.jsp");
          }
        }
      }
      


      if (azione.equalsIgnoreCase("modificaRecensione")) {
        int id = Integer.parseInt(request.getParameter("id"));
        int valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String descrizione = request.getParameter("descrizione");
        String username = request.getParameter("usernameUtente");
        if (usernameLog.equalsIgnoreCase(username)) {
          modificaRecensione(id, valutazione, descrizione);
          response.sendRedirect(request.getContextPath() + "/profiloPersonale");
        }
      }
      
      if (azione.equalsIgnoreCase("segnalaRecensione")) {
        int id = Integer.parseInt(request.getParameter("id"));
        Recensione rec = recensioneManager.recuperaPerId(id);
        request.getSession().setAttribute("recensione", rec);
        response.sendRedirect(request.getContextPath() + "/");
        
      }
      

      if (azione.equalsIgnoreCase("creaRecensione")) {
        if (sessione.getRuolo().equalsIgnoreCase("Utente")) {
          int valutazione = Integer.parseInt(request.getParameter("rating"));
          String descrizione = request.getParameter("descrizione");
          String mittente = request.getParameter("mittente");
          String destinatario = request.getParameter("destinatario");
          System.out.println("CREOLARECENSIONE");
          System.out.println(valutazione + " " + descrizione + " " + mittente + " " + destinatario);
          creaRecensione(valutazione, descrizione, mittente, destinatario);
          System.out.println("\n RECENSIONE CREATA \n");
          response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
        }
      }
      
      if (azione.equalsIgnoreCase("AggiungiSegnalazione")) {
        aggiungiSegnalazione(request);
        response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
      }
      
    }    catch (SQLException e) {
      e.printStackTrace();
    }
  }




  /**
   * Questo metodo si occupa di restituire tutte le recensioni dell'utente 
   * passato come parametro basandosi sul numero della pagina visualizzata.
   * @param username identificativo dell'utente.
   * @param  il numero della pagina attualmente visualizzata dall'utente.
   * @return la lista delle recensioni di un utente basandosi sul numero della pagina visualizzata
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Recensione> stampaRecensioni(String username)
      throws SQLException {
    return recensioneManager.recuperaRecensioni(username);
  }
  
  /**
   * Questo metodo incrementa il contatore delle segnalazioni di una recensione.
   * @param request richista client.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void aggiungiSegnalazione(HttpServletRequest request) throws SQLException {
    int id = Integer.parseInt(request.getParameter("id"));
    Recensione a = recensioneManager.recuperaPerId(id);
    a.setNumSegnalazioni(a.getNumSegnalazioni()+1);
    recensioneManager.modificaRecensione(a);
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
   * @param mittente della nuova recensione.
   * @param destinatario della nuova recensione.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void creaRecensione(int valutazione, String descrizione, 
      String mittente, String destinatario) throws SQLException {
    Recensione temp = new Recensione(valutazione, descrizione, mittente, destinatario);
    recensioneManager.creaRecensione(temp);
  }


}
