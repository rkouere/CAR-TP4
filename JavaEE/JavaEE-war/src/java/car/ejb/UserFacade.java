/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.ejb;

import car.dadatabse.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rkouere
 */
@Stateless
public class UserFacade implements UserFacadeLocalItf {
    @PersistenceContext
    private EntityManager em;

    public UserFacade() {
    }
    
    /**
    * @inheritDoc
    *
    */    
    @Override
    public void init() {
        User user1 = new User("user1");
        User user2 = new User("user2");
        em.persist(user1);
        em.persist(user2);
    }

    /**
    * @inheritDoc
    *
    */    
    @Override
    public boolean addUser(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
    * @inheritDoc
    *
    */    
    @Override
    public boolean removeUser(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> checkUserExists(String name) {
        Query q = em.createQuery("SELECT OBJECT(u) FROM User u");
        return q.getResultList();
    }
    
    
}
