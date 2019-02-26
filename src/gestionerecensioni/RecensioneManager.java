package gestionerecensioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import jdbc.DriverManagerConnectionPool;

/**
 * Il manager della classe Recensione si occupa della gestione delle recensioni: della loro 
 * creazione e rimozione.
 * 
 */
public class RecensioneManager {
  
  private static final String TABLENAME = "Recensione";

  

  /**
   * Questo metodo crea una recensione nel database.
   * @param recensione da inserire nel database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void creaRecensione(Recensione recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      String sql = "INSERT INTO " + TABLENAME + " VALUES(?,?,null,?,?)";
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, recensione.getDescrizione());
      preparedStatement.setString(2, recensione.getMittente());
      preparedStatement.setString(3, recensione.getDestinatario());
      preparedStatement.setInt(4, recensione.getValutazione());
    
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
   * Questo metodo rimuove la recensione selezionata.
   * @param recensione da rimuovere dal database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void rimuoviRecensione(Recensione recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;


    String delete = "DELETE FROM " + TABLENAME + " WHERE Id = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(delete);
      preparedStatement.setInt(1, recensione.getId());
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
   * Il metodo crea un'ArrayList di recensioni da un result set.
   * @param rs result set da listare.
   * @return una lista di 10 recensioni dal database basandosi dalla pagina specificata.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Recensione> listaRecensioni(ResultSet rs) 
      throws SQLException {
    rs.first();
    ArrayList<Recensione> lista = new ArrayList<Recensione>();
    Recensione temp;

    if (rs.next()) {
      System.out.print("1");
      temp = new Recensione();
      temp.setDescrizione(rs.getString("Descrizione"));
      temp.setDestinatario(rs.getString("Destinatario"));
      temp.setId(rs.getInt("ID"));
      temp.setMittente(rs.getString("Mittente"));
      temp.setValutazione(rs.getInt("Valutazione"));
      lista.add(temp);
      rs.next();
      
    }
    return lista; 
  }
  
  /**
   * Recupera tuttle recensioni esistenti di un dato utente.
   * @param utenteDestinatario username di riferimento.
   * @return la lista di tutti gli annunci basandosi sulla pagina visualizzata dall'utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Recensione> recuperaRecensioni(String utenteDestinatario) 
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Recensione> temp = null;
    
    String sql = "SELECT * FROM " + TABLENAME + " WHERE Destinatario LIKE '" 
        + utenteDestinatario + "'";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        temp = listaRecensioni(rs);
      } else {
        temp = new ArrayList<Recensione>();
      }
      
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    
    return temp;
  }
  
  /**
   * Recupera la recensione con l'id selezionato.
   * @param id della recensione cercata.
   * @return la recensione con l'id selezionato.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public Recensione recuperaPerId(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Recensione temp = null;
    
    String sql = "SELECT * FROM " + TABLENAME + "  WHERE ID LIKE '" 
        + id + "'";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      if (!rs.next()) {
        temp = null;
      } else {
        temp = new Recensione(rs.getInt("ID"), rs.getInt("Valutazione"), 
               rs.getString("Descrizione"), rs.getString("Mittente"),
               rs.getString("Destinatario"));
      }
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    
    return temp;
  }
  
  /**
   * Questo metodo modifica la recensione selezionata nel database.
   * 
   * @param recensione da modificare.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void modificaRecensione(Recensione recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = "UPDATE " + TABLENAME + " SET Descrizione = ?, Valutazione = ?"
        + " WHERE ID = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(0, recensione.getDescrizione());
      preparedStatement.setInt(1, recensione.getValutazione());
      preparedStatement.setInt(2, recensione.getId());
     
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
   * Questo metodo restituisce la media delle recensioni di un utente.
   * Nel caso in cui l'utente non ha ricevuto recensioni il metodo 
   * restituisce media pari a 0.
   * @param username dell'utente da prendere in considerazione
   * @return media recensioni
   */
  public int media(String username) throws SQLException {
    System.out.println("Entrati nella media, salve!");
    ArrayList<Recensione> temp = recuperaRecensioni(username);
    
    
    if (temp != null && temp.size() != 0) {
      System.out.println("OH, siamo entrati dentro la media per davvero!");
      Iterator<Recensione> lista =  temp.iterator();
      int a = 0;
      while (lista.hasNext()) {
        Recensione x = lista.next();
        a += x.getValutazione();
      }
      return a / temp.size();
    } else {
      System.out.println("Non ci stavano le recensioni");
      return 0;
    }
  }
  
}
