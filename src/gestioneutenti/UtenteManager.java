package gestioneutenti;

import gestionerecensioni.RecensioneManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DriverManagerConnectionPool;

/**
 * Il manager della classe Utente si occupa della gestione degli utenti: della loro 
 * creazione, rimozione, modifica e della ricerca.
 * 
 */
public class UtenteManager {
  
  private static final String TABLENAME = "Utente";

  /**
   * Il metodo crea un'ArrayList di utenti da un result set.
   * @param rs result set da listare.
   * @return una lista di utenti dal database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Utente> listaUtenti(ResultSet rs) 
      throws SQLException {
    RecensioneManager rm = new RecensioneManager();
    rs.first();
    ArrayList<Utente> lista = new ArrayList<Utente>();
    Utente temp;
    while (!rs.isAfterLast()) {
      temp = new Utente();
      temp.setUsername(rs.getString("Username"));
      temp.setNome(rs.getString("Nome"));
      temp.setCognome(rs.getString("Cognome"));
      temp.setSesso(rs.getString("Sesso"));
      temp.setPassword(rs.getString("Password"));
      temp.setDescrizione(rs.getString("Descrizione"));
      temp.setNumAnnunci(rs.getInt("NumAnnunci"));
      temp.setGestore(rs.getBoolean("Gestore"));
      temp.setMedia(rm.media(temp.getUsername()));
      System.out.println(temp.getMedia());
      lista.add(temp);
      rs.next();    
    }    
    return lista;   
  }

  /**
   * Questo metodo recupera l'utente qualora sia registrato.
   * @param username dell'utente da recuperare.
   * @param password dell'utente da recuperare.
   * @return utente registrato.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public Utente recuperaSeRegistrato(String username, String password) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Utente temp = new Utente();

    String sql = "SELECT * FROM " + TABLENAME + " WHERE Username= ? AND Password= ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, username);
      preparedStatement.setString(2, password);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        temp.setUsername(rs.getString("Username"));
        temp.setNome(rs.getString("Nome"));
        temp.setCognome(rs.getString("Cognome"));
        temp.setSesso(rs.getString("Sesso"));
        temp.setPassword(rs.getString("Password"));
        temp.setDescrizione(rs.getString("Descrizione"));
        temp.setNumAnnunci(rs.getInt("NumAnnunci"));
        temp.setGestore(rs.getBoolean("Gestore"));
        System.out.println(temp.getUsername());
      } else {
        System.out.println("Non esiste");
        temp = null;
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
    return temp;
  }
  
  /**
   * Questo metodo recupera l'utente che ha per username il parametro passato.
   * @param username dell'utente che si cerca.
   * @return utente con l'username passato come parametro.
   * @throws SQLException in caso di errore di accesso al database.
   */
  @SuppressWarnings("resource")
  public Utente recuperaPerUsername(String username) throws SQLException {
    System.out.println("Nel metodo");
    RecensioneManager rm = new RecensioneManager();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Utente temp = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM " + TABLENAME + " WHERE Username = ?  ";
    String flush = "FLUSH TABLES " + TABLENAME; 
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(flush);
      preparedStatement.executeUpdate();
      connection.commit();
      preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setString(1, username);
      System.out.println("Query: " + preparedStatement.toString());
      
      rs = preparedStatement.executeQuery();
      if (rs != null && !rs.first()) {
        System.out.println("Non trovato");
      } else {
        temp = new Utente();
        System.out.println("Trovato qualcosa");
        temp.setUsername(rs.getString("Username"));
        temp.setNome(rs.getString("Nome"));
        temp.setCognome(rs.getString("Cognome"));
        temp.setSesso(rs.getString("Sesso"));
        temp.setPassword(rs.getString("Password"));
        temp.setDescrizione(rs.getString("Descrizione"));
        temp.setNumAnnunci(rs.getInt("NumAnnunci"));
        temp.setMedia(rm.media(temp.getUsername()));
      } 
    } finally {
      rs.close();
      preparedStatement.close();
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  

    
  /**
   * Recupera tutti gli utenti esistenti.
   * @return la lista di tutti gli utenti.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Utente> recuperaUtenti() throws SQLException {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Utente> temp;
    
    String sql = "SELECT * FROM " + TABLENAME;
    String flush = "FLUSH TABLES " + TABLENAME; 
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(flush);
      preparedStatement.executeUpdate();
      connection.commit();
      preparedStatement = connection.prepareStatement(sql);      
      System.out.println("recuperaTutti: " + preparedStatement.toString());    
      ResultSet rs = preparedStatement.executeQuery();
      temp = listaUtenti(rs);
      if (temp == null) {
        temp = new ArrayList<Utente>();
      }
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }   
    return temp;
  }


  /**
   * Questo metodo crea un nuovo utente nel database.
   * 
   * @param u utente da inserire nel database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void creaUtente(Utente u) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TABLENAME + " VALUES(?,?,?,?,?,?,?,?,?)";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setString(1, u.getNome());
      preparedStatement.setString(2, u.getCognome());
      preparedStatement.setString(3, u.getSesso());
      preparedStatement.setString(4, u.getUsername());
      preparedStatement.setString(5, u.getPassword());
      preparedStatement.setBoolean(6, u.isGestore());
      preparedStatement.setInt(7, 0);
      preparedStatement.setString(8, "Descrizione di default");
      preparedStatement.setInt(9, 0);
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

  /**
   * Questo metodo modifica l'utente selezionato nel database.
   * 
   * @param u utente da modificare.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void modificaUtente(Utente u) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String updateQuery = "UPDATE " + TABLENAME
        + " SET Nome = ?, Cognome = ?, Password = ?, Descrizione = ? , NumAnnunci = ? "
        + " WHERE Username = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateQuery);
      preparedStatement.setString(1, u.getNome());
      preparedStatement.setString(2, u.getCognome());
      preparedStatement.setString(3, u.getPassword());
      preparedStatement.setString(4, u.getDescrizione());
      preparedStatement.setInt(5, u.getNumAnnunci());
      preparedStatement.setString(6, u.getUsername());
      
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

  /**
   * Questo metodo rimuove l'utente selezionato dal database.
   * 
   * @param utente da rimuovere.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void rimuoviUtente(Utente utente) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;


    String delete = "DELETE FROM " + TABLENAME + " WHERE Username = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(delete);
      preparedStatement.setString(1, utente.getUsername());

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
  


}