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

      try{
          String azione= request.getParameter("azione");  
          if(azione=="prendiAnnunci") {
              String numPagina = request.getParameter("numPagina"); 
              prendiAnnunci(numPagina);
          }
          
          if(azione=="rimuoviAnnuncio") {
              String iD= request.getParameter("iD");
              rimuoviAnnuncio(iD);
          }
          
          if(azione=="modificaAnnuncio") {

              String titolo= request.getParameter("titolo");
              String descrizione= request.getParameter("descrizione");
              modificaAnnuncio(titolo, descrizione);
              
          }
          
          if(azione=="creaAnnuncio") {
              
              String dipartimento= request.getParameter("dipartimento");
              String titolo= request.getParameter("titolo");
              String descrizione= request.getParameter("descrizione");
              String tipologia= request.getParameter("tipologia");
              
              creaAnnuncio(dipartimento, titolo, descrizione,tipologia);
              
          }
          
          
          if(azione=="cercaAnnuncioiD") {
              
              String iD= request.getParameter("iD");
              cercaAnnuncioiD(iD);
              
          }
          
          if(azione=="cercaAnnuncioFiltri") {
              
              //DA AGGIUNGERE
              cercaAnnuncioFiltri();
              
          }
          
      }
      
      catch(Exception exc){
          
      }
      
      finally{};
      
      
     
  }
  
  private void cercaAnnuncioFiltri() {
    // TODO Auto-generated method stub
    
}


/**Questo metodo si occupa di restituire tutti gli annunci presenti nel database
 * @param numPagina il numero della pagina attualmente visualizzata dall'utente
   */
  private Annuncio prendiAnnunci(String numPagina) {
    return null;
      
  }

  

  /**
   * Questo metodo si occupa di rimuovere un annuncio dal database.
   * @param iD L'ID dell'annuncio da rimuovere
   */
  private void rimuoviAnnuncio(String iD) {
      
  }
  

  /**Questo metodo si occupa di modificare l'annuncio scelto all'interno del database
 * @param descrizione 
 * @param titolo 
   */
  
  private void modificaAnnuncio(String titolo, String descrizione) {
      
  }
  

  /**Questo metodo crea un annuncio all'interno del datbase
 * @param tipologia Specifica se si tratta di un gruppo di studio o un tutoato
 * @param descrizione 
 * @param titolo 
 * @param dipartimento 
   */
  
  private void creaAnnuncio(String dipartimento, String titolo, String descrizione, String tipologia) {
      
  }
  

  /**Questo metodo si occupa di cercare un annuncio tramite il suo ID all'interno del database
 * @param iD 
   */
  
  public Annuncio cercaAnnuncioiD(String iD) {
    return null;
      
  }
  
  
}
