package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class DriverManagerConnectionPool {

  private static List<Connection> freeDbConnections;
  
  static {
    freeDbConnections = new LinkedList<Connection>();
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found: " + e.getMessage());
    }
  }
  
  private static synchronized Connection createDbConnection() throws SQLException {
    Connection newConnection = null;
    String ip = "localhost";
    String port = "3306";
    String db = "mydb";
    String username = "root";
    String password = "";
    
    newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db 
        + "?zeroDateTimeBehavior=convertToNull&useSSL=false", username, password);
    
    System.out.println("Create new DB connection");
    newConnection.setAutoCommit(false);
    return newConnection;
  }
  
  /**
   * Il metodo restituisce una connessione dal suo pool di connessioni libere.
   * @return connessione libera
   * @throws java.sql.SQLException se la connessione fallisce
   */
  
  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;
    if (!freeDbConnections.isEmpty()) {
      connection = (Connection) freeDbConnections.get(0);
      freeDbConnections.remove(0);
      
      try {
        if (connection.isClosed()) {
          connection = getConnection();
        }
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDbConnection();
    }
    
    return connection;
  }
  
  /**
   * Il metodo rilascia la connessione inviata e la riposiziona
   * nell'array di connessioni libere.
   * @param connection connessione da liberare
   * @throws java.sql.SQLException se il rilascio di connessione fallisce
   */
  
  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if (connection != null) { 
      freeDbConnections.add(connection);
    }
  }
  
}
