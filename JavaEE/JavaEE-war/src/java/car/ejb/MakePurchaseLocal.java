/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.ejb;

import car.dadatabse.Books;
import car.dadatabse.Purchase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rkouere
 */
@Local
public interface MakePurchaseLocal {
    /**
     * Adds the list of purchases
     * @param list of books to purchase
     */
    public void makePurchase(List<Books> list);
    /**
     * 
     * @return all the purchases made 
     */
    public List<Purchase> getAllPurchase();
}
