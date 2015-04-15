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
public class booksFacade implements booksFacadeLocal {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    /**
    * @inheritDoc
    *
    */
    @Override
    public void init() {
        Books book1 = new Books();
        book1.setAuthor("Orwell");
        book1.setTitle("1984");
        book1.setDate("08/06/1949");
        Books book2 = new Books();
        book1.setAuthor("Kipling");
        book1.setTitle("Kim");
        book1.setDate("01/10/1901");
        
        em.persist(book1);
        em.persist(book2);
    }

    @Override
    public List<String> getAuthors() {
        Query q = em.createNamedQuery("getAuthor");
        List<String> list = (List<String>) q.getResultList();
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
