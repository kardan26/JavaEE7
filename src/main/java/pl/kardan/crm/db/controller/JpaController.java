/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kardan.crm.db.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Daniel
 */
public class JpaController<T> implements Serializable{

    private  EntityManager em;
    private UserTransaction utx;
    
    
    public JpaController(EntityManager em, UserTransaction utx) {
        this.em = em;
        this.utx = utx;
      
    }
    
    public void create(T item) {
        try {
            utx.begin();
            em.persist(item);
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(JpaController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public List<T> findAll(Class<T> entityClass){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery(entityClass);
        Root<T> root = (Root<T>) cq.from(entityClass);
        cq.select(root);
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }
    
}
