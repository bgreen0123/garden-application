package view;

import static javafx.geometry.Pos.CENTER;


import javafx.scene.paint.Color;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public class WelcomeScreen extends Window{
    Button nextPage;
    Button toLoad;
    Button load;
	Scene scene;
    int width, height;
    Stage stage;
    Color titleFill = Color.WHITE;
    Color titleOutline = Color.BLACK;
    
    /**
     * welcome screen constructor
     * 
     * @param width, screen width
     * @param height, screen height
     * @param stage, welcome stage
     * @param nextPage, button to go to conditions
     * @param toLoad, button to go to load screen
     * @param load, load button
     */
    public WelcomeScreen(int width, int height, Stage stage, Button nextPage, Button toLoad, Button load){
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.nextPage = nextPage;
        this.toLoad = toLoad;
        this.load = load;
    }
    /**
     * contains logic and all components of starting screen
     * title that says "welcome to garden builder"
     * contains a load button to load saved garden
     * button to start a new garden
     */
    @Override
    public void draw() {

        //Background Image
        Image img = new Image(getClass().getResourceAsStream("/images/mtCuba-transparent.png"), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background= new Background(bg);
 
        //GridPane layout with Button in the center, and label on top
        Text title = new Text("Welcome To The Garden Builder");
        title.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC,55));
        title.setFill(titleFill);
        title.setStroke(titleOutline);
        title.setStrokeWidth(2);
        GridPane gp = new GridPane();
        VBox buttons = new VBox();
        buttons.setSpacing(height*.1);
        gp.setPadding(new Insets(150, 0, 100, 0));
        
        nextPage.setPrefSize(width * .3, height * .1);
        nextPage.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        load.setPrefSize(width * .3, height * .1);
        load.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        buttons.getChildren().addAll(nextPage,load);
        buttons.setAlignment(Pos.CENTER);
        
        //spacers
        HBox spacer1 = new HBox();
        spacer1.setPrefHeight(height*.1);
        
        gp.add(title, 0, 1);
        gp.add(spacer1, 0, 2);
        gp.add(buttons, 0, 3);
        gp.setAlignment(Pos.CENTER);
        gp.setBackground(background);

        scene = new Scene(gp, width, height);
        stage.setScene(scene);
        stage.show(); 
    }
}
