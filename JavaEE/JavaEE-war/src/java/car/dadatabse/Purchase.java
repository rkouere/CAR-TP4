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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The java representation of the Purchase table
 * @author rkouere
 */
@Entity
@NamedQueries(value= {
    @NamedQuery(name="getAllOrders", query="SELECT DISTINCT p.id FROM Purchase p"),
})
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Collection<Books> shoping;
    /**
     * Constructor
     */
    public Purchase() {
    }
    /**
     * Sets the shopping list
     * @param shoping A collection
     */
    public void setShoping(Collection<Books> shoping) {
        this.shoping = shoping;
    }
    
    
    
    
    /**
     * 
     * @return The transaction's ID 
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the transaction's id
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "car.dadatabse.Purchase[ id=" + id + " ]";
    }
    
}
