package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import enums.CurrentScreen;
import enums.Moisture;
import enums.Soil;
import enums.Sun;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.DataThread;
import model.Model;
import model.Plant;
import view.MarketItem;
import view.View;
import view.MarketWindowHerbaceous;
import view.MarketWindowWoody;

/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */

public class Controller{
	Model model;
	View view;
	MarketWindowHerbaceous herb;
	MarketWindowWoody wood;
	
	//Buttons
	Button welcomeNext;
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
	Button endToGarden;
	Button restart;
	
	
	//Favorite buttons
	ArrayList<MarketItem> woodyMarket = new ArrayList<MarketItem>();
	ArrayList<MarketItem> herbaceousMarket = new ArrayList<MarketItem>(); 
	
	//Drop down menus
	ChoiceBox<Sun> sun;
	ChoiceBox<Soil> soil;
	ChoiceBox<Moisture> moisture;
	
	//Text Boxes
	TextField budget;
	TextField saveBox;
	TextField loadBox;
	TextField widthBox;
	TextField heightBox;
	
	//User answers
  	Sun sunChoice;
  	Soil soilChoice;
  	Moisture moistureChoice;
  	
  	//Load file
  	String fileName;
  	
  	/**
	 * Main controller method that creates all necessary buttons, text boxes, and sliders.
	 * Contains logic for the buttons. 
	 * Creates drop down menus for the conditions screen
	 * 
	 * @param view, an instance of the view class
	 */
  	
