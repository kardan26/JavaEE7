/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kardan.crm.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import pl.kardan.crm.db.controller.JpaController;
import pl.kardan.crm.db.entities.User;

/**
 *
 * @author Daniel
 */
@Named(value = "authorizedUserBean")
@SessionScoped
public class authorizedUserBean implements Serializable {

    /**
     * Creates a new instance of authorizedUserBean
     */
    private JpaController<User> jpaController;
    private User user;
    
   
    public authorizedUserBean() {
    }
    
    public void init()
    {
        
    }
    
}
