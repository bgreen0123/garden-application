package view;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JList;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Plant;

public class EndWindow extends Window{
    Image background;
    Controller cont;
    Scene scene;
    int width, height;
    Stage stage;
    String fileName;
    TextField saveBox;
    Button preview;
    ObservableList<String> plantsSummaryList;
    
    GardenWindow gw;
    Stage gwStage;
    Scene gwScene;
    View view;
    
    Label plantInfo;
   
    int budget;
    ArrayList<Plant> plantsList;
    ArrayList<Plant> favoritePlants;
    int leps;
    
    Button download;

    public EndWindow(int width, int height, Stage stage, Button download, Controller cont, String fileName, TextField saveBox, 
    				ArrayList<Plant> plantsList, ArrayList<Plant> favoritePlants, View view) {
    	
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.cont = cont;
        this.download = download;
        this.fileName = fileName;
        this.saveBox = saveBox;
        this.plantsList = plantsList;
        this.favoritePlants = favoritePlants;
        this.view = view;
    }
    
    @Override
    public void draw() {
    	budget = cont.getModel().getBudget();
    	leps = cont.getModel().getNumLeps();
    	plantsList = cont.getModel().getPlants();
    	//Background Image
        Image img = new Image(getClass().getResourceAsStream("/images/end-background.jpg"), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background= new Background(bg);
        
        BorderPane border = new BorderPane();
        border.setBackground(background);
        
        
        // gets garden window scene
        gw = view.garden;
        
        Stage previewStage = new Stage();
        
        preview = new Button("Garden Preview");	
		
		preview.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				Pane outerPlot = gw.getOuterPlot();
				
				FlowPane garden = new FlowPane();
				garden.getChildren().addAll(outerPlot);
				
				Scene gardenScene = new Scene(garden, height, width); 
				previewStage.setScene(gardenScene);
				previewStage.show();
			}
		});

        // TODO: make button that previews ^
        
        // summary information about garden
        
        // budget summary 
        
        Image dollarimg = new Image(getClass().getResourceAsStream("/images/dollar_sign.png"));
        ImageView dollar = new ImageView();
        dollar.setImage(dollarimg);
        dollar.setPreserveRatio(true);;
        dollar.setFitHeight(45);
        Label spent = new Label("Money Remaining: $" + budget);
        spent.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
        HBox amountSpentBox = new HBox();
//        VBox.setMargin(amountSpentBox, new Insets(25,25,0,0));
        amountSpentBox.setSpacing(20);;
        amountSpentBox.setAlignment(Pos.CENTER);
        amountSpentBox.getChildren().addAll(dollar, spent);
        
        // plants with list view that shows plants in garden.
        
        Image flowerimg = new Image(getClass().getResourceAsStream("/images/flower.png.png"));
        ImageView plants = new ImageView();
        plants.setImage(flowerimg);
        plants.setPreserveRatio(true);
        Label plantLabel = new Label("Plants!");
        plantLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
        
        plantsSummaryList = FXCollections.observableArrayList();
       
        plantsList.forEach(m -> {
        	plantsSummaryList.add(m.toString());
        	
        });
        
    	ListView<String> plantSummary = new ListView<String>(plantsSummaryList);
    	plantSummary.setMaxSize(width * .3, height * .3);
        
        // leps
        
        Image lepimg = new Image(getClass().getResourceAsStream("/images/butterfly-png.png"));
        ImageView theLep = new ImageView();
        theLep.setImage(lepimg);
        theLep.setPreserveRatio(true);
        theLep.setFitHeight(30);
        Label totalLeps = new Label("Leps supported: " + leps);
        totalLeps.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
        HBox lepsBox = new HBox();
//        VBox.setMargin(lepsBox, new Insets(75,0,0,20));
        lepsBox.setSpacing(20);;
        lepsBox.setAlignment(Pos.CENTER);
        lepsBox.getChildren().addAll(theLep, totalLeps);
        
        // download button image
        Image downloadimg = new Image(getClass().getResourceAsStream("/images/download.png"));
        ImageView downloading = new ImageView();
        downloading.setImage(downloadimg);
        downloading.setFitHeight(30);
        downloading.setPreserveRatio(true);
        
        
        download = new Button("Download");
//        download.setPrefSize(20, 20);
//        download.setGraphic(downloading);
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        download.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event) {
        		fileChooser.showSaveDialog(stage);
        	}
        });
        
        HBox downloadBox = new HBox();
        downloadBox.getChildren().addAll(download, preview);
        downloadBox.setAlignment(Pos.CENTER);
        
        VBox summaryBox = new VBox();
        summaryBox.getChildren().addAll(amountSpentBox, lepsBox, plantLabel, plantSummary);
        summaryBox.setAlignment(Pos.CENTER);
        
        Label summary = new Label("SUMMARY");
        summary.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 48));
        border.setAlignment(summary, Pos.CENTER);

        border.setTop(summary);
        border.setCenter(summaryBox);  
        border.setBottom(downloadBox);
        
        scene = new Scene(border, width, height);
        stage.setScene(scene);
        stage.setTitle("Summary Screen");
        stage.show(); 


        
    }
    
}

