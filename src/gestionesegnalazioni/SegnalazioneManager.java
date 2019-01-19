/**

 * 
 */

package gestionesegnalazioni;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
  public void creaSegnalazione(Segnalazione segnalazione) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = "INSERT INTO " + TableName + " VALUES()";
    
  } //necessita una precondizione per numSegnalazioni>50
   
  
}
