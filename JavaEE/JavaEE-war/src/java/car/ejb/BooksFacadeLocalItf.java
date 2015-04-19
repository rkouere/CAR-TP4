/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package car.ejb;

import car.dadatabse.Books;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author echallier
 */
@Local
public interface BooksFacadeLocalItf {
    /**
     * Enregistre des livres dans la base de donnée
     */
    public void init();
    /**
     * Retourne la liste de tous les auteurs
     * @return une List
     */
    public List<String> getAuthors();
    /**
     * Retourne la liste de tous les titres
     * @return une List
     */
    public List<String> getTitles();
    /**
     * Rajoute un livre a la base
     * 
     */
    public boolean addTitle(String author, String title, String date);
    
    /**
     * Return de titles containing the string in its title
     * @param title
     * @return 
     */
    public List<Books> findAllTitles();
    
    /**
     * Return de titles with a certain author
     * @param title
     * @return 
     */
    public List<Books> findBooksByAuthor(String title);
    

    
}
