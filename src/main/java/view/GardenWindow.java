package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import controller.Controller;
import enums.CurrentScreen;
import enums.PlantType;
import enums.Soil;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Model;
import model.Plant;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public class GardenWindow extends Window{
    Controller cont;
    Model model;
	Image background;
    int budget;
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
    String countBarColor = "rgb(14, 181, 51)"; //"rgb(102,201,52)";
    String imageBack;
    int countBarHeight = 50;
    int countBarItemHeight = 30;
    Label lepLabel;
    Label budgetLabel;
    Label goBackToMarket;
    Insets countBarItemSpacing;
    Button gardenNext;
    HashMap <Circle, Plant> plantCircles;
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
	
	/**
	 * garden window constructor
	 * 
	 * @param width, screen width
	 * @param height, screen height
	 * @param stage, garden window stage
	 * @param cont, controller
	 * @param gardenNext, button that goes to the end screen 
	 */
    public GardenWindow(int width, int height, Stage stage, Controller cont, Button gardenNext) {
    	this.width = width;
        this.height = height;
        this.stage = stage;
        this.cont = cont;
        this.gardenNext = gardenNext;
        plantCircles = new HashMap<Circle, Plant>();
    }
    
    /**
     * creates a new hashmap of plant circles
     */
    public void newHash() {
    	plantCircles = new HashMap<Circle, Plant>();
    }
    
    /**
     * gets the outerplot (garden section)
     * @return the garden pane section of the window
     */
    public Pane getOuterPlot() {
    	return outerPlot;
    }
    
    /**
     * contains logic of the garden window. 
     * displays the top bar with the market button and displays remaining budget and leps supported
     * creates a Pane for the garden that lets the users drag and drop plants
     * has a favorites sidebar containing the favorite list of plants, user can drag and drop these plants onto the outerpane
     */
    @Override
    public void draw() {
    	model = cont.getModel();
    	budget = model.getBudget();
    	model.getFavorites().forEach(p -> {
    		if(p.getImageView() == null) {
    			System.out.println("Image no load");
    			p.makeImageView();
    		}
    	});
    	//Background Image
    	System.out.println("Draw garden!: " + width + ", " + height);
    	if(cont.getSoilType() == Soil.CLAY) {
    		imageBack = "/images/claySoil.png";
    	}
    	else if(cont.getSoilType() == Soil.ROCK) {
    		imageBack = "/images/rockySoil.png";
    	}
    	else {
    		imageBack = "/images/dirt-background-transparent.jpg";
    	}
    	Image bgimg = new Image(getClass().getResourceAsStream(imageBack), width, height, false, true);
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
    	centerHeight = height - countBarHeight - 20;
    	gardenWidth = (double)model.getWidth();
    	gardenHeight = (double) model.getHeight();
    	double growth;
    	if(gardenWidth/centerWidth > gardenHeight/centerHeight) {
    		//Fit garden width to fit the frame size
    		growth = (centerWidth/gardenWidth);
    		gardenWidth = gardenWidth*growth;
    		gardenHeight = (gardenHeight*growth);
    	}
    	else {
    		//Fit garden height to fit the frame size
    		growth = (centerHeight/gardenHeight);
    		gardenHeight = gardenHeight*growth;
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
    	Circle circ;
    	for(Plant p : model.getPlants()) {
    		double imDiameter;
    		switch(p.getSpread()) {
	    		case SMALL:
	    			imDiameter = .75;
	    			break;
	    		case MEDIUM:
	    			imDiameter = 1;
	    			break;
	    		case LARGE:
	    			imDiameter = 1.25;
	    			break;
	    		case XL:
	    			imDiameter = 1.5;
	    			break;
	    		default:
	    			imDiameter = .75;
	    			break;
    		}
    		//updateLeps(p.getLepsSupported());
    		circ = p.getCircle();
        	circ.setRadius((imDiameter/2)*(gardenWidth/model.getWidth()));
    		circ.setCenterX(p.getX());
    		circ.setTranslateX(p.getX());
    		circ.setCenterY(p.getY());
    		circ.setTranslateY(p.getY());
    		circ.setOnMousePressed(event1 -> pressed(event1));
        	circ.setOnMouseDragged(event2 -> drag(event2));
        	circ.setOnMouseReleased(event3 -> released(event3));
        	plantCircles.put(circ, p);
        	if(!plot.getChildren().contains(circ)) {
        		plot.getChildren().add(circ);
        	}
        
        	circ.toFront();
        	Tooltip hoverOver = new Tooltip(p.getComName());
        	hoverOver.setText(p.toString() + "\n" + p.getDetails());
        	Tooltip.install(circ, hoverOver);     
        	ImageView newImg = new ImageView(p.getIm());
        	newImg.setPreserveRatio(true);
        	newImg.setFitWidth(100);
        	hoverOver.setGraphic(newImg);
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
	            	int price;
	            	double imDiameter = 0;
	            	if(favorited.get(index).getType() == PlantType.HERBACIOUS) {
	            		price = 6;
	            	} else  {
	            		price = 20;
	            	}
	            	
            		switch(favorited.get(index).getSpread()) {
	            		case SMALL:
	            			imDiameter = .75;
	            			break;
	            		case MEDIUM:
	            			imDiameter = 1;
	            			break;
	            		case LARGE:
	            			imDiameter = 1.25;
	            			break;
	            		case XL:
	            			imDiameter = 1.5;
	            			break;
	            	}
            		double rad = (imDiameter/2)*gardenWidth/model.getWidth();
            		boolean intersect = false;
            		int placeX = (int)(event.getSceneX() - plot.getLayoutX() - imDiameter*(gardenWidth/model.getWidth())/2);
	            	int placeY = (int)(event.getSceneY() - countBarHeight - 45 - (centerHeight - gardenHeight)/2);
            		for(Map.Entry<Circle, Plant> entry : plantCircles.entrySet()) {
                		Circle e = (Circle) entry.getKey();
        	    		double distSq = (placeX - e.getCenterX()) * (placeX - e.getCenterX()) + 
        	    				(placeY - e.getCenterY()) * (placeY - e.getCenterY());
        	    	    double radSumSq = (rad + e.getRadius()) * (rad + e.getRadius());
        	    	    if(distSq < radSumSq) {
        	    	    	System.out.println("They intersect...");
        	    	    	intersect = true;
        	    	    }
            		}
            		
            		if(!intersect) {
            			Plant p = favorited.get(index).clone();
		            	p.setX(placeX);
		            	p.setY(placeY);
		            	p.setDiameter(imDiameter);
		            	
		            	Circle circ1 = new Circle();
		            	circ1.setRadius(rad);
		            	circ1.setCenterX(placeX);
		            	circ1.setTranslateX(placeX);
		            	circ1.setCenterY(placeY);
		            	circ1.setTranslateY(placeY);
		            	circ1.setFill(new ImagePattern(p.getIm()));
		            	p.setCircle(circ1);
		            	
		            	plantCircles.put(circ1, p);
		            	
		            	//Hover over
		            	Tooltip hoverOver = new Tooltip(p.getComName());
		            	hoverOver.setText(p.toString() + "\n" + p.getDetails());
		            	Tooltip.install(circ1, hoverOver);
		            	ImageView newImg = new ImageView(p.getIm());
		            	newImg.setPreserveRatio(true);
		            	newImg.setFitWidth(100);
		            	hoverOver.setGraphic(newImg);
		            
		            	updateLeps(p.getLepsSupported());
		            	cont.updateBudget(price);
		            	updateBudget(price);
		            	
		            	border.setTop(drawCountBar(makeMarketButton(), drawBudget(), drawLeps()));
		
		            	
		            	//put dragged Node back into list in same place
		            	backingList.set(index, selected);
		            	
		            	p.setX(circ1.getTranslateX());
		            	p.setY(circ1.getTranslateY());
		            	cont.addPlant(p);
		
		            	circ1.setOnMousePressed(event1 -> pressed(event1));
		            	circ1.setOnMouseDragged(event2 -> drag(event2));
		            	circ1.setOnMouseReleased(event3 -> released(event3));  
		            	circ1.setOnMouseClicked(new EventHandler<MouseEvent>(){
		                    public void handle(MouseEvent event) {
		                        MouseButton button = event.getButton();
		                        if(button==MouseButton.SECONDARY){
		                        	plot.getChildren().remove(circ1);
		                        	cont.removePlant(plantCircles.get(circ1));
		                        	plantCircles.remove(circ1);
		                        	deletionBudget(price);
		                        	cont.removalBudget(price);
		                        	deletionLeps(p.getLepsSupported());
		                        	border.setTop(drawCountBar(makeMarketButton(), drawBudget(), drawLeps()));
		                        }
		                    }
		                });
		
		            	plot.getChildren().add(circ1);
		            	circ1.toFront();
		 
		            	event.consume();
            		}
            	}
            }
        });
        
		outerPlot.getChildren().add(plot);
    	IntStream.range(0, model.getWidth()).forEach(n -> {
    	    Line line = new Line(centerWidth/2 - gardenWidth/2 + n*(gardenWidth/model.getWidth()), 
    	    		centerHeight/2 - gardenHeight/2, 
    	    		centerWidth/2 - gardenWidth/2 + n*(gardenWidth/model.getWidth()), 
    	    		(centerHeight / 2) + (gardenHeight / 2));
    	    //height - (centerHeight / 2) + (gardenHeight / 2) - (gardenHeight/model.getHeight())
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
    
    /**
     * mouse event to check if mouse pressed onto a plant
     * @param event,  mouse event passed in
     */
    public void pressed(MouseEvent event) {
    	System.out.println("pressed");
		Node n = (Node)event.getSource();
		n.toFront();
    }
    
    /**
     * mouse event for dragging
     * @param event, mouse event passed in
     */
	public void drag(MouseEvent event) {
		Circle n = (Circle)event.getSource();
		boolean intersect = false;
		double prevX = n.getTranslateX();
		double prevY = n.getTranslateY();
		double rad = plantCircles.get(n).getDiameter()/2*gardenWidth/model.getWidth();
		System.out.println("plot getLayoutY: " + plot.getLayoutY());
		System.out.println("outer getLayoutY: " + outerPlot.getLayoutY());
		System.out.println("location: " + (event.getSceneY() - rad));
		if(event.getSceneX() - rad > (plot.getLayoutX()) && event.getSceneX() + rad < (plot.getLayoutX() + gardenWidth)) {
			n.setTranslateX(event.getSceneX() - plot.getLayoutX() - rad);
			n.setCenterX(n.getTranslateX());
		}
		if(event.getSceneY() - rad > (outerPlot.getLayoutY() + plot.getLayoutY()) 
				&& event.getSceneY() + rad < (height - centerHeight/2 + gardenHeight/2)) {
			n.setTranslateY(event.getSceneY() - countBarHeight - 45 - (centerHeight-gardenHeight)/2);
			n.setCenterY(n.getTranslateY());
		}

		plantCircles.get(n).setX(n.getTranslateX());
		plantCircles.get(n).setY(n.getTranslateY());
		for(Map.Entry<Circle, Plant> entry : plantCircles.entrySet()) {
    		Circle e = (Circle) entry.getKey();
    		if(e != n) {
	    		double distSq = (n.getCenterX() - e.getCenterX()) * (n.getCenterX() - e.getCenterX()) + 
	    				(n.getCenterY() - e.getCenterY()) * (n.getCenterY() - e.getCenterY());
	    	    double radSumSq = (n.getRadius() + e.getRadius()) * (n.getRadius() + e.getRadius());
	    	    if(distSq < radSumSq) {
	    	    	System.out.println("They intersect!");
	    	    	intersect = true;
	    	    }
    		}
		}
    	if(intersect) {
			n.setTranslateX(prevX);
			n.setCenterX(n.getTranslateX());
			n.setTranslateY(prevY);
			n.setCenterY(n.getTranslateY());
			plantCircles.get(n).setX(n.getTranslateX());
			plantCircles.get(n).setY(n.getTranslateY());
    	}
	}    
	
	/**
	 * moust event for released
	 * @param event, mouse event passed in
	 */
    public void released(MouseEvent event) {
    	System.out.println("released");
    }
    /**
     * displays leps supported and has an image of a butterfly, updates as plants are plants
     * 
     * @return an HBox that leps supported information
     */
    public HBox drawLeps() {
    	Image lepImg = new Image(getClass().getResourceAsStream("/images/butterfly-png.png"));
        ImageView lepiv = new ImageView();
        lepiv.setImage(lepImg);
        lepiv.setPreserveRatio(true);
    	lepiv.setFitHeight(countBarItemHeight);
    	lepLabel = new Label("Leps Supported: " + Integer.toString(cont.getModel().getNumLeps()));
    	lepLabel.setStyle("-fx-font-weight: bold;-fx-font-size:" + fontSize + ";-fx-text-fill:" + fontColor + ";");
    	HBox lepBox = new HBox();
    	HBox.setMargin(lepBox, countBarItemSpacing);
    	lepBox.setSpacing(15);
    	lepBox.setAlignment(Pos.CENTER);
    	lepBox.getChildren().addAll(lepiv, lepLabel);
    	return lepBox;
    }
    
    /**
     * displays budget remaining and has an image of a dollar, updates as plants are planted
     * 
     * @return an Hbox that shows budget remaining information
     */
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
    
    /**
     * makes the market Button
     * @return a market stall image that acts as a button
     */
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
    
    /**
     * count bar at the top contains budget, leps, and market button
     * 
     * @param marketiv, market button to go to market
     * @param budgetBox, HBox with dollar image and updating budget remaining
     * @param lepBox, HBox with butterfly image and updating lepcount remaining
     * @return HBox with all components
     */
    public HBox drawCountBar(ImageView marketiv, HBox budgetBox, HBox lepBox) {
    	HBox countBar = new HBox();
    	countBar.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:" + countBarColor + ";");
        countBar.setPrefHeight(countBarHeight);
        marketBox = new HBox(marketiv,goBackToMarket);
        countBar.getChildren().addAll(marketBox, budgetBox, lepBox);
        return countBar;
    }
    /**
     * updates the budget remaining after adding a plant
     * @param b, cost of plant
     */
    public void updateBudget(int b) {
    	budget -= b;
    	/*
    	budgetBox.getChildren().clear();
    	budgetBox.getChildren().addAll(iv, new Label("Budget Remaining: " + Integer.toString(budget)));
    	*/
    	System.out.println("updating budget" + Integer.toString(budget));
    }
    
    /**
     * updates budget remaining after deleting plant from garden
     * @param b, cost of plant
     */
    public void deletionBudget(int b) {
    	budget += b;
    	//cont.getModel().updateBudget(b);
    	System.out.println("updating budget" + Integer.toString(budget));
    }
    
    /**
     * updates the leps supported after adding a plant
     * @param l, amount of leps supported
     */
    public void updateLeps(int l) {
    	if(l < 100) {
    		if((cont.getModel().getNumLeps() % 100) + l >= 100) {
    			lepAnimation();
    		}
    	}
    	cont.getModel().updateLepCount(l);
    	while(l >= 100) {
    		lepAnimation();
    		l -= 100;
    	}
    	System.out.println("updating leps " + Integer.toString(cont.getModel().getNumLeps()));
    }
    
    /**
     * updates the leps supported after removing a plant from garden
     * @param l, amount of leps supported
     */
    public void deletionLeps(int l) {
    	cont.getModel().removeLepCOunt(l);
    }
    
    /**
     * starts lep animation for each 100 leps supported
     */
    public void initializeCircles() {
    	for(int i = 100; i <= cont.getModel().getNumLeps(); i = i+100) {
    		lepAnimation();
    	}
    }
    /**
     * lep animation. leps fly around the screen.
     */
    public void lepAnimation() {
    	Random rand = new Random();
    	//X
    	int x = rand.nextInt((int)(plot.getLayoutX() + gardenWidth));
    	while(x < plot.getLayoutX() || x > (plot.getLayoutX() + gardenWidth)) {
    		x = rand.nextInt((int)(plot.getLayoutX() + gardenWidth));
    	}
    	//Y
    	int y = rand.nextInt((int)(plot.getLayoutY() + gardenHeight));
    	while(y < plot.getLayoutY() || y > (plot.getLayoutY() + gardenHeight)) {
    		y = rand.nextInt((int)(plot.getLayoutY() + gardenHeight));
    	}
    	//ToX
    	int tox = rand.nextInt((int)(plot.getLayoutX() + gardenWidth));
    	while(tox < plot.getLayoutX() || tox > (plot.getLayoutX() + gardenWidth)) {
    		tox = rand.nextInt((int)(plot.getLayoutX() + gardenWidth));
    	}
    	//ToY
    	int toy = rand.nextInt((int)(plot.getLayoutY() + gardenHeight));
    	while(toy < plot.getLayoutY() || y > (plot.getLayoutY() + gardenHeight)) {
    		toy = rand.nextInt((int)(plot.getLayoutY() + gardenHeight));
    	}
    	
    	
    	System.out.println(x + "," + y + "," + tox + "," + toy);
    	ImageView c = new ImageView(new Image(getClass().getResourceAsStream("/images/lepAnimation.png"), width, height, false, true));
    	c.setPreserveRatio(true);
    	c.setFitHeight(30);
    	c.setFitWidth(40);
    	c.setLayoutX(x - (gardenWidth / 8));
    	c.setLayoutY(y - (gardenHeight / 8));
    	
    	TranslateTransition trans = new TranslateTransition();
    	trans.setDuration(Duration.seconds(5));
    	trans.setToX(tox - (gardenWidth / 8));
    	trans.setToY(toy - (gardenHeight / 8));
    	trans.setAutoReverse(true);
    	trans.setCycleCount(Animation.INDEFINITE);   	
    	trans.setNode(c);
    	trans.play();
    	outerPlot.getChildren().add(c);
    }
    
    
}	