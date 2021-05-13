package view;

import java.util.ArrayList;
import java.util.HashSet;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Plant;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public class EndWindow extends Window{
    Image background;
    Controller cont;
    Scene scene;
    int width, height;
    Stage stage;
    String fileName;
    TextField saveBox;
    
    ObservableList<String> plantsSummaryList;
    GardenWindow gw;
    Stage gwStage;
    Scene gwScene;
    View view;
    
    Label plantInfo;
   
    int budget;
    ArrayList<Plant> plantsList;
    HashSet<String> uniquePlants;
    ArrayList<Plant> favoritePlants;
    int leps;
    
    Button preview;
    Button endToGarden;
    Button restart;
    Button save;
    
    /**
     * endwindow constructor 
     * 
     * @param width, screen width
     * @param height, screen height
     * @param stage, end window stage
     * @param restart, restart button
     * @param cont, controller
     * @param fileName, file
     * @param saveBox, save popup
     * @param plantsList, list of plants in the garden
     * @param favoritePlants, list of favorite plants
     * @param view, view 
     * @param save, save button
     * @param endToGarden, goes back to garden button
     */

    public EndWindow(int width, int height, Stage stage, Button restart, Controller cont, String fileName, TextField saveBox, 
    				ArrayList<Plant> plantsList, ArrayList<Plant> favoritePlants, View view, Button save, Button endToGarden) {
    	
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.cont = cont;
        this.restart = restart;
        this.fileName = fileName;
        this.saveBox = saveBox;
        this.plantsList = plantsList;
        this.favoritePlants = favoritePlants;
        this.view = view;
        this.save = save;
        this.endToGarden = endToGarden;
    }
    
    /**
     * contains all the logic for the end window screen.
     * creates a new scene that summarizes all the garden information. 
     * prints out the remaining budget, leps supported, and displays as list of plants in garden
     * also contains a garden preview button, save button, restart button, and a button to go back to the garden
     */
    
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
        preview.setPrefSize(width*.2, height*.1);
        preview.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		preview.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				Pane outerPlot = gw.getOuterPlot();
				FlowPane garden = new FlowPane();
				garden.getChildren().addAll(outerPlot);
				
				Scene gardenScene = new Scene(garden, width * .7, height); 
				previewStage.setScene(gardenScene);
				previewStage.show();
			}
		});
        
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
        amountSpentBox.setSpacing(20);;
        amountSpentBox.setAlignment(Pos.CENTER);
        amountSpentBox.getChildren().addAll(dollar, spent);
        
        // plants with list view that shows plants in garden.
        
        Image flowerimg = new Image(getClass().getResourceAsStream("/images/flower.png.png"));
        ImageView plants = new ImageView();
        plants.setImage(flowerimg);
        plants.setPreserveRatio(true);
        plants.setFitHeight(30);
        
        
        Label plantLabel = new Label("Plants:");
        plantLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
        HBox plantsHBox = new HBox();
        plantsHBox.setSpacing(20);
        plantsHBox.setAlignment(Pos.CENTER);
        plantsHBox.getChildren().addAll(plants, plantLabel);
        
        plantsSummaryList = FXCollections.observableArrayList();
       
        ArrayList<String> plantsToStrings = new ArrayList<String>();
        for(Plant p : plantsList) {
        	plantsToStrings.add(p.toString());
        }
        uniquePlants = new HashSet(plantsToStrings);
        
        uniquePlants.forEach(m -> {
        	plantsSummaryList.add(m);
        	
        });
        
    	ListView<String> plantSummary = new ListView<String>(plantsSummaryList);
    	plantSummary.setMaxSize(width * .2, height * .2);
        
        // leps
        
        Image lepimg = new Image(getClass().getResourceAsStream("/images/butterfly-png.png"));
        ImageView theLep = new ImageView();
        theLep.setImage(lepimg);
        theLep.setPreserveRatio(true);
        theLep.setFitHeight(30);
        Label totalLeps = new Label("Leps supported: " + leps);
        totalLeps.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
        HBox lepsBox = new HBox();
        lepsBox.setSpacing(20);;
        lepsBox.setAlignment(Pos.CENTER);
        lepsBox.getChildren().addAll(theLep, totalLeps);
        
        save.setPrefSize(width*.1, height*.1);
        save.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        HBox saveBut = new HBox(save);
        saveBut.setAlignment(Pos.CENTER);
        
        restart.setPrefSize(width * .1, height * .1);
        restart.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        
        endToGarden.setPrefSize(width * .2, height * .1);
        endToGarden.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        
        
        HBox downloadBox = new HBox();
        downloadBox.getChildren().addAll(restart, preview, save, endToGarden);
        downloadBox.setAlignment(Pos.CENTER);
        downloadBox.setPadding(new Insets(height *.05));
        downloadBox.setSpacing(width * .05);
        
        VBox plantBox = new VBox();
        plantBox.getChildren().addAll(plantsHBox, plantSummary);
        plantBox.setAlignment(Pos.CENTER);
        plantBox.setSpacing(10);
        
        VBox summaryBox = new VBox();
        summaryBox.getChildren().addAll(amountSpentBox, lepsBox, plantBox);
        summaryBox.setSpacing(height * .05);
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


