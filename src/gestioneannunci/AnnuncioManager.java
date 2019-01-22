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
  
  public ArrayList<Annuncio> listaAnnunci(ResultSet rs, int numPagina) throws SQLException {
    ArrayList<Annuncio> lista = new ArrayList<Annuncio>();
    Annuncio temp;
    while (rs.next()) {
      temp = new Annuncio();
      temp.setTitolo(rs.getString("Titolo"));
      temp.setDescrizione(rs.getString("Descrizione"));
      temp.setTipologia(rs.getBoolean("Tipologia"));
      temp.setDipartimento(rs.getString("Dipartimento"));
      temp.setUsernameUtente(rs.getString("Utente_Username"));
      lista.add(temp);
   
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
   * 
   * 
   * @param numPagina
   */
  public void cercaAnnuncio(int numPagina) {
    
  }

  /**
   * 
   * @param tipo
   * @return
   * @throws SQLException
   */
  public Annuncio recuperaPerTipologia(boolean tipo) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Annuncio temp = new Annuncio();
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
        temp.setTitolo(rs.getString("Titolo"));
        temp.setDescrizione(rs.getString("Descrizione"));
        temp.setTipologia(rs.getBoolean("Tipologia"));
        temp.setDipartimento(rs.getString("Dipartimento"));
        temp.setUsernameUtente(rs.getString("Utente_Username"));
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  
  
  public Annuncio recuperaPerDipartimento(String dipartimento) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Annuncio temp = new Annuncio();
    
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
        temp.setTitolo(rs.getString("Titolo"));
        temp.setDescrizione(rs.getString("Descrizione"));
        temp.setTipologia(rs.getBoolean("Tipologia"));
        temp.setDipartimento(rs.getString("Dipartimento"));
        temp.setUsernameUtente(rs.getString("Utente_Username"));
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  public Annuncio recuperaPerUtente(String username) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Annuncio temp = new Annuncio();
    
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
        temp.setTitolo(rs.getString("Titolo"));
        temp.setDescrizione(rs.getString("Descrizione"));
        temp.setTipologia(rs.getBoolean("Tipologia"));
        temp.setDipartimento(rs.getString("Dipartimento"));
        temp.setUsernameUtente(rs.getString("Utente_Username"));
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
}
