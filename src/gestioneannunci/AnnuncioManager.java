
package gestioneannunci;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.DriverManagerConnectionPool;

/**
 * Il manager della classe Annuncio si occupa della gestione degli annunci: 
 * della loro creazione, rimozione, modifica e della ricerca.
 */

public class AnnuncioManager {

  private static final String TABLENAME = "Annuncio";
  
  /**
   * Il metodo crea un'ArrayList di annunci da un result set.
   * @param rs result set da listare.
   * @return una lista di annunci dal database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> listaAnnunci(ResultSet rs) throws SQLException {
    rs.beforeFirst();
    ArrayList<Annuncio> lista = new ArrayList<Annuncio>();
    Annuncio temp;
    int a = 0;
    int b = 0;
    while (rs.next()) {
      b++;
    }
    System.out.println("B = " + b);
    rs.first();
    while (!rs.isAfterLast()) {
      a++;
      temp = new Annuncio();
      temp.setDipartimento(rs.getString(1));
      temp.setTitolo(rs.getString(2));
      temp.setDescrizione(rs.getString(3));
      temp.setTipologia(rs.getBoolean(4));
      temp.setNumSegnalazioni(rs.getInt(5));
      temp.setId(rs.getInt(6));
      temp.setUsernameUtente(rs.getString(7));
      System.out.print(temp.getTitolo() + ", A: " + a);
      lista.add(temp);
      rs.next();    
    }
    return lista;  
  }
  
  

  /**
   * Questo metodo crea un nuovo annuncio nel database.
   * 
   * @param annuncio da inserire nel database.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void creaAnnuncio(Annuncio annuncio) throws SQLException {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    String sql = "INSERT INTO " + TABLENAME + " VALUES(?,?,?,?,0, null, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();

      preparedStatement = connection.prepareStatement(sql);
      String dip = annuncio.getDipartimento();
      if (dip == null) {
        dip = "Informatica";
      }
      preparedStatement.setString(1, dip);
      preparedStatement.setString(2, annuncio.getTitolo());
      preparedStatement.setString(3, annuncio.getDescrizione());
      int tip;
      if (annuncio.isTipologia()) {
        tip = 1;
      } else {
        tip = 0;
      }
      preparedStatement.setInt(4, tip);
      preparedStatement.setString(5, annuncio.getUsernameUtente());
      System.out.println(preparedStatement.toString());
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
   * Questo metodo rimuove l'annuncio selezionato dal database.
   * 
   * @param annuncio da rimuovere.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void rimuoviAnnuncio(Annuncio annuncio) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;


    String delete = "DELETE FROM " + TABLENAME + " WHERE Id = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(delete);
      preparedStatement.setInt(1, annuncio.getId());
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
   * Questo metodo modifica l'annuncio selezionato nel database.
   * 
   * @param annuncio da modificare.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public void modificaAnnuncio(Annuncio annuncio) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    //Dipartimento, Titolo, Descrizione, Tipologia, NumeroSegnalazioni, ID, Utente_Username
    String sql = "UPDATE " + TABLENAME + " SET Dipartimento = ?, Titolo = ?, Descrizione = ?"
        + ", Tipologia = ?, NumSegnalazioni = ?, ID = ?, Utente_Username = ?"
        + " WHERE ID = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, annuncio.getDipartimento());
      preparedStatement.setString(2, annuncio.getTitolo());
      preparedStatement.setString(3, annuncio.getDescrizione());
      int a;
      if (annuncio.isTipologia()) {
        a = 1;
      } else {
        a = 0;
      }
      preparedStatement.setInt(4, a);
      preparedStatement.setInt(5, annuncio.getNumSegnalazioni());
      preparedStatement.setInt(6, annuncio.getId());
      preparedStatement.setString(7, annuncio.getUsernameUtente());
      preparedStatement.setInt(8, annuncio.getId());
     
      System.out.println("Modifica: " + preparedStatement.toString());
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
   * Recupera tutti gli annunci esistenti.
   * @return la lista di tutti gli annunci.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> recuperaAnnunci() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp = null;
   
    String sql = "SELECT * FROM " + TABLENAME;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      System.out.println("Query: " + preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.first()) {
        temp = listaAnnunci(rs);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  /**
   * Recupera l'annuncio con l'id selezionato.
   * @param id dell'annuncio cercato.
   * @return l'annuncio con l'id selezionato.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public Annuncio recuperaPerId(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Annuncio temp = new Annuncio();
    String str = Integer.toString(id);

    String sql = "SELECT * FROM " + TABLENAME + " WHERE id = ?  ";
    String flush = "FLUSH TABLES " + TABLENAME; 
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(flush);
      preparedStatement.executeUpdate();
      connection.commit();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, str);
      System.out.println("Query: " + preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();
      if (!rs.next()) {
        temp = null; 
      } else {
        temp.setId(rs.getInt("ID"));
        temp.setTitolo(rs.getString("titolo"));
        temp.setDescrizione(rs.getString("descrizione"));
        temp.setDipartimento(rs.getString("dipartimento"));
        temp.setTipologia(rs.getBoolean("tipologia"));
        temp.setUsernameUtente(rs.getString("Utente_Username"));
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }

  /**
   * Recupera gli annunci della tipologia specificata.
   * @param tipo filtro.
   * @return la lista di tutti gli annunci, filtrati per tipologia.
   * @throws SQLException @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> recuperaPerTipologia(String descrizione, boolean tipo,
      String dipartimento) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    int t;
    if (tipo) {
      t = 1;
    } else {
      t = 0;
    }

    String sql = "SELECT * FROM " + TABLENAME + " WHERE (Descrizione LIKE ? OR Titolo LIKE ?)"
        + " AND Tipologia = ? AND Dipartimento = ? ";
    String flush = "FLUSH TABLES " + TABLENAME; 
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(flush);
      preparedStatement.executeUpdate();
      connection.commit();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, "%" + descrizione + "%");
      preparedStatement.setString(2, "%" + descrizione + "%");
      preparedStatement.setString(3, Integer.toString(t));
      preparedStatement.setString(4, dipartimento);
      System.out.println("Query: " + preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();
      if (!rs.next()) {
        temp = new ArrayList<Annuncio>(); 
      } else {
        temp = listaAnnunci(rs);
      } 
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  
  /**
   * Recupera gli annunci del dipartimento specificato.
   * @param dipartimento filtro.
   * @return la lista di tutti gli annunci, filtrati per dipartimento.
   * @throws SQLException @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> recuperaPerDipartimento(String dipartimento) 
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    
    String sql = "SELECT * FROM " + TABLENAME + " WHERE Dipartimento = ?  ";
    String flush = "FLUSH TABLES " + TABLENAME; 
    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(flush);
      preparedStatement.executeUpdate();
      connection.commit();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, dipartimento);
      System.out.println("Query: " + preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();
      temp = listaAnnunci(rs);     
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
  
  /**
   * Recupera tutti gli annunci di un singolo utente.
   * @param username dell'utente
   * @return la lista di tutti gli annunci creati da un utente.
   * @throws SQLException in caso di errore di accesso al database.
   */
  public ArrayList<Annuncio> recuperaPerUtente(String username) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Annuncio> temp;
    
    String sql = "SELECT * FROM " + TABLENAME + " WHERE Utente_Username = ?  ";
    String flush = "FLUSH TABLES " + TABLENAME; 

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(flush);
      preparedStatement.executeUpdate();
      connection.commit();    
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, username);
      System.out.println("Query: " + preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();  
      if (!rs.next()) {
        temp = null;
      } else {
        temp = listaAnnunci(rs);
      } 
    } finally {      
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    return temp;
  }
}
