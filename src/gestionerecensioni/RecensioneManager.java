package gestionerecensioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.DriverManagerConnectionPool;

/**
 * Il manager della classe Recensione si occupa della gestione delle recensioni: della loro 
 * creazione e rimozione.
 * 
 */
public class RecensioneManager {
  
  private static final String TABLENAME = "Recensione";  
  private static final int PAGINADIM = 10;

  

  /**
   * Questo metodo crea una recensione nel database.
   * @param recensione da inserire nel database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void creaRecensione(Recensione recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TABLENAME + "VALUES(" + recensione.getDescrizione()
        + "," + recensione.getMittente() + "," + recensione.getId()
        + "," + recensione.getDestinatario() + "," + recensione.getValutazione();
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      
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
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return una lista di 10 recensioni dal database basandosi dalla pagina specificata.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Recensione> listaRecensioni(ResultSet rs, int numPagina) 
      throws SQLException {
    rs.first();
    ArrayList<Recensione> lista = new ArrayList<Recensione>();
    Recensione temp;
    //Sposta il cursore alla posizione corretta
    for (int i = 0; i < numPagina * PAGINADIM; i++) {
      rs.next();
    }

    for (int i = 0; i < 10; i++) {
     
      temp = new Recensione();
      temp.setDescrizione(rs.getString("Descrizione"));
      temp.setDestinatario(rs.getString("Destinatario_Utente"));
      temp.setId(rs.getInt("ID"));
      temp.setMittente(rs.getString("Mittente_Utente"));
      temp.setValutazione(rs.getInt("Valutazione"));
      lista.add(temp);
      rs.next();
      
    }
    return lista; 
  }
  
  /**
   * Recupera tuttle recensioni esistenti di un dato utente.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @param utenteDestinatario username di riferimento.
   * @return la lista di tutti gli annunci basandosi sulla pagina visualizzata dall'utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Recensione> recuperaRecensioni(String utenteDestinatario, int numPagina) 
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Recensione> temp = null;
    
    String sql = "SELECT * FROM " + TABLENAME + " AS a WHERE r.Mittente LIKE " 
        + utenteDestinatario;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        temp = listaRecensioni(rs, numPagina);
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
    
    String sql = "SELECT * FROM " + TABLENAME + " AS a WHERE r.ID LIKE " 
        + id;
    
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
    String sql = "UPDATE " + TABLENAME + "SET Descrizione = ?, Valutazione = ?"
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
  
}
