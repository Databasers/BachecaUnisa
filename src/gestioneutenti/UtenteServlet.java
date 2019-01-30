package gestioneutenti;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtenteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  UtenteManager utenteManager;
  
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    doGet(request, response);
    try {
      String azione = request.getParameter("azione");  
      if (azione == "stampaUtenti") {
        int numPagina = Integer.parseInt(request.getParameter("numPagina"));
        stampaUtenti(numPagina);
      }

      if (azione == "rimuoviUtente") {
        String username = request.getParameter("username");
        rimuoviUtente(username);
      }

      if (azione == "modificaPassword") {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        modificaPassword(username, newPassword);

      }
      
      if (azione == "modificaUtente") {
        String username = request.getParameter("username");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String descrizione = request.getParameter("descrizione");
        modificaUtente(username, nome, cognome, descrizione);

      }
      
      

      if (azione == "creaUtente") {
        Utente u;
        u = utenteManager.recuperaPerUsername(request.getParameter("username"));
        if (u != null) {
          System.out.println("Utente gi� registrato");
          request.setAttribute("alreadyRegistered","true"); //Gi� registrato
          System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
          RequestDispatcher x = getServletContext().getRequestDispatcher("/HTML/Login.jsp"); 
          //da modificare
          x.forward(request, response); 
        }  else {
          String username = request.getParameter("username");
          String nome = request.getParameter("nome");
          String cognome = request.getParameter("cognome");
          String sesso = request.getParameter("sesso");
          String password = request.getParameter("password");
          String descrizione = request.getParameter("descrizione");
          Boolean gestore = Boolean.valueOf(request.getParameter("gestore"));
          creaUtente(u, username, nome, cognome, sesso, password, descrizione, gestore);
          SessioneUtente su = new SessioneUtente(u);
          request.getSession().setAttribute("Utente",su);
          System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
          response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); //da modificare
        }
      }
      
      if (azione == "Login") {
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
          request.getSession().setAttribute("log", su);
          
          request.getSession().setAttribute("Utente", su);
          System.out.println("Login effettuato!");
          System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
          response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); //da modificare
        } catch (Exception e) {
          System.out.println("Utente non registrato");
          request.setAttribute("Done", "falso");
          System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
          RequestDispatcher x = getServletContext().getRequestDispatcher("/HTML/Login.jsp");
          //ritento il login
          x.forward(request, response);
        }
        
        if (azione == "Logout") {
          System.out.println("Logout");
          //controllo se non � loggato
          if (request.getSession().getAttribute("Utente") == null)  {
            response.sendRedirect(request.getContextPath() + "\\HTML\\Login.jsp");
          }
          else if (request.getSession().getAttribute("Gestore") == null)  {
            response.sendRedirect(request.getContextPath() + "\\HTML\\Login.jsp");
          }
          
          request.getSession().removeAttribute("Utente");
          request.getSession().removeAttribute("Gestore");
          request.getSession().invalidate();
          response.sendRedirect(request.getContextPath() + "\\HTML\\Homepage.jsp"); 
          System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
        }
        
        
      }
      
    } catch (Exception exc) {

    } finally {
      
    }



  }




  /**
   * Questo metodo si occupa di restituire tutti gli utenti.
   * @param numPagina il numero della pagina attualmente visualizzata dall'utente.
   * @throws SQLException 
   */
  private ArrayList<Utente> stampaUtenti(int numPagina) throws SQLException {
    return utenteManager.recuperaTutti(numPagina);
  }



  /**
   * Questo metodo si occupa di rimuovere una recensione dal database.
   * @param id l'id della recensione da rimuovere
   * @throws SQLException 
   */
  private void rimuoviUtente(String username) throws SQLException {
    utenteManager.eliminaUtente(username);
  }


  /**
   * Questo metodo si occupa di modificare i dati dell'utente.
   * @param nome dell'utente modificato
   * @param cognome dell'utente modificato
   * @param descrizione dell'utente modificata
   * @throws SQLException 
   */
  private void modificaUtente(String username, String nome, String cognome,
      String descrizione) throws SQLException {
    Utente temp = utenteManager.recuperaPerUsername(username);
    temp.setNome(nome);
    temp.setCognome(cognome);
    temp.setDescrizione(descrizione);
    utenteManager.modificaUtente(temp);
  }
  
  /**
   * Questo metodo permette di modificare la password dell'utente.
   * @param password aggiornata
   * @throws SQLException 
   */
  private void modificaPassword(String username, String newPassword) throws SQLException {
    Utente temp = utenteManager.recuperaPerUsername(username);
    temp.setPassword(newPassword);
    utenteManager.modificaUtente(temp);
  }


  /**
   * Inizializza un nuovo utente.
   * @param username dell'utente
   * @param nome dell'utente
   * @param cognome dell'utente
   * @param sesso dell'utente
   * @param password dell'utente
   * @param descrizione  dell'utente
   * @param numAnnunci numero di annunci creati dall'utente
   */
  private void creaUtente(Utente u, String username, String nome, String cognome, String sesso, 
      String password, String descrizione, boolean gestore) throws SQLException {
    System.out.println("Registrazione utente");
    u = new Utente(username, nome, cognome, sesso, password, descrizione, 0, gestore);
    utenteManager.salvaUtente(u);
  }
  
  

    


}