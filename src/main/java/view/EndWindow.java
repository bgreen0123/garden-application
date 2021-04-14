package view;

import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndWindow extends Window{
    Image background;

    Scene scene;
    int width, height;
    Stage stage;
    Button download;
    
    int budget;
    ArrayList plants;
    ArrayList leps;

    public EndWindow(int width, int height, Stage stage, Button download, int budget, ArrayList plants, ArrayList leps) {
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.download = download;
        this.budget = budget;
        this.plants = plants;
        this.leps = leps;
    }
    
    @Override
    public void draw() {
    	
    	//Background Image
        Image img = new Image(new File("src/main/java/images/End-background.jpg").toURI().toString(), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background= new Background(bg);
        
     // summary information about garden
        
        Label spent = new Label ("Amount Spent: " + budget);
        Label totalLeps = new Label("Total Leps: " + leps);
        Label totalPlants = new Label("Plants: " + plants);
        Label Summary = new Label ("SUMMARY");
        
        spent.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 16));
        totalLeps.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 16));
        totalPlants.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 16));
        Summary.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 36));
        BorderPane border = new BorderPane();
        
        
        
      // create a stack pane 
        
        border.setAlignment(spent, Pos.CENTER);
//        border.setMargin(spent, new Insets(150, 0, 150, 50));
        border.setLeft(spent);
        
        border.setAlignment(totalPlants, Pos.CENTER);
//        border.setMargin(plants, new Insets(150, 0, 150, 75));
        border.setCenter(totalPlants);
        
        border.setAlignment(totalLeps, Pos.CENTER);
//        border.setMargin(totalLeps, new Insets(150, 275, 0, 0));
        border.setRight(totalLeps);
        
        border.setAlignment(Summary, Pos.CENTER);
        border.setMargin(Summary, new Insets(50, 0, 0, 0));
        border.setTop(Summary);
        border.setBackground(background);
        
        scene = new Scene(border, width, height);
        stage.setScene(scene);
        stage.show(); 

        
    }
    
    
}
