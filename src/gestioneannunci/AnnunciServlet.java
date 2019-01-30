package gestioneannunci;

import gestioneutenti.SessioneUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class AnnunciServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  AnnuncioManager annuncioManager;
  
  
  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    doPost(request, response);
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    
    SessioneUtente sessione = (SessioneUtente) request.getSession().getAttribute("log");
    String usernameLog = sessione.getUsername();

    try {
      String azione = request.getParameter("azione");  
      int numPagina = Integer.parseInt(request.getParameter("numPagina"));
      if (azione == "stampaAnnunci") {
        String filtro = request.getParameter("Filtro");
        switch (filtro) {  
          //Non sono sicuro lo switch funzioni con le stringhe, forse è case sensitive
          case "tipologia": { 
            String tipo = request.getParameter("tipologia");
            boolean check = true;
            if (tipo.equalsIgnoreCase("gruppo")) {
              check = false;
            }
            recuperaPerTipologia(check, numPagina);
            break;
          }
          case "dipartimento": {
            String dipartimento = request.getParameter("dipartimento");
            recuperaPerDipartimento(dipartimento, numPagina);
            break;
          }
          case "utente": {
            String utente = request.getParameter("username");
            recuperaPerUtente(utente, numPagina);
            break;
          }
          default: stampaAnnunci(numPagina);
          break;
        }
        stampaAnnunci(numPagina);
      }

      if (azione == "rimuoviAnnuncio") {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("usernameUtente");
        if (sessione.getRuolo().equals("Gestore")) {
          rimuoviAnnuncio(id);
        } else {
          if (usernameLog.equals(username)) {
            rimuoviAnnuncio(id);
          }
        }
      }

      if (azione == "modificaAnnuncio") {
        int id = Integer.parseInt(request.getParameter("id"));
        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        String username = request.getParameter("usernameUtente");
        if (usernameLog.equals(username)) {
          modificaAnnuncio(id, titolo, descrizione);
        }
      }

      if (azione == "creaAnnuncio") {
        if (sessione.getRuolo().equals("Utente")) {
          String utente = request.getParameter("username");
          String dipartimento = request.getParameter("dipartimento");
          String titolo = request.getParameter("titolo");
          String descrizione = request.getParameter("descrizione");
          boolean tipologia = Boolean.valueOf(request.getParameter("tipologia"));
          String username = request.getParameter("usernameUtente");
          creaAnnuncio(dipartimento, titolo, descrizione, tipologia, utente);
        }
      }



      if (azione == "visualizzaAnnuncio") {
        int id = Integer.parseInt(request.getParameter("id"));
        Annuncio annuncioTrovato = annuncioManager.recuperaPerId(id);
        request.setAttribute("annuncioTrovato", annuncioTrovato);
        RequestDispatcher rd = request.getRequestDispatcher("Annuncio.jsp"); //da modificare
        rd.forward(request, response);
      }
      
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      
    }



  }




  /**
   * Questo metodo si occupa di restituire tutti gli annunci presenti nel database.
   * @param numPagina il numero della pagina attualmente visualizzata dall'utente.
   * @throws SQLException 
   */
  private ArrayList<Annuncio> stampaAnnunci(int numPagina) throws SQLException {
    return annuncioManager.recuperaAnnunci(numPagina);

  }
  
  /**
   * Questo metodo si occupa di restituire tutti gli annunci di una data tipologia.
   * @param tipo dell'annuncio <code>true</code> se è un annuncio di tutorato.
   *          <code>false</code> se è un annuncio di gruppo di studio.
   * @return la lista degli annunci della tipologia passata come parametro.
   * @throws SQLException 
   */
  private ArrayList<Annuncio> recuperaPerTipologia(boolean tipo, int numPagina) 
      throws SQLException {
    return annuncioManager.recuperaPerTipologia(tipo, numPagina);

  }
  
  /**
   * Questo metodo si occupa di restituire tutti gli annunci di una dato dipartimento.
   * @param dipartimento filtro
   * @return la lista degli annunci del dipartimento passato come parametro.
   */
  private ArrayList<Annuncio> recuperaPerDipartimento(String dipartimento, int numPagina) 
      throws SQLException {
    return annuncioManager.recuperaPerDipartimento(dipartimento, numPagina);

  }
  
  /**
   *Questo metodo si occupa di restituire tutti gli annunci di un utente. 
   * @param username di riferimento all'utente.
   * @return la lista degli annunci dell'utente.
   */
  private ArrayList<Annuncio> recuperaPerUtente(String username, int numPagina) 
      throws SQLException{
    return annuncioManager.recuperaPerUtente(username, numPagina);

  }



  /**
   * Questo metodo si occupa di rimuovere un annuncio dal database.
   * @param id l'id dell'annuncio da rimuovere
   * @throws SQLException 
   */
  private void rimuoviAnnuncio(int id) throws SQLException {
    Annuncio temp = annuncioManager.recuperaPerId(id);
    annuncioManager.rimuoviAnnuncio(temp);
  }


  /**
   * Questo metodo si occupa di modificare l'annuncio scelto all'interno del database.
   * @param id dell'annuncio da modificare
   * @param descrizione nuova descrizione
   * @param titolo nuovo titolo
   * @throws SQLException 
   */

  private void modificaAnnuncio(int id, String titolo, String descrizione) throws SQLException {
    Annuncio temp = annuncioManager.recuperaPerId(id);
    temp.setTitolo(titolo);
    temp.setDescrizione(descrizione);
    annuncioManager.modificaAnnuncio(temp);
  }


  /**
   * Questo metodo crea un annuncio all'interno del database.
   * @param dipartimento
   * @param titolo
   * @param descrizione
   * @param tipologia
   * @param username utente
   * @throws SQLException
   */
  private void creaAnnuncio(String dipartimento, String titolo, String descrizione, 
      boolean tipologia, String username) throws SQLException {
    Annuncio temp = new Annuncio(titolo, descrizione, tipologia, dipartimento, username);
    annuncioManager.creaAnnuncio(temp);
  }


}
