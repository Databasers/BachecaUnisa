package gestionesegnalazioni;

import java.util.List;

/**
 * L'interfaccia <code>Segnalabile</code> viene implementata 
 * dalle classi che possono essere segnalate.
 * L'interfaccia si occupa della gestione, della creazione 
 * e della rimozione delle segnalazioni.
 *
 */
public interface Segnalabile {

    
  public void addSegnalazione(Segnalazione a);
    
  public void deleteSegnalazione(int id);
  
  public Segnalazione leggiSegnalazione(int indice);
 
  
  List<Segnalazione> lista = null;
 
}
