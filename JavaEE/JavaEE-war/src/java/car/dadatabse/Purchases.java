/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dadatabse;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author rkouere
 */
@Entity
public class Purchases implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
    /**
     * The books associated with the purchase
     */
    private Collection<Books> books;
    
    private String user;
    
    public Collection<Books> getBooks() {
        return this.books;
    }
    
    public void addBooks(Books book) {
        this.books.add(book);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    @Override
    public String toString() {
        return "car.dadatabse.Purchases[ id=" + id + " ]";
    }
    
}
