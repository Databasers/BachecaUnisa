package gestionerecensioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.DriverManagerConnectionPool;

/**
 * Il manager della classe Recensione si occupa della gestione delle recensioni: della loro 
 * creazione e rimozione.
 * 
 */
public class RecensioneManager {
  
  private static final String TableName = "Recensione";
  
  /**
   * Questo metodo crea una recensione nel database.
   * 
   * @param recensione da inserire
   */
  
  public void creaRecensione(Recensione recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TableName + "VALUES(" + recensione.getDescrizione()
        + "," + recensione.getMittente().getUsername() + "," + recensione.getId()
        + "," + recensione.getDestinatario().getUsername() + "," + recensione.getValutazione();
    
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
   * 
   * @param recensione da rimuovere.
   */
  public void rimuoviRecensione(Recensione recensione) throws SQLException {
    Connection connection = DriverManagerConnectionPool.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + TableName
        + "WHERE Id LIKE " + recensione.getId());
    preparedStatement.executeQuery();
  }
}