	public Controller(View view){
		this.view = view;
		model = new Model();
		
		//Buttons
		welcomeNext = new Button("New Garden");
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
		endToGarden = new Button("Back to Garden");
		restart = new Button("Restart");
		
		//Making text box
		budget = new TextField();
		saveBox = new TextField();
		widthBox = new TextField();
		heightBox = new TextField();
		
		//Actions
		welcomeNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.CONDITIONS); // change back to CONDITIONS
			budget.setText("");
			widthBox.setText("");
			heightBox.setText("");
			woodyMarket.forEach(m -> {
				m.setDefault();
			});
			herbaceousMarket.forEach(m -> {
				m.setDefault();
			});
			loading(CurrentScreen.CONDITIONS, true);
		});
		
		backToConditions.setOnAction(e -> view.changeScreen(CurrentScreen.CONDITIONS));
		
		conditionsNext.setOnAction(e -> {
			setChoice(sun,soil,moisture);
			String input = budget.getText();
			String widthInput = widthBox.getText();
			String heightInput = heightBox.getText();
			int budget;
			int width;
			int height;
			if(input == "") {
				budgetError();
				return;
			}
			if((widthInput == "" || heightInput == "")) {
				dimenstionError();
				return;
			}
			try {
				budget = Integer.parseInt(input);
				
			}catch (Exception exc){
				budgetError();
				return;
			}
			try {
				width = Integer.parseInt(widthInput);
				height = Integer.parseInt(heightInput);
				if((width > 50 || height > 50) || (width < 5 || height < 5)) {
					dimenstionError();
					return;
				}
			}catch (Exception exc){
				dimenstionError();
				return;
			}
			if(budget<0) {
				budgetError();
				return;
			}
			
			model.updateBudget(budget);
			model.setWidth(width);
			model.setHeight(height);
			view.changeScreen(CurrentScreen.MARKET_H);
		});
		
		gardenNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.END);
			System.out.println("End Window");
		});
		
		woody.setOnAction(e -> view.changeScreen(CurrentScreen.MARKET_W));
		herbaceous.setOnAction(e -> view.changeScreen(CurrentScreen.MARKET_H));
		marketNext.setOnAction(e -> loading(CurrentScreen.GARDEN, false));
		applyConditions.setOnAction(e -> view.setFilter());
		viewFavs.setOnAction(e -> view.changeScreen(CurrentScreen.FAVS));
		backToMarket.setOnAction(e -> view.changeScreen(CurrentScreen.MARKET_H));
		toWelcome.setOnAction(e -> view.changeScreen(CurrentScreen.WELCOME));
		endToGarden.setOnAction(e -> view.changeScreen(CurrentScreen.GARDEN));
		save.setOnAction(e -> {
			try {
				saveSuccess();
				FileOutputStream fos = new FileOutputStream("garden.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(model);
				System.out.println("Saved");
				oos.flush();
				oos.close();
			}
			catch(Exception exc) {
				System.out.println("Couldn't save file");
				exc.printStackTrace();
				saveError();
				return;
			}
		});
		load.setOnAction(e -> {
			try {
				if(confirmLoad()==ButtonType.CANCEL) {
					return;
				}
				FileInputStream fis = new FileInputStream("garden.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Model nm = (Model)ois.readObject();
				model = new Model(nm.getWidth(),nm.getHeight(),nm.getData(),nm.getNumLeps(),nm.getPlants(),nm.getFavorites(),nm.getSun(),nm.getSoil(),nm.getMoisture(),
						nm.getBudget(), nm.getWarned());
				
				System.out.println("Loaded");
				ois.close();
			}
			catch(Exception exc) {
				System.out.println("Couldn't load file");
				loadError();
				return;
			}
			//after loading go to garden
			herbaceousMarket.clear();
			woodyMarket.clear();
			loading(CurrentScreen.GARDEN, true);
		});
		
		restart.setOnAction(e -> {
			//herb = new MarketWindowHerbaceous();
			//woody = new MarketWindowWoody();
			model = new Model();
			view.getGardenWindow().newHash();
			sun.setValue(Sun.SUN);
			soil.setValue(Soil.SOIL);
			moisture.setValue(Moisture.MOISTURE);
			view.defaultFilter();
			woodyMarket.clear();
			herbaceousMarket.clear();
			view.changeScreen(CurrentScreen.WELCOME);
		});
		
		
		//Creating the drop down menu
		sun = new ChoiceBox<>();
		soil = new ChoiceBox<>();
		moisture = new ChoiceBox<>();
		//Adding choices into the drop menu
		sun.getItems().addAll(Sun.SUN,Sun.FULLSUN,Sun.PARTSUN,Sun.SHADE);
		soil.getItems().addAll(Soil.SOIL,Soil.CLAY,Soil.DIRT,Soil.ROCK);
		moisture.getItems().addAll(Moisture.MOISTURE,Moisture.WET,Moisture.MEDIUM,Moisture.DRY);
		
		//Starting value that the user sees
		sun.setValue(Sun.SUN);
		soil.setValue(Soil.SOIL);
		moisture.setValue(Moisture.MOISTURE);

	}
	
  	/**
	 * Loading class that contains data thread. Shows loading screen while data is loading. 
	 * 
	 * @param sc, the current screen that the user is viewing
	 * @param applyMI, checks to see if loading screen is necessary
	 */
	
	
	private void loading(CurrentScreen sc, Boolean applyMI) {
		DataThread dt = new DataThread(model);
		dt.start();
		if(dt.isAlive()) {
			view.changeScreen(CurrentScreen.LOADINGSCREEN);
			Thread endLoad = new Thread(new Runnable() {

				@Override
				public void run() {
					while(dt.isAlive());
					Platform.runLater(new Runnable() {
			            @Override
			            public void run() {
			            	if(applyMI) {
				            	//Set Market Items
				            	model.getData().getWoody().forEach(p ->{
				    				Button fav = new Button();
				    				MarketItem m = new MarketItem(p, fav);
				    				fav.setOnAction(ev -> {
				    					if(m.getFavorited()) {
				    						model.getFavorites().remove(p);
				    					}else {
				    						model.addFavoritePlant(p);
				    					}
				    					m.setButton();
				    				});
				    				m.setDefault();
				    				woodyMarket.add(m);
				    			});
				    			
				    			model.getData().getHerbacious().forEach(p ->{
				    				Button fav = new Button();
				    				MarketItem m = new MarketItem(p, fav);
				    				fav.setOnAction(ev -> {
				    					if(m.getFavorited()) {
				    						model.getFavorites().remove(p);
				    					}else {
				    						model.addFavoritePlant(p);
				    					}
				    					m.setButton();
				    				});
				    				m.setDefault();
				    				herbaceousMarket.add(m);
				    			});
			            	}
			              view.changeScreen(sc);
			            }
			          });
				}
				
			});
			endLoad.start();
		}else {
			if(applyMI) {
            	//Set Market Items
            	model.getData().getWoody().forEach(p ->{
    				Button fav = new Button();
    				MarketItem m = new MarketItem(p, fav);
    				fav.setOnAction(ev -> {
    					if(m.getFavorited()) {
    						model.getFavorites().remove(p);
    					}else {
    						model.addFavoritePlant(p);
    					}
    					m.setButton();
    				});
    				m.setDefault();
    				woodyMarket.add(m);
    			});
    			
    			model.getData().getHerbacious().forEach(p ->{
    				Button fav = new Button();
    				MarketItem m = new MarketItem(p, fav);
    				fav.setOnAction(ev -> {
    					if(m.getFavorited()) {
    						model.getFavorites().remove(p);
    					}else {
    						model.addFavoritePlant(p);
    					}
    					m.setButton();
    				});
    				m.setDefault();
    				herbaceousMarket.add(m);
    			});
        	}
			view.changeScreen(sc);
		}
	}
	
	//Error Handling
	
  	/**
	 * Error that pops up if the inputed budget is invalid.
	 * 
	 */
	private void budgetError() {
		Alert budgetError = new Alert(AlertType.ERROR);
		budgetError.setHeaderText("Invalid budget");
		budgetError.setContentText("Budget must be an integer, cannot be negative and cannot be\nempty.");
		budgetError.showAndWait();
	}
	/**
	 * Error that pops up if the inputed budget is invalid.
	 * 
	 */
	private void dimenstionError() {
		Alert dimenstionError = new Alert(AlertType.ERROR);
		dimenstionError.setHeaderText("Invalid width or height");
		dimenstionError.setContentText("width and height must be an integer between 5 and 50");
		dimenstionError.showAndWait();
	}
	/**
	 * Error that pops up if user is unable to save the Garden.
	 * 
	 */
	private void saveError() {
		Alert saveError = new Alert(AlertType.ERROR);
		saveError.setHeaderText("Could not save garden");
		saveError.setContentText("The garden could not be saved at this time. Please try again later.");
		saveError.showAndWait();
	}
	/**
	 * Error that pops up if the garden cannot be loaded.
	 * 
	 */
	private void loadError() {
		Alert saveError = new Alert(AlertType.ERROR);
		saveError.setHeaderText("Could not load garden");
		saveError.setContentText("The garden could not be loaded at this time. Please try again later.");
		saveError.showAndWait();
	}
	/**
	 * Warning that shows up if user budget goes below 0.
	 * 
	 */
	private void warn() {
		Alert warnUser = new Alert(AlertType.WARNING);
		warnUser.setHeaderText("Budget below 0");
		warnUser.setContentText("Oh no! You have gone over budget. Select finish to finish, or keep placing plants and go farther over your budget.");
		warnUser.showAndWait();
	}
	/**
	 * Pop up that lets the user know that the saving was successful.
	 * 
	 */
	private void saveSuccess() {
		Alert confirmSave = new Alert(AlertType.INFORMATION);
		confirmSave.setHeaderText("Saving your garden");
		confirmSave.setContentText("Your garden has been saved successfully");
		confirmSave.showAndWait();
	}
	/**
	 * Message that asks to confirm if the user wants to load their garden.
	 * 
	 */
	private ButtonType confirmLoad() {
		Alert confirmSave = new Alert(AlertType.CONFIRMATION);
		confirmSave.setHeaderText("Loading Garden");
		confirmSave.setContentText("You are about to load a garden, would you like to continue?");
		confirmSave.showAndWait();
		return confirmSave.getResult();
	}

	//Getters
	
	/**
	 * gets the restart button
	 * 
	 * @return restart, the restart button
	 */
	public Button getRestart() {
		return restart;
	}
	
	/**
	 * gets the button that allows the user to go back to the Garden.
	 * 
	 * @return endToGarden, back to Garden button.
	 */
	public Button getEndToGarden() {
		return endToGarden;
	}
	
	/**
	 * gets the array list of plants.
	 * 
	 * @return plants list from Model
	 */
	public ArrayList<Plant> getPlantsList(){
		return model.getPlants();
	}
	
	/**
	 * gets the array list of plants that user favorited.
	 * 
	 * @return favorite list from Model
	 */
	public ArrayList<Plant> getFavoriteList(){
		return model.getFavorites();
	}
	
	/**
	 * gets the button that allows the user to go back to the Conditions screen.
	 * 
	 * @return backtoConditions, back to conditions button
	 */
	public Button getBackToConditions() {
		return backToConditions;
	}
	
	/**
	 * gets the button that allows the user to go the market.
	 * 
	 * @return MarketNext, market button
	 */
	public Button getMarketNext() {
		return marketNext;
	}
	
	/**
	 * gets the button that allows the user show herbaceous plants
	 * 
	 * @return herbaceous, button to show herbaceous plants
	 */
	public Button getHerbaceous() {
		return herbaceous;
	}
	
	/**
	 * gets the button that allows the user show woody plants
	 * 
	 * @return woody, button to show woody plants
	 */
	public Button getWoody() {
		return woody;
	}
	
	/**
	 * gets the button that allows the user apply conditions
	 * 
	 * @return apply conditions button.
	 */
	public Button getApplyConditions() {
		return applyConditions;
	}
	
	/**
	 * gets the button that allows the view favorited plants
	 * 
	 * @return viewFavs, button that shows favorites
	 */
	public Button getViewFavs() {
		return viewFavs;
	}
	
	/**
	 * gets the button that allows the user to go back to market.
	 * 
	 * @return the back to market button
	 */
	public Button getBackToMarket() {
		return backToMarket;
	}
	
	/**
	 * gets the array list of herbaceous plants for the market
	 * 
	 * @return herbaceous market array list.
	 */
	public ArrayList<MarketItem> getHerbaceousMarket(){
		return herbaceousMarket;
	}
	
	/**
	 * gets the array list of woody plants for the market
	 * 
	 * @return woody market array list.
	 */

	public ArrayList<MarketItem> getWoodyMarket(){
		return woodyMarket;
	}
	
	/**
	 * gets the array list of herbaceous plants for the market
	 * 
	 * @return herbaceous market array list.
	 */
	public Button getWelcomeButton(){
		return welcomeNext;
	}

	/**
	 * gets the button that lets the user go to the conditions page.
	 * 
	 * @return conditions button.
	 */
	public Button getConditionsButton(){
		return conditionsNext;
	}
	
	/**
	 * gets the button that lets the user go to the end summary screen.
	 * 
	 * @return end button
	 */
	public Button getEndButton() {
		return gardenNext;
	}
	
	/**
	 * gets the button that lets the user go back to the welcome screen
	 * 
	 * @return toWelcome button
	 */
	public Button getToWelcomeButton(){
		return toWelcome;
	}
	
	/**
	 * gets the button that lets the user load their saved garden
	 * 
	 * @return load button
	 */
	public Button getToLoadButton() {
		return toLoad;
	}
	
	/**
	 * gets the button that lets the user save the garden
	 * 
	 * @return save button
	 */
	public Button getSaveButton() {
		return save;
	}
	
	/**
	 * gets the button that lets the user load their saved garden
	 * 
	 * @return load button
	 */
	public Button getLoadButton() {
		return load;
	}
	
	/**
	 * gets the sun choices for the sun drop down
	 * 
	 * @return sun choice box with the sun conditions
	 */
	public ChoiceBox<Sun> getSun() {
		return sun;
	}

	/**
	 * gets the soil choices for the soil drop down
	 * 
	 * @return soil choice box with the soil conditions
	 */
	public ChoiceBox<Soil> getSoil() {
		return soil;
	}
	
	/**
	 * gets the moisture choices for the moisture drop down
	 * 
	 * @return moisture choice box with the moisture conditions
	 */
	public ChoiceBox<Moisture> getMoisture() {
		return moisture;
	}
	
	/**
	 * gets the text field that lets the user enter budget
	 * 
	 * @return budget text field
	 */
	public TextField getBudget() {
		return budget;
	}
	
	/**
	 * gets the text field for saving
	 * 
	 * @return save text field
	 */
	public TextField getSave() {
		return saveBox;
	}
	
	/**
	 * gets the text field for width
	 * 
	 * @return width text field
	 */
	public TextField getWidth() {
		return widthBox;
	}
	
	/**
	 * gets the text field for height
	 * 
	 * @return heightBox,  height text field
	 */
	public TextField getHeight() {
		return heightBox;
	}
	
	/**
	 * gets the filename
	 * 
	 * @return filename
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * gets the model to access fields in model
	 * 
	 * @return model
	 */
	public Model getModel() {
		//This method is used for view to access certain fields such as budget and lep count
		//No fields should be changed or updated from view, it is only to access values.
		return model;
	}

	/**
	 * gets the model to access fields in view
	 * 
	 * @return view
	 */
	public View getView() {
		return view;
	}
	
	/**
	 * gets the soil types
	 * 
	 * @return Soil type
	 */
	public Soil getSoilType() {
		return soil.getValue();
	}
	
	//Setter to set the users choice of conditions
	
	/**
	 * sets the choices for the drop downs
	 * 
	 * @param sun, choicebox containing sun conditions
	 * @param soil, choicebox containing soil conditions
	 * @param moisture, choicebox containing moisture conditions
	 */
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
	
	/**
	 * calls the changeScreen method from view. 
	 * 
	 * @param cs, the current screen that the user is on.
	 */
	public void goToPage(CurrentScreen cs) {
		view.changeScreen(cs);
	}
	
	/**
	 * calls update lep count from model and updates the leps accordingly
	 * 
	 * @param leps, the amount of leps each plant supports.
	 */
	public void updateLeps(int leps) {
		model.updateLepCount(leps);
	}
	
	/**
	 * calls getBudget and updates it accordingly. Also shows the warning if budget goes below 0.
	 * 
	 * @param cost, the cost of each plant.
	 */
	public void updateBudget(int cost) {
		if(model.getBudget() - cost < 0 && !model.getWarned()) {
			warn();
			model.setWarned();
		}
		model.updateBudget(model.getBudget() - cost);
	}
	
	/**
	 * calls getBudget and updates it accordingly if the user deletes a plant from their garden.
	 * 
	 * @param cost, the cost of each plant.
	 */
	public void removalBudget(int cost) {
		if (model.getBudget() + cost > 0 && model.getWarned()){
			model.setWarned();
		}
		model.updateBudget(model.getBudget() + cost);
	}
	
	/**
	 * adds plants that are in the garden to the arrayList of plants.
	 * 
	 * @param p, plant that the user has planted
	 */
	public void addPlant(Plant p) {
		model.addPlant(p);
	}
	
	/**
	 * remove plants that are no longer in the garden from the arrayList of plants.
	 * 
	 * @param p, plant that the user has planted
	 */
	public void removePlant(Plant p) {
		model.removePlant(p);
	}
	
	/**
	 * contains logic for dragging mouse event
	 * 
	 * @return event handler for dragging
	 */
	public EventHandler dragPlant() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	/**
	 * event handler the lets the user enter their budget
	 * 
	 * @return mouse event handler
	 */
	public EventHandler enterBudget() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	/**
	 * event handler the lets the view other plant type.
	 * 
	 * @return action event handler that switches plant types.
	 */
	public EventHandler toOtherPlantType() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
		};
	}
	
	/**
	 * event handler the lets the user favorite a plant.
	 * 
	 * @return action event handler for favoriting plants.
	 */
	public EventHandler favoritePlant() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
		};
	}
}


