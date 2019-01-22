package gestioneannunci;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnunciServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  AnnuncioManager annuncio;
  
  
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
        String filtro = request.getParameter("Filtro");
        switch (filtro) {  
          //Non sono sicuro lo switch funzioni con le stringhe, forse è case sensitive
          case "tipologia": ricercaPerTipologia();
          
            break;

          default: stampaAnnunci(numPagina);
          break;
        }
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
      
      if (azione == "recuperaPerTipologia") {
        boolean tipo = Boolean.parseBoolean(request.getParameter("tipologia"));
        recuperaPerTipologia(tipo);
      }
    } finally {
      
    }



  }




  /**
   * Questo metodo si occupa di restituire tutti gli annunci presenti nel database.
   * @param numPagina il numero della pagina attualmente visualizzata dall'utente.
   */
  private ArrayList<Annuncio> stampaAnnunci(int numPagina) {
    return null;

  }
  
  /**
   * Questo metodo si occupa di restituire tutti gli annunci di una data tipologia.
   * @param tipo dell'annuncio <code>true</code> se è un annuncio di tutorato.
   *          <code>false</code> se è un annuncio di gruppo di studio.
   * @return la lista degli annunci della tipologia passata come parametro.
   */
  private ArrayList<Annuncio> recuperaPerTipologia(boolean tipo) {
    return annuncio.recuperaPerTipologia(tipo);

  }
  
  /**
   * Questo metodo si occupa di restituire tutti gli annunci di una dato dipartimento.
   * @param dipartimento filtro
   * @return la lista degli annunci del dipartimento passato come parametro.
   */
  private ArrayList<Annuncio> recuperaPerDipartimento(String dipartimento) {
    return null;

  }
  
  /**
   *Questo metodo si occupa di restituire tutti gli annunci di un utente. 
   * @param username di riferimento all'utente.
   * @return la lista degli annunci dell'utente.
   */
  private ArrayList<Annuncio> recuperaPerUtente(String username) {
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
    return null;
  }
  
  




}
