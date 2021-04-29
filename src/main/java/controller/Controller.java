package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import enums.CurrentScreen;
import enums.Moisture;
import enums.Soil;
import enums.Sun;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
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
	Button applyConditions;
	Button viewFavs;
	Button backToMarket;
	Button backToConditions;
	Button toLoad;
	Button save;
	Button load;
	Button toWelcome;
	
	//Favorite buttons
	ArrayList<MarketItem> woodyMarket = new ArrayList<MarketItem>();
	ArrayList<MarketItem> herbaceousMarket = new ArrayList<MarketItem>(); 
	
	//Drop down menus
	ChoiceBox<Sun> sun;
	ChoiceBox<Soil> soil;
	ChoiceBox<Moisture> moisture;
	
	//Sliders
	Slider x;
	Slider y;
	
	//Text Boxes
	TextField budget;
	TextField saveBox;
	TextField loadBox;
	
	//User answers
  	Sun sunChoice;
  	Soil soilChoice;
  	Moisture moistureChoice;
  	
  	//Load file
  	String fileName;
  	
	public Controller(View view){
		this.view = view;
		model = new Model();
		model.getThread().start();
		
		model.getData().getWoody().forEach(p ->{
			Button fav = new Button();
			fav.setOnAction(e -> {
				for(Plant pl : model.getFavorites()) {
					if(pl.getComName().equals(p.getComName())) {
						duplicateError();
						return;
					}
				}
				model.addFavoritePlant(p);
				ImageView i = new ImageView(new Image(getClass().getResourceAsStream("/images/check.png")));
				i.setFitHeight(30);
				i.setFitWidth(30);
				fav.setGraphic(i);
			});
			MarketItem m = new MarketItem(p, fav);
			woodyMarket.add(m);
		});
		
		model.getData().getHerbacious().forEach(p ->{
			Button fav = new Button();
			fav.setOnAction(e -> {
				for(Plant pl : model.getFavorites()) {
					if(pl.getComName().equals(p.getComName())) {
						duplicateError();
						return;
					}
				}
				model.addFavoritePlant(p);
				ImageView i = new ImageView(new Image(getClass().getResourceAsStream("/images/check.png")));
				i.setFitHeight(30);
				i.setFitWidth(30);
				fav.setGraphic(i);
			});
			MarketItem m = new MarketItem(p, fav);
			herbaceousMarket.add(m);
		});
		
		//Buttons
		welcomeNext = new Button("Start");
		conditionsNext = new Button("NEXT");
		gardenNext = new Button("FINISH");
		woody = new Button("View Woody Plants");
		herbaceous = new Button("View Herbaceous Plants");
		marketNext = new Button("Go To Garden");
		applyConditions = new Button("Apply Conditions");
		viewFavs = new Button("View Favorites");
		backToMarket = new Button("Return to Market");
		backToConditions = new Button("Return to Conditions");
		toLoad = new Button("Load Garden");
		save = new Button("Save");
		load = new Button("Load");
		toWelcome = new Button("Back to Start");
		
		//Making text box
		budget = new TextField();
		saveBox = new TextField();
		
		//Making sliders
		x = new Slider();
		y = new Slider();
		
		//Actions
		welcomeNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.CONDITIONS); // change back to CONDITIONS
			System.out.println("Button Pressed");
		});
		
		backToConditions.setOnAction(e -> view.changeScreen(CurrentScreen.CONDITIONS));
		
		x.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        model.setWidth(new_val.intValue());
                        System.out.println("Garden Width: " + model.getWidth());
                }
            });
		
		y.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        model.setHeight(new_val.intValue());
                        System.out.println("Garden Height: " + model.getHeight());
                }
            });
		conditionsNext.setOnAction(e -> {
			setChoice(sun,soil,moisture);
			String input = budget.getText();
			int budget;
			if(input == "") {
				budgetError();
				return;
			}
			try {
				budget = Integer.parseInt(input);
			}catch (Exception exc){
				budgetError();
				return;
			}
			model.updateBudget(budget);
			view.changeScreen(CurrentScreen.MARKET_H); //Move to market screen
			System.out.println("Onto market");
		});
		
		gardenNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.END);
			System.out.println("End Window");
		});
		
		woody.setOnAction(e -> view.changeScreen(CurrentScreen.MARKET_W));
		herbaceous.setOnAction(e -> view.changeScreen(CurrentScreen.MARKET_H));
		marketNext.setOnAction(e -> loading());
		applyConditions.setOnAction(e -> view.setFilter());
		viewFavs.setOnAction(e -> view.changeScreen(CurrentScreen.FAVS));
		backToMarket.setOnAction(e -> view.changeScreen(CurrentScreen.MARKET_H));
		toLoad.setOnAction(e ->  view.changeScreen(CurrentScreen.LOAD));
		toWelcome.setOnAction(e -> view.changeScreen(CurrentScreen.WELCOME));
		
		save.setOnAction(e -> {
			try {
				FileOutputStream fos = new FileOutputStream("garden.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(model);
				oos.flush();
				oos.close();
			}
			catch(Exception exc) {
				System.out.println("Couldn't save file");
				saveError();
				return;
			}
		});
		
		load.setOnAction(e -> {
			try {
				FileInputStream fis = new FileInputStream("garden.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				model = (Model) ois.readObject();
				ois.close();
			}
			catch(Exception exc) {
				System.out.println("Couldn't load file");
				loadError();
				return;
			}
		});
		
		
		
		//Creating the drop down menu
		sun = new ChoiceBox<>();
		soil = new ChoiceBox<>();
		moisture = new ChoiceBox<>();
		//Adding choices into the drop menu
		sun.getItems().addAll(Sun.SUN,Sun.FULLSUN,Sun.PARTSUN,Sun.SHADE);
		soil.getItems().addAll(Soil.SOIL,Soil.CLAY,Soil.DIRT,Soil.ROCK);
		moisture.getItems().addAll(Moisture.MOISTURE,Moisture.WET,Moisture.DRY);
		
		//Starting value that the user sees
		sun.setValue(Sun.SUN);
		soil.setValue(Soil.SOIL);
		moisture.setValue(Moisture.MOISTURE);
	}

	private void loading() {
		if(model.getThread().isAlive()) {
			view.changeScreen(CurrentScreen.LOADINGSCREEN);
			Thread endLoad = new Thread(new Runnable() {

				@Override
				public void run() {
					while(model.getThread().isAlive());
					Platform.runLater(new Runnable() {
			            @Override
			            public void run() {
			              view.changeScreen(CurrentScreen.GARDEN);
			            }
			          });
				}
				
			});
			endLoad.start();
		}else {
			view.changeScreen(CurrentScreen.GARDEN);
		}
	}
	
	//Error Handling
	private void duplicateError() {
		Alert dupError = new Alert(AlertType.ERROR);
		dupError.setHeaderText("Plant Already Added");
		dupError.setContentText("You can only add a plant to the favorite list 1 time.");
		dupError.showAndWait();
	}
	private void budgetError() {
		Alert budgetError = new Alert(AlertType.ERROR);
		budgetError.setHeaderText("Invalid budget");
		budgetError.setContentText("Budget must be an integer and cannot be empty.");
		budgetError.showAndWait();
	}
	private void saveError() {
		Alert saveError = new Alert(AlertType.ERROR);
		saveError.setHeaderText("Could not save garden");
		saveError.setContentText("The garden could not be saved at this time. Please try again later.");
		saveError.showAndWait();
	}
	private void loadError() {
		Alert saveError = new Alert(AlertType.ERROR);
		saveError.setHeaderText("Could not load garden");
		saveError.setContentText("The garden could not be loaded at this time. Please try again later.");
		saveError.showAndWait();
	}

	//Getters
	public ArrayList<Plant> getPlantsList(){
		return model.getPlants();
	}
	
	public ArrayList<Plant> getFavoriteList(){
		return model.getFavorites();
	}
	
	public Button getBackToConditions() {
		return backToConditions;
	}
	
	public Button getMarketNext() {
		return marketNext;
	}
	
	public Button getHerbaceous() {
		return herbaceous;
	}
	
	public Button getWoody() {
		return woody;
	}
	
	public Button getApplyConditions() {
		return applyConditions;
	}
	public Button getViewFavs() {
		return viewFavs;
	}
	public Button getBackToMarket() {
		return backToMarket;
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
	
	public Button getToWelcomeButton(){
		return toWelcome;
	}
	
	public Button getToLoadButton() {
		return toLoad;
	}
	
	public Button getSaveButton() {
		return save;
	}
	
	public Button getLoadButton() {
		return load;
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
	
	public TextField getBudget() {
		return budget;
	}
	
	public TextField getSave() {
		return saveBox;
	}
	
	public Slider getXSlider() {
		return x;
	}
	
	public Slider getYSlider() {
		return y;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public Model getModel() {
		//This method is used for view to access certain fields such as budget and lep count
		//No fields should be changed or updated from view, it is only to access values.
		return model;
	}
	
	public View getView() {
		return view;
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
	
	public void addPlant(Plant p) {
		model.addPlant(p);
	}
	
	//Event Handlers
	public EventHandler dragPlant() {
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
	

}


