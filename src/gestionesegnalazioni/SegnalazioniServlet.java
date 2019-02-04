package gestionesegnalazioni;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionerecensioni.Recensione;
import gestioneutenti.SessioneUtente;

public class SegnalazioniServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  

  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    SessioneUtente sessione = (SessioneUtente) request.getSession().getAttribute("log");
    String usernameLog = sessione.getUsername();
    
    try {
      String azione = request.getParameter("azione");  
      
      if (azione.equalsIgnoreCase("stampaSegnalazioni")) {
        int numPagina = Integer.parseInt(request.getParameter("numPagina"));
        ArrayList<Segnalazione> caso = stampaSegnalazioni(numPagina);
        request.getSession().setAttribute("Lista", caso);
        response.sendRedirect(request.getContextPath() + "/HTML/HomePageGestore");
      } else if (azione.equalsIgnoreCase("creaSegnalazione")) {
        creaSegnalazione();
        
      } else if (azione.equalsIgnoreCase("rimuoviSegnalazione")){
        rimuoviSegnalazione();
      }

    } catch (Exception exc) {

    } finally {
      
    }
  }
  
  
  private void rimuoviSegnalazione() {
    // TODO Auto-generated method stub
    
  }


  private void creaSegnalazione() {
    // TODO Auto-generated method stub
    
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
