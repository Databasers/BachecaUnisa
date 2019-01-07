package gestioneannunci;

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
   
      String dipartimento= request.getParameter("dipartimento");
      String titolo= request.getParameter("titolo");
      String descrizione= request.getParameter("descrizione");
      String tipologia= request.getParameter("tipologia");
      int numSegnalazione= 0;
  }
  
  /**Questo metodo si occupa di cercare un annuncio all'interno del database
   * 
   */
  protected Annuncio prendiAnnunci() {
    return null;
      
  }
  
  /**Questo metodo si occupa di rimuovere un annuncio dal database
   * 
   */
  protected void rimuoviAnnuncio() {
      
  }
  
  /**Questo metodo si occupa di modificare l'annuncio scelto all'interno del database
   * 
   */
  
  protected void modificaAnnuncio() {
      
  }
  
  /**Questo metodo crea un annuncio all'interno del datbase
   * 
   */
  
  protected void creaAnnuncio() {
      
  }
  
  /**Questo metodo si occupa di cercare un annuncio all'interno del database
   * 
   */
  
  protected Annuncio cercaAnnuncio() {
    return null;
      
  }
  
  
  
  
  
  
  //prendiAnnuncio, rimuoviAnnuncio, modificaAnnuncio, cercaAnnuncio, creaAnnuncio
}
