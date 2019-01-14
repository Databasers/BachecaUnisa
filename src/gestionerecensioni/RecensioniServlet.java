package gestionerecensioni;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneannunci.Annuncio;

public class RecensioniServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    doGet(request, response);
    try {
      String azione = request.getParameter("azione");  
      if (azione == "stampaRecensioni") {
        int numPagina = Integer.parseInt(request.getParameter("numPagina")); 
        stampaRecensioni(numPagina);
      }

      if (azione == "rimuoviRecensione") {
        int id = Integer.parseInt(request.getParameter("id"));
        rimuoviRecensione(id);
      }

      if (azione == "modificaRecensione") {
        int id = Integer.parseInt(request.getParameter("id"));
        int valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String descrizione = request.getParameter("descrizione");
        modificaRecensione(id, valutazione, descrizione);

      }

      if (azione == "creaRecensione") {
        int valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String descrizione = request.getParameter("descrizione");
        creaRecensione(valutazione, descrizione);
      }


      if (azione == "visualizzaRecensione") {
        int id = Integer.parseInt(request.getParameter("id"));
        visualizzaRecensione(id);
      }
    }

    catch (Exception exc) {

    }

    finally {};



  }




  /**
   * Questo metodo si occupa di restituire tutti le recensioni presenti nel database.
   * @param numPagina il numero della pagina attualmente visualizzata dall'utente.
   */
  private ArrayList<Recensione> stampaRecensioni(int numPagina) {
    return null;

  }



  /**
   * Questo metodo si occupa di rimuovere una recensione dal database.
   * @param id l'id della recensione da rimuovere
   */
  private void rimuoviRecensione(int id) {

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
   */
  private void creaRecensione(int valutazione, String descrizione) {
    
  }

  
  private Recensione visualizzaRecensione(int id) {
    return null;

  }


  
}
