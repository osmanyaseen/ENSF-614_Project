package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.RegisteredUser;
import model.User;
import view.HubManager;
import view.LoginView;

/**
 * GUI interface for Login page
 * @author Deylin Yiao
 * @since December 7, 2021
 */
public class LoginController {
    
    private ModelController modelController;
    private ArrayList<RegisteredUser> regList;
    private User user;
    private LoginView view;
    private HubManager hubManager;
    
    /**
	 * Constructor method for LoginView Object, initially constructs and retrieves the page.
	 */
    public LoginController(ModelController modelController, LoginView view) {
        setModelController(modelController);
        setView(view);
        setUser(modelController.getUser());
        regList = modelController.getRegUserList();
        view.addRegisterListener(new AddLoginListener());
        view.addLoginListener(new AddLoginListener());
        view.addContinueListener(new AddLoginListener());
    }
    
    /**
     * Verifies user is within movie theatre database
     * @param email
     * @param password
     * @return
     */
    public boolean verifyUser(String email, String password) {
        for(RegisteredUser regUser: regList) {
            if(email.equals(regUser.getEmail()) && password.equals(regUser.getPassword())) {
            	user.setRegisteredUser(regUser);
                return true;
            }
        }
        return false;
    }
    
    /**
	 * Implements ActionListener and ties outputs to user action
	 */
    class AddLoginListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent evt) {
            if(evt.getSource() == view.getLoginButton()) {
                try {
                    String email = view.getEmailField().getText();
                    String password = new String(view.getPasswordField().getPassword());
                    if(verifyUser(email, password)) {
                    	hubManager.redirectToHomePageView();
                    } else {
                        view.displayErrorMessage("Invalid Login.");
                    }
                    
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else if (evt.getSource() == view.getRegisterButton()){
                try {
                    hubManager.redirectToRegistrationView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                hubManager.redirectToHomePageView();
            }
        }
    }

    // Getter and setter methods
    public ModelController getModelController() {
        return modelController;
    }

    public JPanel sendLoginPage() {
        return view.getView();
    }
    
    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
    }

    public ArrayList<RegisteredUser> getRegList() {
        return regList;
    }

    public void setRegList(ArrayList<RegisteredUser> regList) {
        this.regList = regList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginView getView() {
        return view;
    }

    public void setView(LoginView view) {
        this.view = view;
    }


    public HubManager getHubManager() {
        return hubManager;
    }


    public void setHubManager(HubManager hubManager) {
        this.hubManager = hubManager;
    }
    
}