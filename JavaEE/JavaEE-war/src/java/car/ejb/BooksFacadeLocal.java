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
 * EJB enabling three things:
 * - init some dumyy information regarding available books (init)
 * - add a title to the database
 * - find all the titles
 * - find books by author
 * @author echallier
 */
@Stateless
public class BooksFacadeLocal implements BooksFacadeLocalItf {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;

    /**
     * Constructor
     */
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
        Books book3 = new Books("Harper Lee", "Ne tirez pas sur l'oiseau moqueur", "11/071960");
        Books book4 = new Books("Gabriel García Márquez", "Cent ans de solitude", "01/02/1967");
        
        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
        em.persist(book4);

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
    public boolean addTitle(String author, String title, String date) {
        Books book1 = new Books(author, title, date);
        try {
            em.persist(book1);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
   /**
    * @inheritDoc
    *
    */
    @Override
    public List<Books> findAllTitles() {
        Query q = em.createQuery("SELECT OBJECT(b) FROM Books b");
        //q.setParameter("ti", title);
        return q.getResultList();
    }
   /**
    * @inheritDoc
    *
    */
    @Override
    public List<Books> findBooksByAuthor(String author) {
        Query q = em.createQuery("SELECT OBJECT(b) FROM Books b WHERE b.author = :au");
        q.setParameter("au", author);
        return q.getResultList();
    }

    /**
    * @inheritDoc
    *
    */
    @Override
    public List<Books> findBooksByTitle(String title) {
        Query q = em.createQuery("SELECT OBJECT(b) FROM Books b WHERE b.title = :au");
        q.setParameter("au", title);
        return q.getResultList();
    }


}
