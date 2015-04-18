/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package car.ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author echallier
 */
@Local
public interface BooksFacadeLocal {
    /**
     * Enregistre des livres dans la base de donnée
     */
    public void init();
    /**
     * Retourne la liste de tous les auteurs
     * @return 
     */
    public List<String> getAuthors();
}
