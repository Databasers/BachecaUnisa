package gestioneannunci;

import gestioneutenti.SessioneUtente;
import gestioneutenti.Utente;
import gestioneutenti.UtenteManager;

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
        String filtro = request.getParameter("filtro");
        if (filtro.equalsIgnoreCase("gruppo")) {
          ArrayList<Annuncio> risultato = recuperaPerTipologia(request.getParameter("descrizione"),
                false, request.getParameter("dipartimento"));
          request.getSession().setAttribute("arisultato", risultato);
          UtenteManager um = new UtenteManager();
          Utente u = um.recuperaPerUsername(request.getParameter("descrizione"));
          ArrayList<Utente> urisul = new ArrayList<Utente>();
          if (u != null) {
            urisul.add(u);
          }
          request.getSession().setAttribute("urisultato", urisul);
          response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
          
        } else if (filtro.equalsIgnoreCase("tutorato")) {
          ArrayList<Annuncio> risultato = recuperaPerTipologia(request.getParameter("descrizione"),
                true, request.getParameter("dipartimento"));
          request.getSession().setAttribute("arisultato", risultato);
          UtenteManager um = new UtenteManager();
          Utente u = um.recuperaPerUsername(request.getParameter("descrizione"));
          ArrayList<Utente> urisul = new ArrayList<Utente>();
          if (u != null) {
            urisul.add(u);
          }
          request.getSession().setAttribute("urisultato", urisul);
          response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
        
        } else if (filtro.equalsIgnoreCase("utente")) {
          String utente = request.getParameter("usernameUtente");
          ArrayList<Annuncio> risultato = recuperaPerUtente(utente);
          request.getSession().setAttribute("arisultato", risultato);
          if (request.getParameter("luogo") != null) {
            response.sendRedirect(request.getContextPath() + "/AnnunciPersonali.jsp");
          } else {
            response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
          }
          
        } else {
          System.out.println("Nessun filtro");
          ArrayList<Annuncio> risultato = stampaAnnunci();
          request.getSession().setAttribute("arisultato", risultato);
          response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
          
        }
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
          response.sendRedirect(request.getContextPath() + "/ProfiloPersonale.jsp");
        }
      }

      if (azione.equalsIgnoreCase("Crea annuncio")) {
        if (sessione.getRuolo().equals("Utente")) {
          String utente = request.getParameter("usernameUtente");
          String dipartimento = request.getParameter("dipartimento");
          String titolo = request.getParameter("titolo");
          String descrizione = request.getParameter("descrizione");
          boolean tipologia = Boolean.valueOf(request.getParameter("tipologia"));
          creaAnnuncio(dipartimento, titolo, descrizione, tipologia, utente);
          response.sendRedirect(request.getContextPath() 
              + "/UtenteServlet?azione=aggiungiAnnuncio");  
        }
      }

      if (azione.equalsIgnoreCase("visualizzannuncio")) {
        int id = Integer.parseInt(request.getParameter("id"));
        Annuncio annuncioTrovato = annuncioManager.recuperaPerId(id);
        System.out.println(annuncioTrovato.getUsernameUtente());
        request.getSession().setAttribute("annuncioTrovato", annuncioTrovato);
        System.out.println("Titolo:" + annuncioTrovato.getTitolo());
        System.out.println("\n ANNUNCIO TROVATO \n");
        
        if (request.getParameter("luogo").equalsIgnoreCase("se")) {
          response.sendRedirect(request.getContextPath() + "/segnalaAnnuncio.jsp?id=" + id);
          
        } else if (request.getParameter("luogo").equalsIgnoreCase("mo")) {
          response.sendRedirect(request.getContextPath() + "/modificaAnnuncio.jsp?id=" + id);
        
        } else {
          response.sendRedirect(request.getContextPath() + "/VisualizzaAnnuncio.jsp?id=" + id);
          
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }




  /**
   * Questo metodo si occupa di restituire tutti gli annunci presenti nel database 
   * basandosi sul numero della pagina visualizzata.
   * @param  il numero della pagina che l'utente visualizza.
   * @return la lista di annunci basandosi sul numero della pagina visualizzata.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> stampaAnnunci() throws SQLException {
    return annuncioManager.recuperaAnnunci();

  }

  /**
   * Questo metodo si occupa di restituire tutti gli annunci di una data tipologia.
   * @param tipo dell'annuncio <code>true</code> se � un annuncio di tutorato.
   *          <code>false</code> se � un annuncio di gruppo di studio.
   * @param  il numero della pagina che l'utente visualizza.
   * @return la lista degli annunci della tipologia passata come parametro.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> recuperaPerTipologia(String descrizione, boolean tipo,
        String dipartimento) 
      throws SQLException {
    return annuncioManager.recuperaPerTipologia(descrizione, tipo, dipartimento);

  }


  /**
   * Questo metodo si occupa di restituire tutti gli annunci di un utente. 
   * @param username di riferimento all'utente.
   * @param  il numero della pagina che l'utente visualizza.
   * @return la lista degli annunci dell'utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> recuperaPerUtente(String username) 
      throws SQLException {
    return annuncioManager.recuperaPerUtente(username);

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
    System.out.println(temp.getDescrizione());
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
