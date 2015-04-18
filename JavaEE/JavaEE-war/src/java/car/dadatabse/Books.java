/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package car.dadatabse;
import car.ejb.*;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table; 

/**
 *
 * @author echallier
 */
@Entity
@Table(name="Books")
@NamedQueries(value= {@NamedQuery(name="getAuthor", query="SELECT DISTINCT b.author FROM Books b ORDER BY b.author"), 
@NamedQuery(name="getTitles", query="SELECT DISTINCT b.title FROM Books b ORDER BY b.title")})
public class Books implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String title;
    private String author;
    private String date;

    public Books() {
    }
    
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (title != null ? title.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.title == null && other.title != null) || (this.title != null && !this.title.equals(other.title))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dadatabse.book.NewEntity[ id=" + title + " ]";
    }

    public Books(String title, String author, String date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the author to set
     */
    public void setTitle(String id) {
        this.title = id;
    }
    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
}
