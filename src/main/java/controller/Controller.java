package controller;

import java.util.ArrayList;

import enums.CurrentScreen;
import enums.Moisture;
import enums.Soil;
import enums.Sun;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Model;
import model.Plant;
import view.MarketItem;
import view.View;

public class Controller{
	Model model;
	View view;
	//Buttons
	Button welcomeNext;
	Button download;
	Button conditionsNext;
	Button woody;
	Button herbaceous;
	Button marketNext;
	Button gardenNext;
	
	//Favorite buttons
	ArrayList<MarketItem> woodyMarket = new ArrayList<MarketItem>();
	ArrayList<MarketItem> herbaceousMarket = new ArrayList<MarketItem>(); 
	
	//Drop down menus
	ChoiceBox<Sun> sun;
	ChoiceBox<Soil> soil;
	ChoiceBox<Moisture> moisture;
	
	//Budget
	TextField text;
	
	//User answers
  	Sun sunChoice;
  	Soil soilChoice;
  	Moisture moistureChoice;
  	

	public Controller(View view){
		this.view = view;
		model = new Model();
		
		model.getData().getWoody().forEach(p ->{
			Button fav = new Button();
			fav.setOnAction(e -> model.addFavoritePlant(p));
			MarketItem m = new MarketItem(p, fav);
			woodyMarket.add(m);
		});
		
		model.getData().getHerbacious().forEach(p ->{
			Button fav = new Button();
			fav.setOnAction(e -> model.addFavoritePlant(p));
			MarketItem m = new MarketItem(p, fav);
			herbaceousMarket.add(m);
		});
		
		//Buttons
		welcomeNext = new Button("Start");
		conditionsNext = new Button("Go To Market");
		gardenNext = new Button("FINISH");
		woody = new Button("View Woody Plants");
		herbaceous = new Button("View Herbaceous Plants");
		marketNext = new Button("Go To Garden");
		
		//Actions
		welcomeNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.CONDITIONS); // change back to CONDITIONS
			System.out.println("Button Pressed");
		});

		conditionsNext.setOnAction(e -> {
			setChoice(sun,soil,moisture);
			view.changeScreen(CurrentScreen.MARKET_H); //Move to market screen
			System.out.println("Onto market");
		});
		
		gardenNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.END);
			System.out.println("End Window");
		});
		
		woody.setOnAction(e -> view.changeScreen(CurrentScreen.MARKET_W));
		herbaceous.setOnAction(e -> view.changeScreen(CurrentScreen.MARKET_H));
		marketNext.setOnAction(e -> view.changeScreen(CurrentScreen.GARDEN));
		
		//Creating the drop down menu
		sun = new ChoiceBox<>();
		soil = new ChoiceBox<>();
		moisture = new ChoiceBox<>();
		//Adding choices into the drop menu
		sun.getItems().addAll(Sun.FULLSUN,Sun.PARTSUN,Sun.SHADE);
		soil.getItems().addAll(Soil.CLAY,Soil.DIRT,Soil.ROCK);
		moisture.getItems().addAll(Moisture.WET,Moisture.DRY);
		//Starting value that the user sees
		sun.getSelectionModel().select(0);
		soil.getSelectionModel().select(0);
		moisture.getSelectionModel().select(0);
		
		//Making text box
		text = new TextField("Enter Your Budget!");

	}

	//Getters
	public Button getMarketNext() {
		return marketNext;
	}
	
	public Button getHerbaceous() {
		return herbaceous;
	}
	
	public Button getWoody() {
		return woody;
	}
	
	public ArrayList<MarketItem> getHerbaceousMarket(){
		return herbaceousMarket;
	}
	
	public ArrayList<MarketItem> getWoodyMarket(){
		return woodyMarket;
	}
	
	public Button getWelcomeButton(){
		return welcomeNext;
	}
	
	public Button getDownloadButton() {
		return download;
	}

	public Button getConditionsButton(){
		return conditionsNext;
	}
	
	public Button getEndButton() {
		return gardenNext;
	}

	public ChoiceBox<Sun> getSun() {
		return sun;
	}

	public ChoiceBox<Soil> getSoil() {
		return soil;
	}

	public ChoiceBox<Moisture> getMoisture() {
		return moisture;
	}
	
	public TextField getText() {
		return text;
	}
	
	public Model getModel() {
		//This method is used for view to access certain fields such as budget and lep count
		//No fields should be changed or updated from view, it is only to access values.
		return model;
	}
	
	//Setter to set the users choice of conditions
	public void setChoice(ChoiceBox<Sun> sun, ChoiceBox<Soil> soil, ChoiceBox<Moisture> moisture ) {
		sunChoice = sun.getValue();
		soilChoice = soil.getValue();
		moistureChoice = moisture.getValue();
		model.setSun(sunChoice);
		model.setSoil(soilChoice);
		model.setMoisture(moistureChoice);
		System.out.println(sunChoice + ", " + soilChoice + ", and " + moistureChoice);
	}

	//Actions
	public void goToPage(CurrentScreen cs) {
		view.changeScreen(cs);
	}

	public void updateLeps(int leps) {
		model.updateLepCount(leps);
	}
	
	public void updateBudget(int cost) {
		model.updateBudget(model.getBudget() - cost);
	}
	
	public void addFavPlant(Plant p) {
		model.addFavoritePlant(p);
	}
	
	public EventHandler dragPlant() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	/*
	 * Change Listeners for Sliders can be added after those sliders are created
	 * Need them for: 
	 *     Choosing width
	 *     Choosing height
	 */
	
	public EventHandler enterBudget() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	public EventHandler toOtherPlantType() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
		};
	}
	
	public EventHandler favoritePlant() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
		};
	}
}


