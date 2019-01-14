package gestioneannunci;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnunciServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    try {
      String azione = request.getParameter("azione");  
      if (azione == "stampaAnnunci") {
        int numPagina = Integer.parseInt(request.getParameter("numPagina")); 
        stampaAnnunci(numPagina);
      }

      if (azione == "rimuoviAnnuncio") {
        int id = Integer.parseInt(request.getParameter("id"));
        rimuoviAnnuncio(id);
      }

      if (azione == "modificaAnnuncio") {
        int id = Integer.parseInt(request.getParameter("id"));
        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        modificaAnnuncio(id, titolo, descrizione);

      }

      if (azione == "creaAnnuncio") {

        String dipartimento = request.getParameter("dipartimento");
        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        String tipologia = request.getParameter("tipologia");
        creaAnnuncio(dipartimento, titolo, descrizione,tipologia);
      }



      if (azione == "visualizzaAnnuncio") {
        int id = Integer.parseInt(request.getParameter("id"));
        visualizzaAnnuncio(id);
      }
    }

    catch (Exception exc) {

    }

    finally {};



  }




  /**
   * Questo metodo si occupa di restituire tutti gli annunci presenti nel database.
   * @param numPagina il numero della pagina attualmente visualizzata dall'utente.
   */
  private ArrayList<Annuncio> stampaAnnunci(int numPagina) {
    return null;

  }



  /**
   * Questo metodo si occupa di rimuovere un annuncio dal database.
   * @param id l'id dell'annuncio da rimuovere
   */
  private void rimuoviAnnuncio(int id) {

  }


  /**
   * Questo metodo si occupa di modificare l'annuncio scelto all'interno del database.
   * @param id dell'annuncio da modificare
   * @param descrizione nuova descrizione
   * @param titolo nuovo titolo
   */

  private void modificaAnnuncio(int id, String titolo, String descrizione) {

  }


  /**
   * Questo metodo crea un annuncio all'interno del database.
   * @param tipologia Specifica se si tratta di un gruppo di studio o un tutoato
   * @param descrizione del nuovo annuncio
   * @param titolo del nuovo annuncio
   * @param dipartimento del nuovo annuncio
   * @param tipologia del nuovo annuncio
   */
  private void creaAnnuncio(String dipartimento, String titolo, String descrizione, 
      String tipologia) {

  }

  private Annuncio visualizzaAnnuncio(int id) {

  }


  /**Questo metodo si occupa di cercare un annuncio tramite il suo ID all'interno del database.
   * @param iD 
   */



}
