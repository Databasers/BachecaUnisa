package gestioneannunci;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.DriverManagerConnectionPool;

/**
 * Il manager della classe Annuncio si occupa della gestione degli annunci: 
 * Della loro creazione, rimozione, modifica e della ricerca.
 * 
 *
 */

public class AnnuncioManager {

  private static final String TableName = "Annuncio";
  private static final int PAGINADIM = 10;
  
  /**
   * 
   * @param rs
   * @param numPagina
   * @return
   * @throws SQLException
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
   * @param annuncio da inserire nel db
   */
  public void creaAnnuncio(Annuncio annuncio) throws SQLException {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TableName + "VALUES(?,?,?,?,null, null, ?)";
    
    
    
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
   */
  public void rimuoviAnnuncio(Annuncio annuncio) throws SQLException {
    Connection connection = DriverManagerConnectionPool.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + TableName
        + "WHERE Id LIKE " + annuncio.getId());
    preparedStatement.executeQuery();
  }
  
  /**Questo metodo modifica l'annuncio selezionato nel database.
   * 
   * @param annuncio da modificare.
   * 
   */
  public void modificaAnnuncio(Annuncio annuncio) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    //Dipartimento, Titolo, Descrizione, Tipologia, NumeroSegnalazioni, ID, Utente_Username
    String sql = "UPDATE " + TableName + "SET Dipartimento = ?, Titolo = ?, Descrizione = ?"
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
   * Recupera tutti gli annunci esistenti-
   * @param numPagina
   * @return
   * @throws SQLException
   */
  public ArrayList<Annuncio> recuperaAnnunci(int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp = null;
   
    String sql = "SELECT* FROM " + TableName;

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
   * Recupera gli annunci della tipologia specificata.
   * @param tipo
   * @return
   * @throws SQLException
   */
  public ArrayList<Annuncio> recuperaPerTipologia(boolean tipo, int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    String str = String.valueOf(tipo);

    String sql = "SELECT* FROM " + TableName + " WHERE Tipologia = ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, str);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      if (!rs.next()) {
        temp = null; 
        }
               
      else {
        temp = listaAnnunci(rs, numPagina);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  
  /**
   * Recupera gli annunci del dipartimento specificato.
   * @param dipartimento
   * @param numPagina
   * @return
   * @throws SQLException
   */
  public ArrayList<Annuncio> recuperaPerDipartimento(String dipartimento, int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    
    String sql = "SELECT* FROM " + TableName + " WHERE Dipartimento = ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, dipartimento);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();

      if (!rs.next()) {
        temp = null;
      }
      else {
        temp = listaAnnunci(rs, numPagina);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  /**
   * Recupera tutti gli annunci di un singolo utente.
   * @param username
   * @param numPagina
   * @return
   * @throws SQLException
   */
  public ArrayList<Annuncio> recuperaPerUtente(String username, int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    
    String sql = "SELECT* FROM " + TableName + " WHERE Username = ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, username);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();

      if (!rs.next()) {
        temp = null;
      }
      else {
        temp = listaAnnunci(rs, numPagina);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
}
