package gestioneutenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DriverManagerConnectionPool;


public class UtenteManager {
  
  private static final String TableName = "Utente";
  private static final int PAGINADIM = 10;
  
  public ArrayList<Utente> listaUtenti(ResultSet rs, int numPagina) throws SQLException {
    rs.first();
    ArrayList<Utente> lista = new ArrayList<Utente>();
    Utente temp;
    //Sposta il cursore alla posizione corretta
    //Qualcuno si faccia qualche simulazione per vedere se si muove nelle posizioni giuste
    for (int i = 0; i < numPagina * PAGINADIM; i++) {
      rs.next();
    }
    //Prende i prossimi 10 Annunci e li aggiunge alla lista
    //Again, fare conti per vedere se va
    for (int i = 0; i < 10; i++) {
     
      temp = new Utente();
      temp.setUsername(rs.getString("Username"));
      temp.setNome(rs.getString("Nome"));
      temp.setCognome(rs.getString("Cognome"));
      temp.setSesso(rs.getString("Sesso"));
      temp.setPassword(rs.getString("Password"));
      temp.setDescrizione(rs.getString("Descrizione"));
      temp.setNumAnnunci(rs.getInt("Numero annunci"));
      temp.setGestore(rs.getBoolean("Gestore"));
      lista.add(temp);
      rs.next();
      
    } 
    
    return lista;
    
  }

  
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
      temp.setGestore(rs.getBoolean("Gestore"));


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
    

  public ArrayList<Utente> recuperaTutti(int numPagina) throws SQLException {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Utente> temp = null;
    
    String sql = "SELECT * FROM " + TableName;
          
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      
      System.out.println("recuperaTutti: " + preparedStatement.toString());
      
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        temp = listaUtenti(rs, numPagina);
      }
        
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    
    return temp;
  }


  
  public void salvaUtente(Utente u) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TableName + " VALUES(?,?,?,?,?,?,?,?)";
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
      preparedStatement.setBoolean(8, u.isGestore());
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
        + " WHERE Username = ?";

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