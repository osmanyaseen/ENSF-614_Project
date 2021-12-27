package controller;

import view.*;

/**
 * The main application class
 * @author Deylin Yiao
 * @since December 7, 2021
 *
 */
public class MovieTheatreApp {

	public static void main(String[] args) {
		ModelController modelController = ModelController.getOnlyInstance();
		LoginView loginView = new LoginView();
		RegistrationView registrationView = new RegistrationView();
		PaymentView paymentView = new PaymentView();
		HomePageView homePageView = new HomePageView();
		CancellationView cancellationView = new CancellationView();
		HubView hubView = new HubView();
		LoginController loginController = new LoginController(modelController, loginView);
		RegistrationController registrationController = new RegistrationController(modelController, registrationView);
		PaymentController paymentController = new PaymentController(modelController, paymentView);
		HomePageController homePageController = new HomePageController(modelController, homePageView);
		CancelController cancelController = new CancelController(modelController, cancellationView);
		HubManager manager = new HubManager(hubView, loginController, registrationController,
											paymentController, homePageController, cancelController);
	}
}
