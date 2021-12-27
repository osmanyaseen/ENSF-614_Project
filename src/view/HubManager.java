package view;

import controller.*;

/**
 * View manager class responsible for redirecting each component view through the HubView class
 * @author Deylin Yiao & Maliha Zakir
 * @since December 7, 2021
 */
public class HubManager {

    private HubView hubView = new HubView();

    private LoginController loginController;
    private RegistrationController registrationController;
    private HomePageController homePageController;
    private PaymentController paymentController;
    private CancelController cancelController;

    /**
     * Constructor method for hubManager object
     * @param hubView HubView object
     * @param loginController LoginController Object
     * @param registrationController RegistrationController Object
     * @param paymentController PaymentController Object
     * @param homePageController HomePageController Object
     * @param cancelController CancelController Object
     */
    public HubManager(HubView hubView, LoginController loginController, RegistrationController registrationController,
    					 PaymentController paymentController,HomePageController homePageController,
    					CancelController cancelController){
        this.hubView = hubView;
        this.loginController = loginController;
        loginController.setHubManager(this);
        this.registrationController = registrationController;
        registrationController.setHubManager(this);
        this.homePageController = homePageController;
        homePageController.setHubManager(this);
        this.paymentController = paymentController;
        paymentController.setHubManager(this);
        this.cancelController = cancelController;
        cancelController.setHubManager(this);
        this.hubView.initialBuild();
        hubView.addComponent(loginController.getView());
    }
    
    /**
     * Redirects the hub view to display the registration GUI
     */
    public void redirectToRegistrationView() {
    	hubView.refresh();
    	hubView.addComponent(registrationController.getView());
    	hubView.revalidate();
    	hubView.repaint();
    }
    
    /**
     * Redirects the hub view to display the login GUI
     */
    public void redirectToLoginView() {
    	hubView.refresh();
    	hubView.addComponent(loginController.getView());
    	hubView.revalidate();
    	hubView.repaint();
    }
    
    /**
     * Redirects the hub view to display the payment GUI
     */
    public void redirectToPaymentView() {
    	hubView.refresh();
    	hubView.addComponent(paymentController.getView());
    	hubView.revalidate();
    	hubView.repaint();
    }

    /**
     * Redirects the hub view to display the homepage/movie selection GUI
     */
    public void redirectToHomePageView() {
    	hubView.refresh();
    	hubView.addComponent(homePageController.getHomePageView());
    	hubView.revalidate();
    	hubView.repaint();
    }
    
    /**
     * Redirects the hub view to display the cancellation GUI
     */
	public void redirectToCancelView() {
		hubView.refresh();
    	hubView.addComponent(cancelController.getView());
    	hubView.revalidate();
    	hubView.repaint();
		
	}
    
	// Getter and setter methods
    public HubView getHubView() {
		return hubView;
	}
    
	public void setHubView(HubView hubView) {
		this.hubView = hubView;
	}

}
