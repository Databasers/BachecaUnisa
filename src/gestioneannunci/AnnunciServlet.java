package gestioneannunci;

import gestioneutenti.SessioneUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La Servlet della classe Annuncio si occupa delle logiche applicative degli annunci.
 */

@WebServlet("/AnnunciServlet")
public class AnnunciServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  AnnuncioManager annuncioManager = new AnnuncioManager();




  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    doPost(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    SessioneUtente sessione = (SessioneUtente) request.getSession().getAttribute("Utente");
    String usernameLog = sessione.getUsername();

    try {
      String azione = request.getParameter("azione");  
      System.out.println(azione);
      if (azione.equalsIgnoreCase("stampaAnnunci")) {
        int numPagina = Integer.parseInt(request.getParameter("numPagina"));
        String filtro = request.getParameter("filtro");
        if(filtro.equalsIgnoreCase("tipologia")) {
          String tipo = request.getParameter("tipologia");
          boolean check = true;
          if (tipo.equalsIgnoreCase("gruppo")) {
            check = false;
          }
          ArrayList<Annuncio> risultato = recuperaPerTipologia(check, numPagina);
          request.getSession().setAttribute("arisultato", risultato);
        } else if(filtro.equalsIgnoreCase("dipartimento")) {
          String dipartimento = request.getParameter("dipartimento");
          ArrayList<Annuncio> risultato = recuperaPerDipartimento(dipartimento, numPagina);
          request.getSession().setAttribute("arisultato", risultato);
        
        } else if(filtro.equalsIgnoreCase("utente")) {
          String utente = request.getParameter("usernameUtente");
          ArrayList<Annuncio> risultato = recuperaPerUtente(utente, numPagina);
          request.getSession().setAttribute("arisultato", risultato);
          
        } else {
          System.out.println("Nessun filtro");
          ArrayList<Annuncio> risultato = stampaAnnunci(numPagina);
          request.getSession().setAttribute("arisultato", risultato);
          
        }
        response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
      }
      if (azione.equalsIgnoreCase("rimuoviAnnuncio")) {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("usernameUtente");
        if (sessione.getRuolo().equals("Gestore")) {
          rimuoviAnnuncio(id);
          response.sendRedirect(request.getContextPath() + "/VisualeGestore.jsp?");

        } else {
          if (usernameLog.equalsIgnoreCase(username)) {
            rimuoviAnnuncio(id);
            response.sendRedirect(request.getContextPath() + "/ProfiloPersonale.jsp?");
            //TODO Ogni profilo utente deve avere l'id
          }
        }
      }

      if (azione.equalsIgnoreCase("modificaAnnuncio")) {
        int id = Integer.parseInt(request.getParameter("id"));
        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        String username = request.getParameter("usernameUtente");
        if (usernameLog.equals(username)) {
          modificaAnnuncio(id, titolo, descrizione);
          response.sendRedirect(request.getContextPath() + "/ProfiloPersonale.jsp"); //TODO
        }
      }

      if (azione.equalsIgnoreCase("creaAnnuncio")) {
        if (sessione.getRuolo().equals("Utente")) {
          String utente = request.getParameter("usernameUtente");
          String dipartimento = request.getParameter("dipartimento");
          String titolo = request.getParameter("titolo");
          String descrizione = request.getParameter("descrizione");
          boolean tipologia = Boolean.valueOf(request.getParameter("tipologia"));
          creaAnnuncio(dipartimento, titolo, descrizione, tipologia, utente);
          response.sendRedirect(request.getContextPath() + "/ProfiloPersonale.jsp");  //TODO
        }
      }



      if (azione.equalsIgnoreCase("visualizzaAnnuncio")) {
        int id = Integer.parseInt(request.getParameter("id"));
        Annuncio annuncioTrovato = annuncioManager.recuperaPerId(id);
        request.getSession().setAttribute("annuncioTrovato", annuncioTrovato);
        response.sendRedirect(request.getContextPath() + "/VisualizzaAnnuncio.jsp?id=" + id);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }




  /**
   * Questo metodo si occupa di restituire tutti gli annunci presenti nel database 
   * basandosi sul numero della pagina visualizzata.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista di annunci basandosi sul numero della pagina visualizzata.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> stampaAnnunci(int numPagina) throws SQLException {
    return annuncioManager.recuperaAnnunci(numPagina);

  }

  /**
   * Questo metodo si occupa di restituire tutti gli annunci di una data tipologia.
   * @param tipo dell'annuncio <code>true</code> se � un annuncio di tutorato.
   *          <code>false</code> se � un annuncio di gruppo di studio.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista degli annunci della tipologia passata come parametro.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> recuperaPerTipologia(boolean tipo, int numPagina) 
      throws SQLException {
    return annuncioManager.recuperaPerTipologia(tipo, numPagina);

  }
  

  /**
   * Questo metodo si occupa di restituire tutti gli annunci di un dato dipartimento.
   * @param dipartimento filtro.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista degli annunci del dipartimento passato come parametro.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> recuperaPerDipartimento(String dipartimento, int numPagina) 
      throws SQLException {
    return annuncioManager.recuperaPerDipartimento(dipartimento, numPagina);

  }

  /**
   * Questo metodo si occupa di restituire tutti gli annunci di un utente. 
   * @param username di riferimento all'utente.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista degli annunci dell'utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> recuperaPerUtente(String username, int numPagina) 
      throws SQLException {
    return annuncioManager.recuperaPerUtente(username, numPagina);

  }



  /**
   * Questo metodo si occupa di rimuovere un annuncio dal database.
   * @param id dell'annuncio da rimuovere.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void rimuoviAnnuncio(int id) throws SQLException {
    Annuncio temp = annuncioManager.recuperaPerId(id);
    annuncioManager.rimuoviAnnuncio(temp);

  }


  /**
   * Questo metodo si occupa di modificare l'annuncio scelto all'interno del database.
   * @param id dell'annuncio da modificare.
   * @param descrizione nuova descrizione.
   * @param titolo nuovo titolo.
   * @throws SQLException in caso di errore di accesso al database.
   */

  private void modificaAnnuncio(int id, String titolo, String descrizione) throws SQLException {
    Annuncio temp = annuncioManager.recuperaPerId(id);
    temp.setTitolo(titolo);
    temp.setDescrizione(descrizione);
    annuncioManager.modificaAnnuncio(temp);
  }


  /**
   * Questo metodo crea un annuncio all'interno del database.
   * @param dipartimento del nuovo annuncio.
   * @param titolo del nuovo annuncio.
   * @param descrizione del nuovo annuncio.
   * @param tipologia del nuovo annuncio.
   * @param username utente che ha creato l'annuncio.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void creaAnnuncio(String dipartimento, String titolo, String descrizione, 
      boolean tipologia, String username) throws SQLException {
    Annuncio temp = new Annuncio(titolo, descrizione, tipologia, dipartimento, username);
    annuncioManager.creaAnnuncio(temp);
  }


}
