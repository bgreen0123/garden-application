package view;

import java.io.File;

import enums.Moisture;
import enums.Soil;
import enums.Sun;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConditionsWindow extends Window{
    Scene scene;
    int width, height;
    Stage stage;
    ChoiceBox sun, soil, moisture; 
    Button conditionsNext;
    TextField text;
    
    //Constructor
    public ConditionsWindow(int width, int height, Stage stage, ChoiceBox sun, ChoiceBox soil, ChoiceBox moisture, Button conditionsNext, TextField text){
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.sun = sun;
        this.soil = soil;
        this.moisture = moisture;
        this.conditionsNext = conditionsNext;
        this.text = text;
    }
    
    @Override
    public void draw() {
    	//Choose a title and set it's font and size
        Label t = new Label("Choose Your Conditions and budget!!");
        t.setFont(Font.font("Courier New",30));
        
        //Background image for conditions window
        Image img = new Image(new File("src/main/java/images/conditions-window.jpg").toURI().toString(),width,height,false,true);
        BackgroundImage bg = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background background = new Background(bg);
        
		//Set up a border pane
        BorderPane border = new BorderPane();
        
        //Set up HBoxes
        HBox choiceBox = new HBox();
        HBox buttonBox = new HBox();
        HBox textBox = new HBox();
        
        choiceBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        choiceBox.getChildren().addAll(sun,soil,moisture);
        textBox.setAlignment(Pos.BOTTOM_CENTER);
        textBox.getChildren().add(text);
        
        conditionsNext.setPrefSize(100,50);
        buttonBox.getChildren().add(conditionsNext);
        border.setCenter(choiceBox);
        border.setRight(buttonBox);
        border.setBottom(textBox);
        
        //Add the tile, padding and the drop down menus to the border pane. Set the background
        border.setTop(t);
        t.setPadding(new Insets(30,30,0,130));
        border.setBackground(background);
        

        scene = new Scene(border, width, height);
        stage.setScene(scene);
        stage.show();
    }
    
}


