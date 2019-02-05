package gestionesegnalazioni;

import gestioneutenti.SessioneUtente;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * La Servlet della classe Segnalazione si occupa delle logiche applicative delle segnalazioni.
 */
public class SegnalazioniServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final SegnalazioneManager segnalazioneManager = new SegnalazioneManager();

  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    
    try {
      String azione = request.getParameter("azione");  
      
      if (azione.equalsIgnoreCase("stampaSegnalazioni")) {
        int numPagina = Integer.parseInt(request.getParameter("numPagina"));
        ArrayList<Segnalazione> caso = stampaSegnalazioni(numPagina);
        request.getSession().setAttribute("Lista", caso);
        response.sendRedirect(request.getContextPath() + "/HTML/HomePageGestore");
      } else if (azione.equalsIgnoreCase("creaSegnalazione")) {
        //Non potendo assegnare null ad un intero scelgo di assegnare -1.
        //Il suo ID verrà comunque ignorato nel metodo crea segnalazione
        SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
        Segnalazione segnalazione = new Segnalazione(-1,
            request.getParameter("descrizione"), 
            Integer.parseInt(request.getParameter("motivazione")),
            Integer.parseInt(request.getParameter("recensione")),
            Integer.parseInt(request.getParameter("annuncio")),
            su.getNome());
        creaSegnalazione(segnalazione);
        if (segnalazione.isTipoSegnalazione()) {
          response.sendRedirect(request.getContextPath() + "/HTML/VisualizzaAnnuncio");
        }
        if (request.getParameter("luogo").equalsIgnoreCase("profilo")) {
          response.sendRedirect(request.getContextPath() + "/HTML/Profilo");
        } else {
          response.sendRedirect(request.getContextPath() + "/HTML/profilo_personale");
        }
        
      } else if (azione.equalsIgnoreCase("rimuoviSegnalazione")) {
        int id = Integer.parseInt(request.getParameter("ID"));
        rimuoviSegnalazione(id);
        response.sendRedirect(request.getContextPath() + "/HTML/HomePageGestore");
      }

    } catch (Exception exc) {
      exc.printStackTrace();
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    doGet(request, response);
  }
  
  /**
   * Questo metodo si occupa di rimuovere una segnalazione dal database.
   * @param id della segnalazione da rimuovere.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void rimuoviSegnalazione(int id) throws SQLException {
    Segnalazione temp = segnalazioneManager.recuperaPerId(id);
    segnalazioneManager.rimuoviSegnalazione(temp);
    
  }

  /**
   * uesto metodo crea una segnalazione all'interno del database.
   * @param segnalazione da inserire nel database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void creaSegnalazione(Segnalazione segnalazione) throws SQLException {
    if (segnalazioneManager.creaSegnalazione(segnalazione)) {
      System.out.println("Ogetto segnalato ancora valido");
    }
    
    
  }


  /**
   * Questo metodo si occupa di restituire tutte le segnalazioni presenti nel database.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Segnalazione> stampaSegnalazioni(int numPagina) throws SQLException {
    return segnalazioneManager.recuperaSegnalazioni(numPagina);
  }



  
}
