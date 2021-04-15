package view;

import java.io.File;
import java.util.ArrayList;

import controller.Controller;
import enums.CurrentScreen;
import enums.PlantType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.Plant;

public class GardenWindow extends Window{
    Controller cont;
    Model model;
	Image background;
    int budget = 500;
    int totalLeps = 0;
    Button toMarket;
    Pane faveList;
    Scene scene;
    int width, height;
    Stage stage;
    String fontSize = "18";
    String fontColor = "black";
    String favBarColor = "white";
    int heartSize = 28;
    String countBarColor = "rgb(102,201,52)";
    int countBarHeight = 50;
    int countBarItemHeight = 30;
    Label lepLabel;
    Label budgetLabel;
    Insets countBarItemSpacing;
    Button gardenNext;

    public GardenWindow(int width, int height, Stage stage, Controller cont, Button gardenNext) {
    	this.width = width;
        this.height = height;
        this.stage = stage;
        this.cont = cont;
        this.gardenNext = gardenNext;
    }
    
    @Override
    public void draw() {
    	model = cont.getModel();
    	
    	//Background Image
    	System.out.println("Draw garden!");
        Image bgimg = new Image(new File("src/main/java/images/dirt-background-transparent.jpg").toURI().toString(), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(bgimg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background= new Background(bg);
        
        //Entire screen is a borderpane
        BorderPane border = new BorderPane();
        border.setBackground(background);
        
        countBarItemSpacing = new Insets((countBarHeight-countBarItemHeight)/2, 
    			0, (countBarHeight-countBarItemHeight)/2, 50);
        
        //Budget section
        HBox budgetBox = drawBudget();
    	
    	//Lep section
    	HBox lepBox = drawLeps();
    	
        //Market Stand Button
    	ImageView marketiv = makeMarketButton();
    	
    	//Top bar
    	HBox countBar = drawCountBar(marketiv, budgetBox, lepBox);
    	
    	//Button to next page
    	HBox gardenNextBox = new HBox();
    	gardenNextBox.getChildren().add(gardenNext);
        gardenNextBox.setAlignment(Pos.BOTTOM_RIGHT);
        
    	
        //Favorite sidebar
        VBox favs = new VBox();
    	favs.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:" + favBarColor + ";");
    	favs.setPrefWidth(150);
    	Image heartImg = new Image(new File("src/main/java/images/heart.png").toURI().toString(), width, height, false, true);
        ImageView heartiv = new ImageView();
        heartiv.setImage(heartImg);
        heartiv.setPreserveRatio(true);
    	heartiv.setFitHeight(heartSize);
    	Label favLabel = new Label("Favorites");
    	favLabel.setStyle("-fx-font-weight: bold;-fx-font-size:" + Integer.toString(Integer.valueOf(fontSize)) + ";-fx-text-fill:" + fontColor + ";");
    	HBox favTitle = new HBox(heartiv, favLabel);
    	favTitle.setAlignment(Pos.CENTER);
    	favTitle.setSpacing(15);
    	favs.getChildren().add(favTitle);
    	
    	GridPane plot = new GridPane();
    	plot.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:transparent;");

    	favs.setSpacing(30);
    	
    	ObservableList<String> backingList;
    	ListView<String> plantList;
    	plot.setPickOnBounds(false);
    	ArrayList<String> plantPics = new ArrayList<String>();
    	ArrayList<Plant> favorited = model.getFavorites();
    	for(int i = 0; i < favorited.size(); i++) {
			plantPics.add(favorited.get(i).getComName());
    	}
    	backingList = FXCollections.observableArrayList(plantPics);
    	plantList = new ListView<>(backingList);
    	favs.getChildren().add(plantList);
    	
    	// Add mouse event handler for the source
		plantList.setOnDragDetected(new EventHandler <MouseEvent>()
		{
            public void handle(MouseEvent event)
            {
            	plantList.startFullDrag(); //key to javaFX special drag-n-drop handling
            	event.consume();
            }
        });
		
		// Add mouse handler for target
		plot.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
		{
            public void handle(MouseDragEvent event)
            {	
            	System.out.println("dragging released!");
            	int index = plantList.getSelectionModel().getSelectedIndex();
            	String selected = plantList.getSelectionModel().getSelectedItem();

            	//make a copy of selected ImageView to put in plot Pane
            	ImageView iv1;
            	int price;
            	if(favorited.get(index).getType() == PlantType.HERBACIOUS) {
            		iv1 = new ImageView(new Image(new File("src/main/java/images/plant.png").toURI().toString(), width, height, false, true));
            		price = 6;
            	} else  {
            		iv1 = new ImageView(new Image(new File("src/main/java/images/tree.png").toURI().toString(), width, height, false, true));
            		price = 20;
            	}
            	iv1.setPreserveRatio(true);
            	iv1.setFitHeight(100);
            	
            	cont.updateLeps(favorited.get(index).getLepsSupported());
            	updateLeps(favorited.get(index).getLepsSupported());
            	cont.updateBudget(price);
            	updateBudget(price);
            	cont.addFavPlant(favorited.get(index));
            	
            	
            	border.setTop(drawCountBar(makeMarketButton(), drawBudget(), drawLeps()));

            	
            	//put dragged Node back into list in same place
            	backingList.set(index, selected);

            	iv1.setTranslateX(event.getSceneX() - plot.getLayoutX());
            	iv1.setTranslateY(event.getSceneY() - plot.getLayoutY());

            	iv1.setOnMousePressed(event1 -> pressed(event1));
            	iv1.setOnMouseDragged(event2 -> drag(event2));
            	iv1.setOnMouseReleased(event3 -> released(event3));     

            	plot.getChildren().add(iv1);
            	iv1.toFront();
 
            	event.consume();
            }
        });
        
		
        border.setTop(countBar);
        border.setCenter(plot);
        border.setRight(favs);
        favs.getChildren().add(gardenNextBox);
        border.setRight(gardenNextBox);
		gardenNext.setPrefSize(100, 50);
        
        
        
        
        
        scene = new Scene(border, width, height);
        stage.setScene(scene);
        stage.show(); 
    }
    
    public void pressed(MouseEvent event) {
    	System.out.println("pressed");
		Node n = (Node)event.getSource();
		n.toFront();
    }
    
	public void drag(MouseEvent event) {
		//System.out.println("ic mouse");
		Node n = (Node)event.getSource();
		n.setTranslateX(n.getTranslateX() + event.getX());
		n.setTranslateY(n.getTranslateY() + event.getY());
	}    
    public void released(MouseEvent event) {
    	System.out.println("released");
    }
    
    public HBox drawLeps() {
    	Image lepImg = new Image(new File("src/main/java/images/butterfly.png").toURI().toString(), width, height, false, true);
        ImageView lepiv = new ImageView();
        lepiv.setImage(lepImg);
        lepiv.setPreserveRatio(true);
    	lepiv.setFitHeight(countBarItemHeight);
    	lepLabel = new Label("Leps Supported: " + Integer.toString(totalLeps));
    	lepLabel.setStyle("-fx-font-weight: bold;-fx-font-size:" + fontSize + ";-fx-text-fill:" + fontColor + ";");
    	HBox lepBox = new HBox();
    	HBox.setMargin(lepBox, countBarItemSpacing);
    	lepBox.setSpacing(15);
    	lepBox.setAlignment(Pos.CENTER);
    	lepBox.getChildren().addAll(lepiv, lepLabel);
    	return lepBox;
    }
    
    public HBox drawBudget() {
    	Image dollarimg = new Image(new File("src/main/java/images/dollar-sign.png").toURI().toString(), width, height, false, true);
        ImageView dollariv = new ImageView();
        dollariv.setImage(dollarimg);
        dollariv.setPreserveRatio(true);
    	dollariv.setFitHeight(countBarItemHeight);
    	budgetLabel = new Label("Budget Remaining: " + Integer.toString(budget));
    	budgetLabel.setStyle("-fx-font-weight: bold;-fx-font-size:" + fontSize + ";-fx-text-fill:" + fontColor + ";");
    	HBox budgetBox = new HBox();
    	HBox.setMargin(budgetBox, countBarItemSpacing);
    	budgetBox.setSpacing(15);
    	budgetBox.setAlignment(Pos.CENTER);
    	budgetBox.getChildren().addAll(dollariv, budgetLabel);
    	return budgetBox;
    }
    
    public ImageView makeMarketButton() {
    	Image marketImg = new Image(new File("src/main/java/images/stand.jpg").toURI().toString(), width, height, false, true);
        ImageView marketiv = new ImageView();
        marketiv.setImage(marketImg);
        marketiv.setPreserveRatio(true);
    	marketiv.setFitHeight(countBarHeight);
    	Insets marketItemSpacing = new Insets((countBarHeight-countBarItemHeight)/2, 
    			0, (countBarHeight-countBarItemHeight)/2, 25);
    	HBox.setMargin(marketiv, marketItemSpacing);
    	marketiv.setOnMouseReleased(new EventHandler<MouseEvent>(){
    		public void handle(MouseEvent e) {
    			cont.goToPage(CurrentScreen.MARKET_H);
    		}
    	});
    	return marketiv;
    }
    
    public HBox drawCountBar(ImageView marketiv, HBox budgetBox, HBox lepBox) {
    	HBox countBar = new HBox();
    	countBar.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:" + countBarColor + ";");
        countBar.setPrefHeight(countBarHeight);
        countBar.getChildren().addAll(marketiv, budgetBox, lepBox);
        return countBar;
    }
    
    public void updateBudget(int b) {
    	budget -= b;
    	/*
    	budgetBox.getChildren().clear();
    	budgetBox.getChildren().addAll(iv, new Label("Budget Remaining: " + Integer.toString(budget)));
    	*/
    	System.out.println("updating budget" + Integer.toString(budget));
    }
    
    public void updateLeps(int l) {
    	totalLeps += l;
    	/*
    	lepBox.getChildren().clear();
    	lepBox.getChildren().addAll(iv, new Label("Leps Supported: " + Integer.toString(totalLeps)));
    	*/
    	System.out.println("updating leps" + Integer.toString(totalLeps));
    }
    
    
}	



