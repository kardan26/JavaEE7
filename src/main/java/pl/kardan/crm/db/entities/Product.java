/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kardan.crm.db.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity(name = "\"TProduct\"")
@XmlRootElement
public class Product extends AbstractEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Column(name = "productName")
    private String productName;
    @Column(name = "prize")
    private Double prize;
    
    @ManyToMany
    private Collection<Category> categories;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Collection<Category> getProductCategory() {
        return categories;
    }

    public void setProductCategory(Collection<Category> productCategory) {
        this.categories = productCategory;
    }
    
    
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.kardan.crm.db.entities.Product[ id=" + Id + " ]";
    }
    
}
