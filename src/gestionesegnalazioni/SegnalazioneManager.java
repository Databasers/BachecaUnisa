package gestionesegnalazioni;

import gestioneannunci.AnnuncioManager;
import gestionerecensioni.RecensioneManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.DriverManagerConnectionPool;

/**
 * Il manager della classe Segnalazione si occupa della gestione delle segnalazioni: 
 * Della loro creazione, rimozione e della ricerca.
 */

public class SegnalazioneManager {
  
  private static final String TABLENAME = "Annuncio";
  private static final int PAGINADIM = 10;
  private static final int MAX_SEGNALAZIONI = 50;
 
  /**
   * Questo metodo crea una nuova segnalazione.
   * @param segnalazione da inserire nel database.
   * @return <code>true</code> se la segnalazione era su un Annuncio
   *         <code>false</code> se su una Recensione.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public boolean creaSegnalazione(Segnalazione segnalazione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql;
    String test;
    if (segnalazione.isTipoSegnalazione()) {
      sql = "INSERT INTO " + TABLENAME + " VALUES(null," + segnalazione.getDescrizione()
          + "," + segnalazione.getUtente() + "," + segnalazione.getMotivazione() + ","
          + segnalazione.getIdSegnalato() + ", null)";
      test = "SELECT COUNT(ID) AS segnalazioni FROM " + TABLENAME
          + "WHERE Annuncio.Segnalato_A LIKE " + segnalazione.getIdSegnalato();
      
    } else {
      sql = "INSERT INTO " + TABLENAME + " VALUES(null," + segnalazione.getDescrizione() 
          + "," + segnalazione.getUtente() + "," + segnalazione.getMotivazione()
          + ",null," + segnalazione.getIdSegnalato() + ")";
      test = "SELECT COUNT(ID) AS segnalazioni FROM " + TABLENAME
          + "WHERE Recensione.ID.Segnalato_R LIKE " + segnalazione.getIdSegnalato();
    }
    
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
    int numero = 0;
    try {
      preparedStatement = connection.prepareStatement(test);
      ResultSet rs = preparedStatement.executeQuery();
      numero = rs.getInt("segnalazioni");
      
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    if (numero > MAX_SEGNALAZIONI) {
      if (segnalazione.isTipoSegnalazione()) {
        AnnuncioManager io = new AnnuncioManager();
        io.rimuoviAnnuncio(io.recuperaPerId(segnalazione.getIdSegnalato()));
      } else {
        RecensioneManager io = new RecensioneManager();
        io.rimuoviRecensione(io.recuperaPerId(segnalazione.getIdSegnalato()));
      }
      return false;
    } else {
      return true;
    }
  }
  
  /**
   * Questo metodo rimuove la segnalazione selezionata dal database.
   * 
   * @param temp segnalazione da rimuovere dal database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void rimuoviSegnalazione(Segnalazione temp) throws SQLException {
    String sql = "DELETE FROM " + TABLENAME + " WHERE ID LIKE " + temp.getId();
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
  
  /**
   * Recupera tutte le segnalazioni esistenti.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return la lista di tutte le segnalazion basandosi sulla pagina visualizzata dall'utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
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
  
  
  /**
   * Il metodo crea un'ArrayList di segnalazioni da un result set.
   * @param rs result set da listare.
   * @param numPagina il numero della pagina che l'utente visualizza.
   * @return una lista di 10 segnalazioni dal database basandosi dalla pagina specificata.
   * @throws SQLException in caso di errore di accesso al database.
   */
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
          rs.getInt("Recensione.ID.Segnalato_R"), rs.getString("Utente"));
      lista.add(temp);
      rs.next();      
    } 
    return lista;
  }
  
  /**
   * Recupera la segnalazione con l'id selezionato.
   * @param id della segnalazione cercata.
   * @return la segnalazione con l'id selezionato.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public Segnalazione recuperaPerId(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Segnalazione temp = new Segnalazione();
    String str = Integer.toString(id);

    String sql = "SELECT* FROM " + TABLENAME + " WHERE id = ?  ";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, str);
      System.out.println("Query: " + preparedStatement.toString());

      ResultSet rs = preparedStatement.executeQuery();
      if (!rs.next()) {
        temp = null; 
      } else {
        temp.setMotivazione(rs.getInt("motivazione"));
        temp.setDescrizione(rs.getString("descrizione"));
        temp.setAnnuncio(rs.getInt("annuncio"));
        temp.setRecensione(rs.getInt("recensione"));
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
}
