package gestioneutenti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La Servlet della classe Utente si occupa delle logiche applicative degli utenti.
 */
@WebServlet("/UtenteServlet")
public class UtenteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final String DEFAULT_DESCRIPTION = "Questo utente non ha ancora"
      + " scritto una descrizione :(";
  UtenteManager utenteManager = new UtenteManager();


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    

    SessioneUtente sessione = (SessioneUtente) request.getSession().getAttribute("Utente");

    try {
      String azione = request.getParameter("azione");
      System.out.println(azione);
      if (azione.equalsIgnoreCase("stampaUtenti")) {
        ArrayList<Utente> risultato = stampaUtenti();
        request.getSession().setAttribute("urisultato", risultato);
        System.out.println("\n LISTA DEGLI UTENTI CONCLUSA \n");
        response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
      }

      if (azione.equalsIgnoreCase("prelevaUtente")) {
        System.out.println("In Preleva utente");
        SessioneUtente l = (SessioneUtente) request.getSession().getAttribute("Utente");
        Utente u = prelevautente(request.getParameter("username"));
        System.out.println("Presi i due parametri");
        request.getSession().setAttribute("utenteTrovato", u);
        l.getUsername();
        u.getUsername();
        System.out.println(u.getUsername() + " == " + l.getUsername());
        if (request.getParameter("luogo").equalsIgnoreCase("crea")) {
          System.out.println("Luogo = crea");
          response.sendRedirect(request.getContextPath() + "/CreaNuovoAnnuncio.jsp");
        } else if (request.getParameter("luogo").equalsIgnoreCase("pro")) {
          System.out.println("Luogo = profilo");
          response.sendRedirect(request.getContextPath() + "/ProfiloPersonale.jsp");
        } else if (request.getParameter("luogo").equalsIgnoreCase("mod")) {
          System.out.println("Luogo = modProfilo");
          response.sendRedirect(request.getContextPath() + "/modificaProfilo.jsp");
        } else if (request.getParameter("luogo").equalsIgnoreCase("feed")) { 
          System.out.println("Luogo = feed");
          System.out.println(request.getContextPath() + "/RilascioFeedback.jsp?username="
              + u.getUsername());
          response.sendRedirect(request.getContextPath() + "/RilascioFeedback.jsp?username="
              + u.getUsername());
        } else {
          System.out.println("Luogo = profilo utente");
          response.sendRedirect(request.getContextPath() + "/ProfiloUtente.jsp?username="
              + u.getUsername());
        }
      }
      
      if (azione.equalsIgnoreCase("rimuoviUtente")) {
        if (sessione.getRuolo().equals("Gestore")) {
          String username = request.getParameter("username");
          rimuoviUtente(username);
          response.sendRedirect(request.getContextPath() + "/VisualeGestore.jsp");
        }
      }


      if (azione.equalsIgnoreCase("modificaPassword")) {
        String newPassword = request.getParameter("newPassword");
        String usernameLog = sessione.getUsername();
        modificaPassword(usernameLog, newPassword);
        response.sendRedirect(request.getContextPath() + "/ProfiloPersonale.jsp");
      }

      if (azione.equalsIgnoreCase("modificaUtente")) {
        String usernameLog = sessione.getUsername();
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String descrizione = request.getParameter("descrizione");
        Utente temp = utenteManager.recuperaPerUsername(usernameLog);
        temp.setNome(nome);
        temp.setCognome(cognome);
        temp.setDescrizione(descrizione);
        utenteManager.modificaUtente(temp);
        response.sendRedirect(request.getContextPath() + "/ProfiloPersonale.jsp");
      }



      if (azione.equalsIgnoreCase("creaUtente")) {
        System.out.println("Iniziato");
        if (utenteManager.recuperaPerUsername(request.getParameter("username")) != null) {
          System.out.println("Utente già registrato");
          System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
          response.sendRedirect(request.getContextPath() + "/registrazione.jsp?ar=t");
        }  else {
          String username = request.getParameter("username");
          String nome = request.getParameter("nome");
          String cognome = request.getParameter("cognome");
          String sesso = request.getParameter("sesso");
          String password = request.getParameter("password");
          creaUtente(username, nome, cognome, sesso, password, false);
          Utente u = new Utente(username, nome, cognome, sesso, password, DEFAULT_DESCRIPTION,
              0, false);
          SessioneUtente su = new SessioneUtente(u);
          request.getSession().setAttribute("Utente",su);
          System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
          response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
        }
      }

      if (azione.equalsIgnoreCase("Login")) {
        doLogin(request, response);     
      }

      if (azione.equalsIgnoreCase("Logout")) {
        doLogout(request, response);
      } 
      
      if (azione.equalsIgnoreCase("AggiungiAnnuncio")) {
        aggiungiAnnuncio(request);
        response.sendRedirect(request.getContextPath() + "/AnnunciPersonali.jsp");
      }
      

    } catch (Exception exc) {
      exc.printStackTrace();
    } 
    
  }



  /**
   * Questo metodo incrementa il contatore degli annunci di un utente.
   * @param request richista client.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void aggiungiAnnuncio(HttpServletRequest request) throws SQLException {
    SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
    Utente u = prelevautente(su.getUsername());
    u.setNumAnnunci(u.getNumAnnunci() + 1);
    utenteManager.modificaUtente(u);
    
  }

  /**
   * Questo metodo preleva un singolo utente dal database.
   * @param parameter l'username dell'utente
   * @return L'utente selezionato
   */
  private Utente prelevautente(String parameter) throws SQLException {
    System.out.println("Metodo Preleva utente iniziato");
    return utenteManager.recuperaPerUsername(parameter);
  }


  /**
   * Questo metodo si occupa di restituire tutti gli utenti.
   * @param  il numero della pagina attualmente visualizzata dall'utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private ArrayList<Utente> stampaUtenti() throws SQLException {
    return utenteManager.recuperaUtenti();
  }



  /**
   * Questo metodo si occupa di rimuovere un utente dal database.
   * @param username dell'utente da rimuovere.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void rimuoviUtente(String username) throws SQLException {
    Utente temp = utenteManager.recuperaPerUsername(username);
    utenteManager.rimuoviUtente(temp);
  }


  /**
   * Questo metodo si occupa di modificare i dati dell'utente.
   * @param username dell'utente da modificare.
   * @param nome dell'utente modificato.
   * @param cognome dell'utente modificato.
   * @param descrizione dell'utente modificata.
   * @throws SQLException in caso di errore di accesso al database.
   */

  
  /**
   * Questo metodo permette di modificare la password dell'utente.
   * @param username dell'utente da modificare.
   * @param newPassword aggiornata
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void modificaPassword(String username, String newPassword) throws SQLException {
    Utente temp = utenteManager.recuperaPerUsername(username);
    temp.setPassword(newPassword);
    utenteManager.modificaUtente(temp);
  }



  /**
   * Inizializza un nuovo utente.
   * @param u utente dichiarato precedentemente per il controllo sull'unicita' dell'username.
   * @param username dell'utente.
   * @param nome dell'utente.
   * @param cognome dell'utente.
   * @param sesso dell'utente.
   * @param password dell'utente.
   * @param descrizione dell'utente
   * @param gestore <code>true</code> se e' gestore.
   *                <code>false</code> se e' utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  private void creaUtente(String username, String nome, String cognome, String sesso, 
      String password, boolean gestore) throws SQLException {
    System.out.println("Registrazione utente");
    Utente u;
    u = new Utente(username, nome, cognome, sesso, password, 0, gestore);
    utenteManager.creaUtente(u);
  }
  
  /**
   * Permette di effettuare il Login.
   * @param request della servlet.
   * @param response request della servlet.
   * @throws ServletException in caso di errore dal lato Servlet.
   * @throws IOException in caso di errore di input non validi.
   */
  private void doLogin(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    try {
      Utente u = utenteManager.recuperaSeRegistrato(username, password);

      SessioneUtente su;
      if (u.isGestore() == true) {
        su = new SessioneUtente(u, "Gestore");
      } else {
        su = new SessioneUtente(u, "Utente");
      }
      request.getSession().setAttribute("Utente", su);
      System.out.println("Login effettuato!");
      System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
      response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
    } catch (Exception e) {
      System.out.println("Username o password non corretti. MESSAGE:" + e.getMessage());
      request.setAttribute("Done", "falso");
      System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
      RequestDispatcher x = getServletContext().getRequestDispatcher("/Login.jsp?error=true");
      //ritento il login
      x.forward(request, response);
    }
  }
  
  /**
   * Permette ad un utente di effettuare il Logout.
   * @param request request della servlet.
   * @param response request della servlet.
   * @throws ServletException in caso di errore dal lato Servlet.
   * @throws IOException in caso di errore di input non validi.
   */
  private void doLogout(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    System.out.println("Logout");
    //controllo se non � loggato
    if (request.getSession().getAttribute("Utente") == null)  {
      response.sendRedirect(request.getContextPath() + "/Login.jsp");
    } else if (request.getSession().getAttribute("Gestore") == null)  {
      response.sendRedirect(request.getContextPath() + "/Login.jsp");
    }
    
    request.getSession().removeAttribute("Utente");
    request.getSession().removeAttribute("Gestore");
    request.getSession().invalidate();
    response.sendRedirect(request.getContextPath() + "/Login.jsp"); 
    System.out.println("\n FINE GESTIONE LOGOUT \n");
  }

}