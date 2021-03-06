package gestionesegnalazioni;

import gestioneutenti.SessioneUtente;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * La Servlet della classe Segnalazione si occupa delle logiche applicative delle segnalazioni.
 */
@WebServlet("/SegnalazioniServlet")
public class SegnalazioniServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final SegnalazioneManager segnalazioneManager = new SegnalazioneManager();

  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    
    try {
      String azione = request.getParameter("azione");  
      
      if (azione.equalsIgnoreCase("stampaSegnalazioni")) {
        ArrayList<Segnalazione> caso = stampaSegnalazioni();
        request.getSession().setAttribute("Lista", caso);
        response.sendRedirect(request.getContextPath() + "/HTML/HomePageGestore");
        
      } else if (azione.equalsIgnoreCase("creaSegnalazione")) {
        SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
        Segnalazione segnalazione;
        
        if (request.getParameter("recensione").length() > 0) {
          segnalazione = new Segnalazione(Integer.parseInt(request.getParameter("recensione")),
              null);
          System.out.println("È una recensione");
          segnalazione.setDescrizione(request.getParameter("descrizione")); 
          segnalazione.setMotivazione(Integer.parseInt(request.getParameter("motivazione")));
          segnalazione.setUtente(su.getUsername());
          creaSegnalazione(segnalazione);
          response.sendRedirect(request.getContextPath() 
              + "/ProfiloPersonale.jsp");
        } else {
          segnalazione = new Segnalazione(null, Integer.parseInt(request.getParameter("annuncio")));
          System.out.println("È un annuncio");
          segnalazione.setDescrizione(request.getParameter("descrizione")); 
          segnalazione.setMotivazione(Integer.parseInt(request.getParameter("motivazione")));
          segnalazione.setUtente(su.getUsername());
          creaSegnalazione(segnalazione);
          response.sendRedirect(request.getContextPath() 
              + "/AnnunciServlet?azione=aggiungiSegnalazione&id=" + segnalazione.getIdSegnalato());
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
    System.out.println(segnalazione.toString());
    System.out.println(segnalazione.getId() + " " + segnalazione.getUtente() + " "
        + segnalazione.getIdSegnalato());
    if (segnalazioneManager.creaSegnalazione(segnalazione)) {
      System.out.println("Ogetto segnalato ancora valido");
    }

    
  }


  /**
   * Questo metodo si occupa di restituire tutte le segnalazioni presenti nel database.
   * @return la lista di segnalazioni basandosi sul numero della pagina visualizzata.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Segnalazione> stampaSegnalazioni() throws SQLException {
    return segnalazioneManager.recuperaSegnalazioni();
  }



  
}
