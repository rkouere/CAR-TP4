/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dadatabse;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The java representation of the Clients table
 * @author rkouere
 */
@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pseudo;

    /**
     * 
     * @return The pseudo of the client 
     */
    public String getPseudo() {
        return pseudo;
    }
    /**
     * Sets the seudo of the client
     * @param pseudo 
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    /**
     * Constructor
     * @param The client's pseudo 
     */
    public Client(String pseudo) {
        this.pseudo = pseudo;
    }
    /**
     * Constructor
     */
    public Client() {
    }
    
    /**
     * 
     * @return The client's ID
     */
    public Long getId() {
        return id;
    }
    /**
     * Set's the user's id
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "car.dadatabse.Users[ id=" + id + " ]";
    }
    
}
