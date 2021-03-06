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
  UtenteManager utenteManager = new UtenteManager();




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
        if (sessione.getRuolo().equals("Gestore")) {
          rimuoviAnnuncio(id);
          response.sendRedirect(request.getContextPath() + "/VisualeGestore.jsp?");

        } else {
          UtenteManager um = new UtenteManager();
          Utente u = um.recuperaPerUsername(sessione.getUsername());
          u.setNumAnnunci(u.getNumAnnunci() - 1);
          um.modificaUtente(u);
          rimuoviAnnuncio(id);
          response.sendRedirect(request.getContextPath() + "/AnnunciPersonali.jsp?");
           
        }
      }

      if (azione.equalsIgnoreCase("modificaAnnuncio")) {
        int id = Integer.parseInt(request.getParameter("id"));
        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        String username = request.getParameter("usernameUtente");
        if (usernameLog.equals(username)) {
          modificaAnnuncio(id, titolo, descrizione);
          response.sendRedirect(request.getContextPath() + "/AnnunciPersonali.jsp");
        }
      }

      if (azione.equalsIgnoreCase("Crea annuncio")) {
        if (sessione.getRuolo().equals("Utente")) {
          String utente = request.getParameter("usernameUtente");
          Utente u = utenteManager.recuperaPerUsername(utente);
          if (u.getNumAnnunci() < 5) {
            String dipartimento = request.getParameter("dipartimento");
            String titolo = request.getParameter("titolo");
            String descrizione = request.getParameter("descrizione");
            String tipologiastr = request.getParameter("tipologia");
            Boolean tipologia = false;
            if (tipologiastr.equals("1")) {
              tipologia = true;
            }
            creaAnnuncio(dipartimento, titolo, descrizione, tipologia, u.getUsername());
            response.sendRedirect(request.getContextPath()
                          + "/UtenteServlet?azione=aggiungiAnnuncio");
          } else {
            response.sendRedirect(request.getContextPath()
                         + "/Homepage.jsp?error=numAnnunci");
          }
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
      
      if (azione.equalsIgnoreCase("AggiungiSegnalazione")) {
        aggiungiSegnalazione(request);
        response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
      }
      
      

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }




  /**
   * Questo metodo si occupa di restituire tutti gli annunci presenti nel database.
   * @return la lista di annunci.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> stampaAnnunci() throws SQLException {
    return annuncioManager.recuperaAnnunci();

  }

  /**
   * Questo metodo si occupa di restituire tutti gli annunci di una data tipologia.
   * @param tipo dell'annuncio <code>true</code> se è un annuncio di tutorato.
   *          <code>false</code> se è un annuncio di gruppo di studio.
   * @return la lista degli annunci della tipologia passata come parametro.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Annuncio> recuperaPerTipologia(String descrizione, boolean tipo,
        String dipartimento) 
      throws SQLException {
    return annuncioManager.recuperaPerTipologia(descrizione, tipo, dipartimento);

  }
  
  /**
   * Questo metodo incrementa il contatore delle segnalazioni di un annuncio.
   * @param request richista client.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void aggiungiSegnalazione(HttpServletRequest request) throws SQLException {
    int id = Integer.parseInt(request.getParameter("id"));
    Annuncio a = annuncioManager.recuperaPerId(id);
    a.setNumSegnalazioni(a.getNumSegnalazioni() + 1);
    annuncioManager.modificaAnnuncio(a);
  }


  /**
   * Questo metodo si occupa di restituire tutti gli annunci di un utente. 
   * @param username di riferimento all'utente.
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
    if (temp == null) {
      System.out.println("Annuncio non trovato");
    } else {
      annuncioManager.rimuoviAnnuncio(temp);
    }
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
