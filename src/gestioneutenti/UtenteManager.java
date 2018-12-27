/**
 * 
 */

package gestioneutenti;

/**
 * 
 *
 */
public class UtenteManager {

  /**
   * Questo metodo registra un nuovo utente inserendolo nel database
   * 
   * @param username dell'utente che si sta registrando
   * @param password dell'utente che si sta registrando
   * @param nome dell'utente che si sta registrando
   * @param cognome dell'utente che si sta registrando
   */
  public void registrazione(String username, String password, String nome, String cognome) {}
  
  /**
   * Questo metodo avvia la procedura di login di un ospite
   * 
   */
  public void login() {}
  
  /**
   * Questo metodo elimina un utente dal database 
   * @param utente che sarà eliminato dal database
   */
  public void ban(Utente utente) {}
  
  /**
   * Questo metodo cerca un utente nel database 
   * @param utente che sarà cercato nel database
   */
  public void cercaUtente(Utente utente) {}
   
}
