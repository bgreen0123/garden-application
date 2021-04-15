package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;



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
	MarketWindowHerbaceous marketH;
	MarketWindowWoody marketW;
	ConditionsWindow conditions;
	
	Model model;
	

	@Override
    public void start(Stage stage) {
		cont = new Controller(this);
		model = cont.getModel();
		welcome = new WelcomeScreen(width, height, stage, cont.getWelcomeButton());
		conditions = new ConditionsWindow(width, height, stage, cont.getSun(), cont.getSoil(), cont.getMoisture(), cont.getConditionsButton());
		endWindow = new EndWindow(width, height, stage, cont.getDownloadButton(), model.getBudget(), model.getPlants(), model.getNumLeps());
		marketH = new MarketWindowHerbaceous(width, height, stage, cont.getHerbaceousMarket(), cont.getWoody(), cont.getMarketNext());
		marketW = new MarketWindowWoody(width, height, stage, cont.getWoodyMarket(), cont.getHerbaceous(), cont.getMarketNext());
		garden = new GardenWindow(width, height, stage, cont, cont.getEndButton());
		stage.setTitle("Garden");
		currentScreen = CurrentScreen.WELCOME;
		drawScreen();
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
			System.out.println("End");
			endWindow.draw();
			break;
		case MARKET_H:
			marketH.draw();
			break;
		case MARKET_W:
			marketW.draw();
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

