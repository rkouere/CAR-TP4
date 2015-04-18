/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package car.ejb;
import car.dadatabse.Books;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author echallier
 */
@Stateless
public class BooksFacadeLocal implements BooksFacadeLocalItf {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;

    public BooksFacadeLocal() {
    }
    
    /**
    * @inheritDoc
    *
    */
    @Override
    public void init() {
        Books book1 = new Books("Orwell", "1984", "08/06/1949");
        Books book2 = new Books("Kipling", "Kim", "01/10/1901");
        Books book3 = new Books("Harper Lee", "Ne tirez pas sur l'oiseau moqueur", "11 juillet 1960");
        
        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
    }
   /**
    * @inheritDoc
    *
    */
    @Override
    public List<String> getAuthors() {
        return em.createNamedQuery("getAuthor").getResultList();            
    }
   /**
    * @inheritDoc
    *
    */
    @Override
    public List<String> getTitles() {
        return em.createNamedQuery("getTitles").getResultList();         
    }
    /**
    * @inheritDoc
    *
    */
    @Override
    public void addTitle(String author, String title, String date) {
        Books book1 = new Books(author, title, date);
        em.persist(book1);
    }


}
