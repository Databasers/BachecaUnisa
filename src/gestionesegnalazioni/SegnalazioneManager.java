/**

 * 
 */

package gestionesegnalazioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.DriverManagerConnectionPool;

/**
 * Manager della classe segnalazione.
 * 
 * @author kinglash
 *
 */
public class SegnalazioneManager {
  
  private static final String TableName = "Annuncio";
  
  /**
   * Questo metodo crea una nuova Segnalazione.
   * 
   * @author kinglash
   */
  public void creaSegnalazione(Segnalazione segnalazione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = "INSERT INTO " + TableName + " VALUES(null," + segnalazione.getDescrizione() + ","
        + segnalazione.getMotivazione() + "," + segnalazione.isTipoSegnalazione() + "," 
        + segnalazione.getRecensione() + "," + segnalazione.getAnnuncio() + ")";
    
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
   
  
}
