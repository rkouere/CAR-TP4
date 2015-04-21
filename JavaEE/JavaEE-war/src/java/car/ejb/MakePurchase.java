/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.ejb;

import car.dadatabse.Books;
import car.dadatabse.Purchase;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rkouere
 */
@Stateless
public class MakePurchase implements MakePurchaseLocal {
    @PersistenceContext
    private EntityManager em;
    Collection<Books> liste;
    Purchase purchase = null;
    /**
     * Constructor
     */
    public MakePurchase() {
        this.purchase = new Purchase();
    }
    
    
    /**
    * @inheritDoc
    *
    */
    @Override
    public void makePurchase(List<Books> list) {
        this.purchase.setShoping(liste);
        em.persist(purchase);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Purchase> getAllPurchase() {
        return em.createNamedQuery("getAllOrders").getResultList();         
    }
}
