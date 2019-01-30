package gestionerecensioni;

import gestioneutenti.Utente;
import gestioneutenti.UtenteManager;
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

  UtenteManager utenteManager = new UtenteManager();
  
  /**
   * Questo metodo crea una recensione nel database.
   * 
   * @param recensione da inserire
   * @return 
   */
  
  public void creaRecensione(Recensione recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TABLENAME + "VALUES(" + recensione.getDescrizione()
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
    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + TABLENAME
        + "WHERE Id LIKE " + recensione.getId());
    preparedStatement.executeQuery();
  }
  
  public ArrayList<Recensione> listaRecensioni(ResultSet rs, int numPagina) throws SQLException {
    rs.first();
    ArrayList<Recensione> lista = new ArrayList<Recensione>();
    Recensione temp;
    //Sposta il cursore alla posizione corretta
    //Qualcuno si faccia qualche simulazione per vedere se si muove nelle posizioni giuste
    for (int i = 0; i < numPagina * PAGINADIM; i++) {
      rs.next();
    }
    //Prende i prossimi 10 Annunci e li aggiunge alla lista
    //Again, fare conti per vedere se va
    for (int i = 0; i < 10; i++) {
     
      temp = new Recensione();
      temp.setDescrizione(rs.getString("Descrizione"));
      temp.setDestinatario(utenteManager.recuperaPerUsername(rs.getString("Destinatario_Utente")));
      temp.setId(rs.getInt("ID"));
      temp.setMittente(utenteManager.recuperaPerUsername(rs.getString("Mittente_Utente")));
      temp.setValutazione(rs.getInt("Valutazione"));
      lista.add(temp);
      rs.next();
      
    }
    return lista; 
  }
  
  public ArrayList<Recensione> stampaRecensione(Utente utente, int numPagina) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Recensione> temp = null;
    
    String sql = "SELECT * FROM " + TABLENAME + " AS a WHERE a.Mittente LIKE " 
        + utente.getUsername();
    
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
  
  public Recensione recensionePerId(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Recensione temp = null;
    
    String sql = "SELECT * FROM " + TABLENAME + " AS a WHERE a.ID LIKE " 
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
               rs.getString("Descrizione"), 
               utenteManager.recuperaPerUsername(rs.getString("Mittente")),
               utenteManager.recuperaPerUsername(rs.getString("Destinatario")));
      }
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    
    return temp;
  }
  
  public void modificaRecensione(Recensione recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = "UPDATE " + TABLENAME + "SET Descrizione= ?"
        + " WHERE ID = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(0, recensione.getDescrizione());
      preparedStatement.setInt(1, recensione.getId());
     
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
