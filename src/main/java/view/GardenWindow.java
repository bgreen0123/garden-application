package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import controller.Controller;
import enums.CurrentScreen;
import enums.PlantType;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Model;
import model.Plant;

public class GardenWindow extends Window{
    Controller cont;
    Model model;
	Image background;
    int budget;
    int totalLeps = 0;
    Button toMarket;
    Pane faveList;
    Pane outerPlot;
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
    Label goBackToMarket;
    Insets countBarItemSpacing;
    Button gardenNext;
    HashMap <ImageView, Plant> plantImageViews;
	HBox listbox;
	HBox marketBox;
	double favHeight = .8;
	double favWidth = .3;
	double gardenWidth;
	double gardenHeight;
	double centerWidth;
	double centerHeight;
	double herbDiameter = 1.0;
	double woodyDiameter = 1.5;
	GridPane plot;

    public GardenWindow(int width, int height, Stage stage, Controller cont, Button gardenNext) {
    	this.width = width;
        this.height = height;
        this.stage = stage;
        this.cont = cont;
        this.gardenNext = gardenNext;
        plantImageViews = new HashMap<ImageView, Plant>();
    }
    
    
    public Pane getOuterPlot() {
    	return outerPlot;
    }
    @Override
    public void draw() {
    	model = cont.getModel();
    	budget = model.getBudget();
    	//Background Image
    	System.out.println("Draw garden!");
    	Image bgimg = new Image(getClass().getResourceAsStream("/images/dirt-background-transparent.jpg"), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(bgimg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background= new Background(bg);
        
        //Entire screen is a borderpane
        BorderPane border = new BorderPane();
        border.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:white;");
        //border.setBackground(background);
        
        countBarItemSpacing = new Insets((countBarHeight-countBarItemHeight)/2, 
    			0, (countBarHeight-countBarItemHeight)/2, 50);
        
        //Budget section
        HBox budgetBox = drawBudget();
    	
    	//Lep section
    	HBox lepBox = drawLeps();
    	
        //Market Stand Button
    	goBackToMarket = new Label("Click this icon to return \n to market.");
    	goBackToMarket.setStyle("-fx-font-weight: bold;-fx-font-size:" + Integer.toString(Integer.valueOf(fontSize)) + ";-fx-text-fill:" + fontColor + ";");
    	ImageView marketiv = makeMarketButton();
    	
    	//Top bar
    	HBox countBar = drawCountBar(marketiv, budgetBox, lepBox);
    	
    	//Button to next page
    	HBox gardenNextBox = new HBox();
    	gardenNextBox.getChildren().add(gardenNext);
        gardenNextBox.setAlignment(Pos.BOTTOM_CENTER);
        
    	
        //Favorite sidebar
        VBox favs = new VBox();
    	favs.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:" + favBarColor + ";");
    	favs.setPrefWidth(width * favWidth);
    	Image heartImg = new Image(getClass().getResourceAsStream("/images/heart.png"));
        ImageView heartiv = new ImageView();
        heartiv.setImage(heartImg);
        heartiv.setPreserveRatio(true);
    	heartiv.setFitHeight(heartSize);
    	Label favLabel = new Label("Favorites");
    	favLabel.setStyle("-fx-font-weight: bold;-fx-font-size:" + Integer.toString(Integer.valueOf(fontSize)) + ";-fx-text-fill:" + fontColor + ";");
    	HBox favTitle = new HBox(heartiv, favLabel);
    	favTitle.setAlignment(Pos.CENTER);
    	favTitle.setSpacing(15);
    	favTitle.setPadding(new Insets(15, 15, 15, 15));
    	favs.getChildren().add(favTitle);
    	
    	centerWidth = width - (favWidth*width);
    	centerHeight = height - countBarHeight;
    	gardenWidth = (double)model.getWidth();
    	gardenHeight = (double) model.getHeight();
    	double growth;
    	if(gardenWidth/centerWidth > gardenHeight/centerHeight) {
    		//Fit garden width to fit the frame size
    		growth = centerWidth/gardenWidth;
    		gardenWidth = centerWidth;
    		gardenHeight = (gardenHeight*growth);
    	}
    	else {
    		//Fit garden height to fit the frame size
    		growth = centerHeight/gardenHeight;
    		gardenHeight = centerHeight;
    		gardenWidth = (gardenWidth*growth);
    	}

    	outerPlot = new Pane();
    	plot = new GridPane();
    	plot.setPrefSize(gardenWidth, gardenHeight);
    	plot.setBackground(background);
    	plot.setLayoutX((centerWidth/2) - gardenWidth/2);
    	plot.setLayoutY((centerHeight/2) - (gardenHeight/2));
    	
    	ObservableList<HBox> backingList;
    	ListView<HBox> plantList;
    	plot.setPickOnBounds(false);
    	ArrayList<HBox> plantPics = new ArrayList<HBox>();
    	ArrayList<Plant> favorited = model.getFavorites();
    	favorited.forEach(p->{
    		ImageView listiv;
    		Label n = new Label(p.toString());
    		listiv = p.getImageView();
    		HBox entry = new HBox(listiv, n);
    		listiv.setPreserveRatio(true);
        	listiv.setFitWidth(75);
			plantPics.add(entry);
    	}); 
    	backingList = FXCollections.observableArrayList(plantPics);
    	plantList = new ListView<>(backingList);
    	plantList.setPrefHeight(height * favHeight);
    	favs.getChildren().add(plantList);
    	
    	//Add the plants
    	ImageView plantiv;
    	for(Plant p : model.getPlants()) {
    		plantiv = p.getImageView();
    		plantiv.setTranslateX(p.getX());
    		plantiv.setTranslateY(p.getY());
    		plot.getChildren().add(plantiv);
        	plantiv.toFront();
    	}
    	
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
		outerPlot.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
		{
            public void handle(MouseDragEvent event)
            {	
            	//Checking for in bounds
            	if(event.getSceneX() > centerWidth/2 - gardenWidth/2 && event.getSceneX() < centerWidth/2 + gardenWidth/2 &&
            			event.getSceneY() > height - centerHeight/2 - gardenHeight/2 && 
            			event.getSceneY() < height - centerHeight/2 + gardenHeight/2) {
	            	int index = plantList.getSelectionModel().getSelectedIndex();
	            	HBox selected = plantList.getSelectionModel().getSelectedItem();
	
	            	//make a copy of selected ImageView to put in plot Pane
	            	ImageView iv1;
	            	int price;
	            	double imDiameter;
	            	if(favorited.get(index).getType() == PlantType.HERBACIOUS) {
	            		iv1 = new ImageView(new Image(getClass().getResourceAsStream("/images/plant.png")));
	            		price = 6;
	            		imDiameter = herbDiameter;
	            	} else  {
	            		iv1 = new ImageView(new Image(getClass().getResourceAsStream("/images/tree.png")));
	            		price = 20;
	            		imDiameter = woodyDiameter;
	            	}
	            	iv1.setPreserveRatio(true);
	            	iv1.setFitHeight(imDiameter*(gardenWidth/model.getWidth()));
	            	
	            	Plant p = (Plant)(favorited.get(index).clone());
	            	p.setDiameter(imDiameter);
	            	plantImageViews.put(iv1, p);
	            	
	            	cont.updateLeps(p.getLepsSupported());
	            	updateLeps(p.getLepsSupported());
	            	cont.updateBudget(price);
	            	updateBudget(price);
	            	
	            	border.setTop(drawCountBar(makeMarketButton(), drawBudget(), drawLeps()));
	
	            	
	            	//put dragged Node back into list in same place
	            	backingList.set(index, selected);
	
	            	int placeX = (int)(event.getSceneX() - plot.getLayoutX() - imDiameter*(gardenWidth/model.getWidth())/2);
	            	int placeY = (int)(event.getSceneY() - plot.getLayoutY() - imDiameter*(gardenWidth/model.getWidth()) - 40);
	            	iv1.setTranslateX(placeX);
	            	iv1.setTranslateY(placeY);
	            	
//	            	double rad = imDiameter*(gardenWidth/model.getWidth())/2;
//	            	if(placeX - rad < (centerWidth/2 - gardenWidth/2)) {
//	            		iv1.setTranslateX(iv1.getTranslateX() + placeX - (centerWidth/2 - gardenWidth/2));
//	            	}
//	            	if(placeX + rad > (centerWidth/2 + gardenWidth/2)) {
//	            		iv1.setTranslateX(iv1.getTranslateX() - ((centerWidth/2 + gardenWidth/2) - placeX));
//	            	}
//	            	if(placeY - rad < (height - centerWidth/2 - gardenWidth/2)) {
//	            		iv1.setTranslateY(iv1.getTranslateY() + placeY - (height - centerHeight/2 - gardenHeight/2));
//	            	}
//	            	if(placeY + rad > (height - centerWidth/2 + gardenWidth/2)) {
//	            		iv1.setTranslateY(iv1.getTranslateY() - ((height - centerHeight/2 + gardenWidth/2) - placeY));
//	            	}
	            	
	            	p.setX(iv1.getTranslateX());
	            	p.setY(iv1.getTranslateY());
	            	p.setImageView(iv1);
	            	cont.addPlant(p);
	
	            	iv1.setOnMousePressed(event1 -> pressed(event1));
	            	iv1.setOnMouseDragged(event2 -> drag(event2));
	            	iv1.setOnMouseReleased(event3 -> released(event3));     
	
	            	plot.getChildren().add(iv1);
	            	iv1.toFront();
	 
	            	event.consume();
            	}
            }
        });
        
		outerPlot.getChildren().add(plot);
    	IntStream.range(0, model.getWidth()).forEach(n -> {
    	    Line line = new Line(centerWidth/2 - gardenWidth/2 + n*(gardenWidth/model.getWidth()), 
    	    		centerHeight/2 - gardenHeight/2, 
    	    		centerWidth/2 - gardenWidth/2 + n*(gardenWidth/model.getWidth()), 
    	    		height - (centerHeight / 2) + (gardenHeight / 2) - (gardenHeight/model.getHeight()));
    	    outerPlot.getChildren().add(line);
    	});
    	IntStream.range(0, model.getHeight()).forEach(n -> {
    	    Line line = new Line(centerWidth/2 - gardenWidth/2, 
    	    		centerHeight/2 - gardenHeight/2 + n*(gardenHeight/model.getHeight()), 
    	    		centerWidth/2 + gardenWidth/2, 
    	    		centerHeight/2 - gardenHeight/2 + n*(gardenHeight/model.getHeight()));
    	    outerPlot.getChildren().add(line);
    	});
        border.setTop(countBar);
        border.setCenter(outerPlot);
        border.setRight(favs);
        
        gardenNext.setPrefSize(width * favWidth, height * .2);
        gardenNext.setAlignment(Pos.CENTER);
        gardenNext.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:" + countBarColor + ";");
        gardenNext.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        favs.getChildren().add(gardenNext);
        
        initializeCircles();
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
		Node n = (Node)event.getSource();
		System.out.println("Location: " + event.getSceneX()
				 + ", " + event.getSceneY());
		System.out.println("Border: " + plot.getLayoutX() + ", " + (plot.getLayoutX() + gardenWidth));
		System.out.println(centerWidth/2 - gardenWidth/2 + " ... " + centerWidth/2 + gardenWidth/2);
		double rad = plantImageViews.get(n).getDiameter()/2*gardenWidth/model.getWidth();
		if(event.getSceneX() - rad > (plot.getLayoutX()) && event.getSceneX() + rad < (plot.getLayoutX() + gardenWidth)) {
			n.setTranslateX(n.getTranslateX() + event.getX() - ((plantImageViews.get(n).getDiameter())*(gardenWidth/model.getWidth()))/2);
		}
		if(event.getSceneY() - rad > (height - centerHeight/2 - gardenHeight/2) 
				&& event.getSceneY() + rad < (height - centerHeight/2 + gardenHeight/2)) {
			n.setTranslateY(n.getTranslateY() + event.getY() - ((plantImageViews.get(n).getDiameter())*(gardenWidth/model.getWidth()))/2);
		}
		plantImageViews.get(n).setX(n.getTranslateX());
		plantImageViews.get(n).setY(n.getTranslateY());
	}    
    public void released(MouseEvent event) {
    	System.out.println("released");
    }
    
    public HBox drawLeps() {
    	Image lepImg = new Image(getClass().getResourceAsStream("/images/butterfly-png.png"));
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
    	Image dollarimg = new Image(getClass().getResourceAsStream("/images/dollar-sign.png"));
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
    	Image marketImg = new Image(getClass().getResourceAsStream("/images/stand.jpg"));
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
        marketBox = new HBox(marketiv,goBackToMarket);
        countBar.getChildren().addAll(marketBox, budgetBox, lepBox);
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
    	while(l >= 100) {
    		lepAnimation();
    		l -= 100;
    	}
    	System.out.println("updating leps " + Integer.toString(totalLeps));
    }
    public void initializeCircles() {
    	for(int i = 100; i <= totalLeps; i = i+100) {
    		lepAnimation();
    	}
    }
    public void lepAnimation() {
    	Random rand = new Random();
    	int x = rand.nextInt((int)(width / 3));
    	int y = rand.nextInt((int)(height / 2));
    	int tox = rand.nextInt((int)(width / 3));
    	int toy = rand.nextInt((int)(height / 2));
    	System.out.println(x + "," + y + "," + tox + "," + toy);
    	ImageView c = new ImageView(new Image(getClass().getResourceAsStream("/images/lepAnimation.png"), width, height, false, true));
    	c.setPreserveRatio(true);
    	c.setFitHeight(30);
    	c.setFitWidth(40);
    	c.setLayoutX(x);
    	c.setLayoutY(y);
    	
    	TranslateTransition trans = new TranslateTransition();
    	trans.setDuration(Duration.seconds(5));
    	trans.setToX(tox);
    	trans.setToY(toy);
    	trans.setAutoReverse(true);
    	trans.setCycleCount(Animation.INDEFINITE);   	
    	trans.setNode(c);
    	trans.play();
    	outerPlot.getChildren().add(c);
    }
    
    
}	