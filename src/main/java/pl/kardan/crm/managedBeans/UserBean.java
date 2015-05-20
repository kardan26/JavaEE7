/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kardan.crm.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import pl.kardan.crm.db.controller.JpaController;
import pl.kardan.crm.db.entities.User;

/**
 *
 * @author Daniel
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable{

    
    private JpaController<User> jpaController;
    private User user;
    private List<User> users;
    private Class<User> userEntity;
    private String password2;
    @PersistenceContext(unitName = "CrmPU")
    private EntityManager em;
    
    @Resource
    private UserTransaction utx;
    
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
       user = new User();
       users = new ArrayList<>();
    }
    
    @PostConstruct
    public void init(){
        jpaController = new JpaController<>(em,utx); 
    }
    public String addUser()
    {
        if(user.getPassword().equals(password2))
        {
            jpaController.create(user);
            return "index";
        }
        else
            return "";
        
    }
    public List<User> getUsers()
    {       
         if(jpaController != null)
             users = jpaController.findAll(User.class);
        return users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        System.out.println(user.getUserName());
        this.user = user;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
//    
//    public void login(ActionEvent ev)
//    {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        try {
//            String navigateString = "";
//            // Checks if username and password are valid if not throws a ServletException
//            request.login(user.getUserName(), user.getPassword());
//            // gets the user principle and navigates to the appropriate page
//            Principal principal = request.getUserPrincipal();
//            if (request.isUserInRole("admin")) {
//                navigateString = "/admin/index";
//            } 
//            try {
//                
//                context.getExternalContext().redirect(request.getContextPath() + navigateString);
//            } catch (IOException ex) {
//                context.addMessage(null, new FacesMessage("Error!" + request.getUserPrincipal().toString(), "Exception occured"));
//            }
//        } catch (ServletException e) {
//            context.addMessage(null, new FacesMessage("Error!" +  request.getUserPrincipal().toString(), "The username or password you provided does not match our records."));
//        }
//    }
    public void logout(ActionEvent actionEvent)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        if(req.getUserPrincipal()!=null)
            try {
                req.logout();
                facesContext.getExternalContext().responseReset();
                
        } catch (ServletException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
   
    }
   
}
