package gestionesegnalazioni;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        Segnalazione segnalazione = new Segnalazione(-1,
            request.getParameter("descrizione"), 
            Integer.parseInt(request.getParameter("motivazione")),
            Integer.parseInt(request.getParameter("recensione")),
            Integer.parseInt(request.getParameter("annuncio")));
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

    } finally {
      
    }
  }
  
  
  private void rimuoviSegnalazione(int id) throws SQLException {
    segnalazioneManager.rimuoviSegnalazione(id);
    
  }


  private void creaSegnalazione(Segnalazione segnalazione) throws SQLException {
    segnalazioneManager.creaSegnalazione(segnalazione);
    
  }


  private ArrayList<Segnalazione> stampaSegnalazioni(int numPagina) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    doGet(request, response);
  }
  
}
