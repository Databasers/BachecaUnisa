package gestioneutenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DriverManagerConnectionPool;


public class UtenteManager {
  
  private static final String TableName = "Utente";

  
  public Utente recuperaSeRegistrato(String username,String password) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Utente temp = new Utente();

    String sql = "SELECT* FROM " + TableName + " WHERE Username=? AND Password= ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, username);
      preparedStatement.setString(2, password);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();

      rs.next();
      temp.setUsername(rs.getString("Username"));
      temp.setNome(rs.getString("Nome"));
      temp.setCognome(rs.getString("Cognome"));
      temp.setSesso(rs.getString("Sesso"));
      temp.setPassword(rs.getString("Password"));
      temp.setDescrizione(rs.getString("Descrizione"));
      temp.setNumAnnunci(rs.getInt("Numero annunci"));


    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return temp;
  }
  

  public Utente recuperaPerUsername(String username) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Utente temp = new Utente();
    
    String sql = "SELECT* FROM " + TableName + " WHERE Username=?  ";
    
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
        temp.setUsername(rs.getString("Username"));
        temp.setNome(rs.getString("Nome"));
        temp.setCognome(rs.getString("Cognome"));
        temp.setSesso(rs.getString("Sesso"));
        temp.setPassword(rs.getString("Password"));
        temp.setDescrizione(rs.getString("Descrizione"));
        temp.setNumAnnunci(rs.getInt("Numero annunci"));
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
    

  public ArrayList<Utente> recuperaTutti(String order) throws SQLException {
    
    ArrayList<Utente> u = new ArrayList<Utente>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "SELECT * FROM " + TableName + "";
    if (order != null && !order.equals("")) {
      sql += " ORDER BY " + order;
    }
      
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      
      System.out.println("doRetrieveAll: " + preparedStatement.toString());
      
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Utente temp = new Utente();
        temp.setUsername(rs.getString("Username"));
        temp.setNome(rs.getString("Nome"));
        temp.setCognome(rs.getString("Cognome"));
        temp.setSesso(rs.getString("Sesso"));
        temp.setPassword(rs.getString("Password"));
        temp.setDescrizione(rs.getString("Descrizione"));
        temp.setNumAnnunci(rs.getInt("Numero annunci"));
        u.add(temp);
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return u;
  }


  
  public void salvaUtente(Utente u) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TableName + " VALUES(?,?,?,?,?,?,?)";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setString(1, u.getNome());
      preparedStatement.setString(2, u.getCognome());
      preparedStatement.setString(3, u.getSesso());
      preparedStatement.setString(4, u.getUsername());
      preparedStatement.setString(5, u.getPassword());
      preparedStatement.setInt(6, u.getNumAnnunci());
      preparedStatement.setString(7, u.getDescrizione());
      System.out.println("doSave: " + preparedStatement.toString());
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

  
  public void modificaUtente(Utente u) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String updateQuery = "UPDATE " + TableName
        + " SET Nome = ?, Cognome = ?, Password = ?, Descrizione = ? "
        + " WHERE CodiceFiscale = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateQuery);
      preparedStatement.setString(1, u.getNome());
      preparedStatement.setString(2, u.getCognome());
      preparedStatement.setString(3, u.getPassword());
      preparedStatement.setString(4, u.getDescrizione());
      preparedStatement.setString(5, u.getUsername());
      
      System.out.println("doUpdate: " + preparedStatement.toString());
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

  
  public boolean eliminaUtente(String username) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String delete = "DELETE FROM " + TableName + " WHERE Username = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(delete);
      preparedStatement.setString(1, username);

      System.out.println("doDelete: " + preparedStatement.toString());
      result = preparedStatement.executeUpdate();
      
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
    return (result != 0);
  }

}