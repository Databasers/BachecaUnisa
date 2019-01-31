
/**

 * 
 */

package gestionesegnalazioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestioneannunci.Annuncio;
import jdbc.DriverManagerConnectionPool;

/**
 * Manager della classe segnalazione.
 * 
 * @author kinglash
 *
 */
public class SegnalazioneManager {
  
  private static final String TABLENAME = "Annuncio";
  private static final int PAGINADIM = 10;
  
  /**
   * Questo metodo crea una nuova Segnalazione.
   * 
   * return <true> se la segnalazione era su un Annuncio, <false> se su una recensione.
   * @author kinglash
   */
  public boolean creaSegnalazione(Segnalazione segnalazione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = "INSERT INTO " + TABLENAME + " VALUES(null," + segnalazione.getDescrizione() + ","
        + segnalazione.getMotivazione() + "," + segnalazione.isTipoSegnalazione() + ")";
    
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
    return segnalazione.isTipoSegnalazione();
    
  }
  
  public void rimuoviSegnalazione(int id) throws SQLException {
    String sql = "DELETE FROM " + TABLENAME + " WHERE ID LIKE " + id;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
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
  
  public ArrayList<Segnalazione> recuperaSegnalazioni(int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Segnalazione> temp = null;
   
    String sql = "SELECT* FROM " + TABLENAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        temp = listaSegnalazioni(rs, numPagina);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
   
  public ArrayList<Segnalazione> listaSegnalazioni(ResultSet rs, int numPagina)
      throws SQLException {
    rs.first();
    ArrayList<Segnalazione> lista = new ArrayList<Segnalazione>();
    Segnalazione temp;
    //Sposta il cursore alla posizione corretta
    //Qualcuno si faccia qualche simulazione per vedere se si muove nelle posizioni giuste
    for (int i = 0; i < numPagina * PAGINADIM; i++) {
      rs.next();
    }
    //Prende i prossimi 10 Annunci e li aggiunge alla lista
    //Again, fare conti per vedere se va
    for (int i = 0; i < 10; i++) {
     
      temp = new Segnalazione(rs.getInt("ID"), rs.getString("Descrizione"),
          rs.getInt("Motivazione"), rs.getInt("Annuncio.Segnalato_A"),
          rs.getInt("Recensione.ID.Segnalato_R"));
      lista.add(temp);
      rs.next();
      
    } 
    
    return lista;
    
  }
  
}
