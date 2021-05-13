package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import java.awt.*;

import controller.Controller;

import enums.CurrentScreen;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
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

	/**
	 * start method that contains creates all instances of each window
	 * sets current screen as the welcome
	 * calls draw Screen method
	 */
	@Override
    public void start(Stage stage) {
		cont = new Controller(this);
		model = cont.getModel();
		welcome = new WelcomeScreen(width, height, stage, cont.getWelcomeButton(), cont.getToLoadButton(), cont.getLoadButton());
		conditions = new ConditionsWindow(width, height, stage, cont.getSun(), cont.getSoil(), cont.getMoisture(), cont.getConditionsButton(), cont.getXSlider(), cont.getYSlider(), cont.getBudget(), cont.getWidth(), cont.getHeight());
		endWindow = new EndWindow(width, height, stage, cont.getRestart(), cont, cont.getFileName(), cont.getSave(),cont.getPlantsList(), cont.getFavoriteList(), this, cont.getSaveButton(), cont.getEndToGarden());
		marketH = new MarketWindowHerbaceous(width, height, stage, cont.getHerbaceousMarket(), cont.getWoody(), cont.getMarketNext(), cont.getApplyConditions(), cont.getViewFavs(), cont.getBackToConditions(), cont);
		marketW = new MarketWindowWoody(width, height, stage, cont.getWoodyMarket(), cont.getHerbaceous(), cont.getMarketNext(), cont.getApplyConditions(), cont.getViewFavs(), cont.getBackToConditions(), cont);
		garden = new GardenWindow(width, height, stage, cont, cont.getEndButton());
		favs = new Favs(width, height, stage, cont.getBackToMarket(), cont.getMarketNext(), cont);
		load = new LoadWindow(width, height, stage, cont.getFileName(), cont.getToWelcomeButton());
		ls = new LoadingScreen(stage, width, height);
		
		stage.setTitle("Garden");
		currentScreen = CurrentScreen.WELCOME;
		drawScreen();
	}
	
	/**
	 * sets the filter for herbaceous market and woody market
	 * 
	 */
	public void setFilter() {
		marketH.setFilter();
		marketW.setFilter();
		drawScreen();
	}
	
	/**
	 * sets a default market for herbaceous market and woody market
	 * 
	 */
	public void defaultFilter() {
		marketH.defaultFilter();
		marketW.defaultFilter();
		drawScreen();
	}
	
	/**
	 * switch case that draws a screen
	 * contains a case for each screen in our garden application
	 */
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
	
	/**
	 * changes screen and uses the drawScreen method
	 * @param s
	 */
	public void changeScreen(CurrentScreen s){
		currentScreen = s;
		drawScreen();
	}
	
	/**
	 * gets screen height
	 * @return height of screen
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * gets screen width
	 * @return width of screen
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * gets the Garden Window
	 * @return garden instance of a Garden
	 */
	public GardenWindow getGardenWindow() {
		return garden;
	}
	
	/**
	 * main method that launches application
	 * @param args
	 */
	public static void main(String[] args) {
		launch();
	}
}

