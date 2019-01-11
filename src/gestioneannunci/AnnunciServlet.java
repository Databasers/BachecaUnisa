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
<<<<<<< HEAD
        
      try {
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
          
          if(azione=="cercaAnnuncioiD") {
              
              String iD= request.getParameter("iD");
              cercaAnnuncioiD(iD);
              
          }
          
          if(azione=="cercaAnnuncioFiltri") {
              
              //DA AGGIUNGERE
              cercaAnnuncioFiltri();
              
          }
          
          //altre ricerche: 
      }
      
      finally ();
      
      
     
  }
  
  private void cercaAnnuncioFiltri() {
    // TODO Auto-generated method stub
    
}


/**Questo metodo si occupa di restituire tutti gli annunci presenti nel database
 * @param numPagina 
=======
   
    String dipartimento = request.getParameter("dipartimento");
    String titolo = request.getParameter("titolo");
    String descrizione = request.getParameter("descrizione");
    String tipologia = request.getParameter("tipologia");
    int numSegnalazione = 0;
  }
  
  /**
   * Questo metodo si occupa di cercare un annuncio all'interno del database.
>>>>>>> 59b6be9ad9fdea3cebbf595a97a4b7e1b8f89242
   * 
   */
  private Annuncio prendiAnnunci(String numPagina) {
    return null;
      
  }
  
<<<<<<< HEAD
  /**Questo metodo si occupa di rimuovere un annuncio dal database
 * @param iD 
=======
  /**
   * Questo metodo si occupa di rimuovere un annuncio dal database.
>>>>>>> 59b6be9ad9fdea3cebbf595a97a4b7e1b8f89242
   * 
   */
  private void rimuoviAnnuncio(String iD) {
      
  }
  
<<<<<<< HEAD
  /**Questo metodo si occupa di modificare l'annuncio scelto all'interno del database
 * @param descrizione 
 * @param titolo 
=======
  /**
   * Questo metodo si occupa di modificare l'annuncio scelto all'interno del database.
>>>>>>> 59b6be9ad9fdea3cebbf595a97a4b7e1b8f89242
   * 
   */
  
  private void modificaAnnuncio(String titolo, String descrizione) {
      
  }
  
<<<<<<< HEAD
  /**Questo metodo crea un annuncio all'interno del datbase
 * @param tipologia 
 * @param descrizione 
 * @param titolo 
 * @param dipartimento 
=======
  /**
   * Questo metodo crea un annuncio all'interno del datbase.
>>>>>>> 59b6be9ad9fdea3cebbf595a97a4b7e1b8f89242
   * 
   */
  
  private void creaAnnuncio(String dipartimento, String titolo, String descrizione, String tipologia) {
      
  }
  
<<<<<<< HEAD
  /**Questo metodo si occupa di cercare un annuncio all'interno del database
 * @param iD 
=======
  /**
   * Questo metodo si occupa di cercare un annuncio all'interno del database.
>>>>>>> 59b6be9ad9fdea3cebbf595a97a4b7e1b8f89242
   * 
   */
  
  public Annuncio cercaAnnuncioiD(String iD) {
    return null;
      
  }
  
  
}
