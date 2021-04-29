package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import java.awt.*;

import controller.Controller;

import enums.CurrentScreen;

public class View extends Application {
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) size.getHeight() - 65;
	int width = (int) size.getWidth();
	Controller cont;
	CurrentScreen currentScreen;
	WelcomeScreen welcome;
	EndWindow endWindow;
	GardenWindow garden;
	MarketWindowHerbaceous marketH;
	MarketWindowWoody marketW;
	ConditionsWindow conditions;
	Favs favs;
	LoadWindow load;
	LoadingScreen ls;
	
	Model model;
	View view;

	@Override
    public void start(Stage stage) {
		cont = new Controller(this);
		model = cont.getModel();
		welcome = new WelcomeScreen(width, height, stage, cont.getWelcomeButton(), cont.getToLoadButton(), cont.getLoadButton());
		conditions = new ConditionsWindow(width, height, stage, cont.getSun(), cont.getSoil(), cont.getMoisture(), cont.getConditionsButton(), cont.getXSlider(), cont.getYSlider(), cont.getBudget());
		endWindow = new EndWindow(width, height, stage, cont.getDownloadButton(), cont, cont.getFileName(), cont.getSave(),cont.getPlantsList(), cont.getFavoriteList(), this, cont.getSaveButton(), cont.getEndToGarden());
		marketH = new MarketWindowHerbaceous(width, height, stage, cont.getHerbaceousMarket(), cont.getWoody(), cont.getMarketNext(), cont.getApplyConditions(), cont.getViewFavs(), cont.getBackToConditions(), cont);
		marketW = new MarketWindowWoody(width, height, stage, cont.getWoodyMarket(), cont.getHerbaceous(), cont.getMarketNext(), cont.getApplyConditions(), cont.getViewFavs(), cont.getBackToConditions(), cont);
		garden = new GardenWindow(width, height, stage, cont, cont.getEndButton());
		favs = new Favs(width, height, stage, cont.getBackToMarket(), cont.getMarketNext(), cont.getBackToConditions(), cont);
		load = new LoadWindow(width, height, stage, cont.getFileName(), cont.getToWelcomeButton());
		ls = new LoadingScreen(stage, width, height);
		
		stage.setTitle("Garden");
		currentScreen = CurrentScreen.WELCOME;
		drawScreen();
	}
	
	public void setFilter() {
		marketH.setFilter();
		marketW.setFilter();
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
		case FAVS:
			favs.draw();
			break;
		case LOAD:
			System.out.println("Load");
			load.draw();
			break;
		case LOADINGSCREEN:
			System.out.println("Loading");
			ls.draw();
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

