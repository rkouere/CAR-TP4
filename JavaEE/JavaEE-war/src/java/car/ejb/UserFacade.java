/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.ejb;

import car.dadatabse.Client;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Was supposed to be used to manage users
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
        Client user1 = new Client("user1");
        Client user2 = new Client("user2");
        em.persist(user1); 
        em.persist(user2);
    }

    /**
    * @inheritDoc
    *
    */    
    @Override
    public void addUser(String name) {
        Client user1 = new Client(name);
        em.persist(user1); 
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
    public boolean checkUserExists(String name) {
        List<Client> listTmp = checkUser(name);
 
        for(Client c:listTmp)
            if(c.getPseudo().equals(name))
                return true;
        return false;
    }
    
    private List<Client> checkUser(String name) {
        Query q = em.createQuery("SELECT OBJECT(u) FROM Client u WHERE u.pseudo = :au");
        return q.setParameter("au", name).getResultList();
    }

    @Override
    public List<Client> getAllUsers() {
        Query q = em.createQuery("SELECT *  FROM Client c");
        return q.getResultList();
    }
    
    
}
