/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.ejb;

import car.dadatabse.Books;
import car.dadatabse.Client;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rkouere
 */
@Local
public interface UserFacadeLocalItf {
    /**
     * Adds one user called "user" to the database
     */
    public void init();
    /**
     * Adds a user to the database
     * @param The name of the user  
     */
    public void addUser(String name);
    
    /**
     * Remove a user to the database
     * @param The name of the user 
     */
    public boolean removeUser(String name);
    
    /**
     * Checks that a user exists
     * @param The username
     * @return true if the user exists
     */
    public List<Client> checkUserExists(String name);
    
}
