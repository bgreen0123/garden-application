package view;

import java.io.File;

import controller.Controller;
import enums.CurrentScreen;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.stage.Stage;

public class GardenWindow extends Window{
    Controller cont;
	Image background;
    int budget;
    String totalLeps = "0";
    Button toMarket;
    Pane faveList;
    Scene scene;
    int width, height;
    Stage stage;
    String fontSize = "18";
    String fontColor = "black";
    String countBarColor = "rgb(102,201,52)";
    int countBarHeight = 50;
    int countBarItemHeight = 30;

    public GardenWindow(int width, int height, Stage stage, Controller cont) {
    	this.width = width;
        this.height = height;
        this.stage = stage;
        this.cont = cont;
    }
    
    @Override
    public void draw() {
    	//Background Image
    	System.out.println("Draw garden!");
        Image bgimg = new Image(new File("src/main/java/images/dirt-background-transparent.jpg").toURI().toString(), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(bgimg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background= new Background(bg);
        
        //Entire screen is a borderpane
        BorderPane border = new BorderPane();
        border.setBackground(background);
        
        //Budget section
        Image dollarimg = new Image(new File("src/main/java/images/dollar-sign.png").toURI().toString(), width, height, false, true);
        ImageView dollariv = new ImageView();
        dollariv.setImage(dollarimg);
        dollariv.setPreserveRatio(true);
    	dollariv.setFitHeight(countBarItemHeight);
    	Label budgetLabel = new Label("Budget Remaining: " + Integer.toString(budget));
    	budgetLabel.setStyle("-fx-font-weight: bold;-fx-font-size:" + fontSize + ";-fx-text-fill:" + fontColor + ";");
    	HBox budgetBox = new HBox();
    	Insets countBarItemSpacing = new Insets((countBarHeight-countBarItemHeight)/2, 
    			0, (countBarHeight-countBarItemHeight)/2, 50);
    	HBox.setMargin(budgetBox, countBarItemSpacing);
    	budgetBox.setSpacing(15);
    	budgetBox.setAlignment(Pos.CENTER);
    	budgetBox.getChildren().addAll(dollariv, budgetLabel);
    	
    	//Lep section
        Image lepImg = new Image(new File("src/main/java/images/butterfly.png").toURI().toString(), width, height, false, true);
        ImageView lepiv = new ImageView();
        lepiv.setImage(lepImg);
        lepiv.setPreserveRatio(true);
    	lepiv.setFitHeight(countBarItemHeight);
    	Label lepLabel = new Label("Leps Supported: " + totalLeps);
    	lepLabel.setStyle("-fx-font-weight: bold;-fx-font-size:" + fontSize + ";-fx-text-fill:" + fontColor + ";");
    	HBox lepBox = new HBox();
    	HBox.setMargin(lepBox, countBarItemSpacing);
    	lepBox.setSpacing(15);
    	lepBox.setAlignment(Pos.CENTER);
    	lepBox.getChildren().addAll(lepiv, lepLabel);
    	
        
        //Market Stand Button
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
    			cont.goToPage(CurrentScreen.WELCOME);
    		}
    	});
    	
    	//Top bar
    	HBox countBar = new HBox();
    	countBar.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:" + countBarColor + ";");
        countBar.setPrefHeight(countBarHeight);
        countBar.getChildren().addAll(marketiv, budgetBox, lepBox);
    	
    	GridPane plot = new GridPane();
        border.setTop(countBar);
        border.setCenter(plot);
        
        
        scene = new Scene(border, width, height);
        stage.setScene(scene);
        stage.show(); 
    }
    
    public void updateBudget(int b) {
    	budget = b;
    }
    
    
}	
