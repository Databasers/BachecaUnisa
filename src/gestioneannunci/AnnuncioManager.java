package gestioneannunci;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.DriverManagerConnectionPool;

/**
 * Il manager della classe Annuncio si occupa della gestione degli annunci: 
 * della loro creazione, rimozione, modifica e della ricerca.
 */

public class AnnuncioManager {

  private static final String TABLENAME = "Annuncio";
  private static final int PAGINADIM = 10;
  
  /**
   * Il metodo crea un'ArrayList di annunci da un result set.
   * @param rs result set da listare.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return una lista di 10 annunci dal database basandosi dalla pagina specificata.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> listaAnnunci(ResultSet rs, int numPagina) throws SQLException {
    rs.first();
    ArrayList<Annuncio> lista = new ArrayList<Annuncio>();
    Annuncio temp;
    //Sposta il cursore alla posizione corretta
    //Qualcuno si faccia qualche simulazione per vedere se si muove nelle posizioni giuste
    for (int i = 0; i < numPagina * PAGINADIM; i++) {
      rs.next();
    }
    //Prende i prossimi 10 Annunci e li aggiunge alla lista
    //Again, fare conti per vedere se va
    for (int i = 0; i < 10; i++) {
     
      temp = new Annuncio();
      temp.setTitolo(rs.getString("Titolo"));
      temp.setDescrizione(rs.getString("Descrizione"));
      temp.setTipologia(rs.getBoolean("Tipologia"));
      temp.setDipartimento(rs.getString("Dipartimento"));
      temp.setUsernameUtente(rs.getString("Utente_Username"));
      lista.add(temp);
      rs.next();    
    } 
    return lista;  
  }
  
  

  /**
   * Questo metodo crea un nuovo annuncio nel database.
   * 
   * @param annuncio da inserire nel database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void creaAnnuncio(Annuncio annuncio) throws SQLException {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TABLENAME + "VALUES(?,?,?,?,null, null, ?)";
    
    
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setString(0, annuncio.getDipartimento());
      preparedStatement.setString(1, annuncio.getTitolo());
      preparedStatement.setString(2, annuncio.getDescrizione());
      int tip;
      if (annuncio.isTipologia()) {
        tip = 1;
      } else {
        tip = 0;
      }
      preparedStatement.setInt(3, tip);
      preparedStatement.setString(6, annuncio.getUsernameUtente());
      
      preparedStatement.executeUpdate();
      
      connection.commit();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }

  /**
   * Questo metodo rimuove l'annuncio selezionato dal database.
   * 
   * @param annuncio da rimuovere.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void rimuoviAnnuncio(Annuncio annuncio) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;


    String delete = "DELETE FROM " + TABLENAME + " WHERE Id = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(delete);
      preparedStatement.setInt(1, annuncio.getId());
      System.out.println("doDelete: " + preparedStatement.toString());
      preparedStatement.executeUpdate();
      
      connection.commit();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }
  
  /**
   * Questo metodo modifica l'annuncio selezionato nel database.
   * 
   * @param annuncio da modificare.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void modificaAnnuncio(Annuncio annuncio) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    //Dipartimento, Titolo, Descrizione, Tipologia, NumeroSegnalazioni, ID, Utente_Username
    String sql = "UPDATE " + TABLENAME + "SET Dipartimento = ?, Titolo = ?, Descrizione = ?"
        + ", Tipologia = ?, NumeroSegnalazioni = ?, ID = ?, Utente_Username = ?"
        + " WHERE ID = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(0, annuncio.getDipartimento());
      preparedStatement.setString(1, annuncio.getTitolo());
      preparedStatement.setString(2, annuncio.getDescrizione());
      int a;
      if (annuncio.isTipologia()) {
        a = 1;
      } else {
        a = 0;
      }
      preparedStatement.setInt(3, a);
      preparedStatement.setInt(4, annuncio.getNumSegnalazioni());
      preparedStatement.setInt(5, annuncio.getId());
      preparedStatement.setString(6, annuncio.getUsernameUtente());
      preparedStatement.setString(7, annuncio.getUsernameUtente());
     
      preparedStatement.executeUpdate();
      connection.commit();
    } finally {
      try { 
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }
  
  /**
   * Recupera tutti gli annunci esistenti.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista di tutti gli annunci basandosi sulla pagina visualizzata dall'utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> recuperaAnnunci(int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp = null;
   
    String sql = "SELECT* FROM " + TABLENAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        temp = listaAnnunci(rs, numPagina);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  /**
   * Recupera l'annuncio con l'id selezionato.
   * @param id dell'annuncio cercato.
   * @return l'annuncio con l'id selezionato.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public Annuncio recuperaPerId(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Annuncio temp = new Annuncio();
    String str = Integer.toString(id);

    String sql = "SELECT* FROM " + TABLENAME + " WHERE id = ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, str);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      if (!rs.next()) {
        temp = null; 
      } else {
        temp.setTitolo(rs.getString("titolo"));
        temp.setDescrizione(rs.getString("descrizione"));
        temp.setDipartimento(rs.getString("dipartimento"));
        temp.setTipologia(rs.getBoolean("tipologia"));
        temp.setUsernameUtente(rs.getString("username"));
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }

  /**
   * Recupera gli annunci della tipologia specificata.
   * @param tipo filtro.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista di tutti gli annunci, filtrati per tipologia,
   *          basandosi sulla pagina visualizzata dall'utente.
   * @throws SQLException @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> recuperaPerTipologia(boolean tipo, int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    String str = String.valueOf(tipo);

    String sql = "SELECT * FROM " + TABLENAME + " WHERE Tipologia = ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, str);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      if (!rs.next()) {
        temp = null; 
      } else {
        temp = listaAnnunci(rs, numPagina);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  
  /**
   * Recupera gli annunci del dipartimento specificato.
   * @param dipartimento filtro.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista di tutti gli annunci, filtrati per dipartimento,
   *         basandosi sulla pagina visualizzata dall'utente.
   * @throws SQLException @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> recuperaPerDipartimento(String dipartimento, int numPagina) 
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    
    String sql = "SELECT* FROM " + TABLENAME + " WHERE Dipartimento = ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, dipartimento);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();

      if (!rs.next()) {
        temp = null;
      } else {
        temp = listaAnnunci(rs, numPagina);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  /**
   * Recupera tutti gli annunci di un singolo utente.
   * @param username dell'utente
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista di tutti gli annunci creati da un utente
   *         basandosi sulla pagina visualizzata dall'utente stesso.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> recuperaPerUtente(String username, int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    
    String sql = "SELECT* FROM " + TABLENAME + " WHERE Username = ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, username);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();

      if (!rs.next()) {
        temp = null;
      } else {
        temp = listaAnnunci(rs, numPagina);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
}
