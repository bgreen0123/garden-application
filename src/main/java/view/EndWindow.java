package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class EndWindow extends Window{
    Image background;

    Scene scene;
    int width, height;
    Stage stage;
    
    int budget;
    ArrayList plantsList;
    ArrayList lepsList;
    
    Button download;

    public EndWindow(int width, int height, Stage stage, Button download, int budget,ArrayList plantsList, ArrayList lepsList) {
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.budget = budget;
        this.download = download;
        this.plantsList = plantsList;
        this.lepsList = lepsList;
    }
    
    @Override
    public void draw() {
    	
    	//Background Image
        Image img = new Image(new File("src/main/java/images/End-background.jpg").toURI().toString(), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background= new Background(bg);
        
        BorderPane border = new BorderPane();
        border.setBackground(background);
        
        
        // summary information about garden
        
        // budget summary 
        
        Image dollarimg = new Image (new File("src/main/java/images/dollar_sign.png").toURI().toString(), width, height, false, true);
        ImageView dollar = new ImageView();
        dollar.setImage(dollarimg);
        dollar.setPreserveRatio(true);;
        dollar.setFitHeight(45);
        Label spent = new Label("Amount Spent: " + budget);
        spent.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
        VBox amountSpentBox = new VBox();
        VBox.setMargin(amountSpentBox, new Insets(25,25,0,0));
        amountSpentBox.setSpacing(10);;
        amountSpentBox.setAlignment(Pos.CENTER);
        amountSpentBox.getChildren().addAll(dollar, spent);
        
        // plants
        
        Image flowerimg = new Image (new File("src/main/java/images/flower.png.png").toURI().toString(), width, height, false, true);
        ImageView plants = new ImageView();
        plants.setImage(flowerimg);
        plants.setPreserveRatio(true);;
        plants.setFitHeight(40);
        Label plant = new Label("Plants chosen: " + plantsList);
        plant.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
        VBox plantsBox = new VBox();
        VBox.setMargin(plantsBox, new Insets(50,0,0,0));
        plantsBox.setSpacing(10);;
        plantsBox.setAlignment(Pos.CENTER);
        plantsBox.getChildren().addAll(plants, plant);
        
        // leps
        
        Image lepimg = new Image (new File("src/main/java/images/butterfly-png.png").toURI().toString(), width, height, false, true);
        ImageView leps = new ImageView();
        leps.setImage(lepimg);
        leps.setPreserveRatio(true);
        leps.setFitHeight(30);
        Label totalLeps = new Label("Leps supported: " + lepsList);
        totalLeps.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
        VBox lepsBox = new VBox();
        VBox.setMargin(lepsBox, new Insets(75,0,0,20));
        lepsBox.setSpacing(10);;
        lepsBox.setAlignment(Pos.CENTER);
        lepsBox.getChildren().addAll(leps, totalLeps);
        
        // download button image
        Image downloadimg = new Image (new File("src/main/java/images/download.png").toURI().toString(), width, height, false, true);
        ImageView downloading = new ImageView();
        downloading.setImage(downloadimg);
        downloading.setFitHeight(30);
        downloading.setPreserveRatio(true);
        
        
        Image garden = new Image(new File("src/main/java/images/temporary_garden.jpg").toURI().toString(), width, height, false, true);
        ImageView gardenimg = new ImageView();
        gardenimg.setImage(garden);
        gardenimg.setPreserveRatio(true);
        gardenimg.setFitHeight(210);
        VBox gardenBox = new VBox();
        VBox.setMargin(gardenBox, new Insets(0,0,0,0));
        gardenBox.setSpacing(10);
        gardenBox.setAlignment(Pos.TOP_CENTER);
        gardenBox.getChildren().addAll(gardenimg);
        
        
//        Menu file = new Menu("File");
//        MenuItem item = new MenuItem("Download", downloading);
//        file.getItems().addAll(item);
        
        
        download = new Button();
        download.setTranslateX(330);
        download.setTranslateY(25);
        download.setPrefSize(20, 20);
        download.setGraphic(downloading);
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        download.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event) {
        		fileChooser.showSaveDialog(stage);
        	}
        });
        
//        MenuBar menuBar = new MenuBar(file);
        
        VBox downloadBox = new VBox();
        VBox.setMargin(downloadBox, new Insets(0,0,0,0));
        downloadBox.getChildren().addAll(gardenBox, download);
        
        VBox summaryBox = new VBox();
        VBox.setMargin(summaryBox, new Insets(150, 50, 0 ,0));
        summaryBox.getChildren().addAll(amountSpentBox, plantsBox, lepsBox); 
        
        Label summary = new Label("SUMMARY");
        summary.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 48));
        border.setAlignment(summary, Pos.CENTER);
        border.setMargin(summary,  new Insets(50,0,0,0));
        
        border.setMargin(downloadBox, new Insets(150,0,0,0));

        border.setTop(summary);
        border.setLeft(summaryBox);
        border.setCenter(downloadBox);
        
        scene = new Scene(border, width, height);
        stage.setScene(scene);
        stage.setTitle("Summary Screen");
        stage.show(); 

        
    }
    
//    public void saveFile(Image image) {
//    	Menu file = new Menu("File");
//    	
//    	MenuItem item = new MenuItem("Save", downloadimg);
//    	
//    	FileChooser fileChooser = new FileChooser();
//    	fileChooser.setTitle("Open Resource File");
//    	File selectedFile = fileChooser.showSaveDialog(stage);
//    	if (selectedFile != null) {
//    		Image img = SwingFXUtils.toFXImage(bImg, null);
//    		saveToFile(img, selectedFile);
//    	}
//    }
    
//    public static void saveToFile(Image image) {
//    	File gardenFile = new File("C:/Garden/");
//    	BufferedImage bImage = fromFXImage(image, null);
//    	try {
//    		ImageIO.write(bImage, getFileExtension(gardenFile), gardenFile);
//    	} catch (IOException e) {
//    		throw new RuntimeException(e);
//    	}
//    }
//    
//    private static String getFileExtension(File file) {
//    	String name = file.getName();
//    	int lastIndexOf = name.lastIndexOf(".") + 1;
//    	if (lastIndexOf == -1) {
//    		return "";
//    	}
//    	return name.substring(lastIndexOf);
//    }
    
    
}
