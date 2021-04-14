package view;

import javafx.application.Application;
import javafx.stage.Stage;

import controller.Controller;

import enums.CurrentScreen;

public class View extends Application {
	int height = 600;
	int width = 800;
	Controller cont;
	CurrentScreen currentScreen;
	WelcomeScreen welcome;
	EndWindow endWindow;
	GardenWindow garden;
	MarketItem marketItem;
	MarketWindow marketWindow;
	MarketEntryWindow marketEntry;
	ConditionsWindow conditions;

	@Override
    public void start(Stage stage) {
		cont = new Controller(this);
		welcome = new WelcomeScreen(width, height, stage, cont.getWelcomeButton());
		conditions = new ConditionsWindow(width, height, stage);
		garden = new GardenWindow(width, height, stage, cont);
		stage.setTitle("Garden");
		currentScreen = CurrentScreen.GARDEN;
		drawScreen();
	}
	
	public void update() {
		return;
	}
	
	public void drawScreen() {
		switch(currentScreen){
		case WELCOME:
			System.out.println("Welcome");
			welcome.draw();
			break;
		case GARDEN:
			System.out.println("Garden");
			garden.draw();
			break;
		case CONDITIONS:
			System.out.println("Conditions");
			conditions.draw();
			break;
		case END:
			//endWindow.draw();
			break;
		case MARKET:
			//marketWindow.draw();
			break;
		default:
			welcome.draw();
			currentScreen = CurrentScreen.WELCOME;
			break;
		}
		return;
	}

	public void changeScreen(CurrentScreen s){
		currentScreen = s;
		drawScreen();
	}

	public static void main(String[] args) {
		launch();
	}
}