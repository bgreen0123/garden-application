package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public class ConditionsWindow extends Window{
    Scene scene;
    int width, height;
    Stage stage;
    ChoiceBox sun, soil, moisture; 
    Button conditionsNext;
    TextField budget, widthBox, heightBox;
    
    //Constructor
    
    /**
     * constructor for the conditions window
     * 
     * @param width, width of screen
     * @param height, height of screen
     * @param stage, javafx stage for window
     * @param sun, drop down of sun conditions
     * @param soil, drop down of soil conditions
     * @param moisture, drop down of moisture conditions
     * @param conditionsNext, button to go to next screen after conditions
     * @param x, horizontal dimension slider
     * @param y, vertical dimension slider
     * @param text, budget input 
     * @param widthBox, user input width dimensions
     * @param heightBox, user input height dimensions
     */
    public ConditionsWindow(int width, int height, Stage stage, ChoiceBox sun, ChoiceBox soil, ChoiceBox moisture, Button conditionsNext, 
    		TextField text, TextField widthBox, TextField heightBox){
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.sun = sun;
        this.soil = soil;
        this.moisture = moisture;
        this.conditionsNext = conditionsNext;
        this.budget = text;
        this.widthBox = widthBox;
        this.heightBox = heightBox;
    }
    
    
    /**
     * contains all the logic for the conditions screen.
     * contains the condition drop downs, and text input boxes.
     * contains a gridbox that contains all the components and shows the stage.
     * conditions window stage gets drawn after button pressed from previous screen
     */
    
    @Override
    public void draw() {
        
        //Background image for conditions window
        Image img = new Image(getClass().getResourceAsStream("/images/welcome-background.jpg"), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background background = new Background(bg);
        
		//Set up a grid pane
        GridPane gp = new GridPane();
       
        //Set Conditions Area
        VBox conditions = new VBox();
        conditions.setSpacing(height * .1);
        conditions.setPrefWidth(width * .6);
        
        Label cond = new Label("Enter Your Garden Conditions");
        cond.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 36));
        cond.setTextFill(Color.BLACK);
        
        GridPane choiceBox = new GridPane();
        choiceBox.setPrefWidth(width * 60);
        
        HBox condBox = new HBox(cond);
        condBox.setAlignment(Pos.CENTER);
        
        HBox sunBox = new HBox(sun);
        sunBox.setPrefWidth(width * .2);
        choiceBox.add(sunBox, 1, 0);
        HBox soilBox = new HBox(soil);
        soilBox.setPrefWidth(width * .2);
        choiceBox.add(soilBox, 2, 0);
        HBox moistureBox = new HBox(moisture);
        moistureBox.setPrefWidth(width * .2);
        choiceBox.add(moistureBox, 3, 0);
        
        conditions.getChildren().addAll(condBox, choiceBox);
        conditions.setAlignment(Pos.CENTER);
        
        VBox sliderBox = new VBox();
        sliderBox.setSpacing(height * .05);
        sliderBox.setPrefWidth(width * .6);
        
        Label dim = new Label("Set Garden Dimensions");
        dim.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 36));
        dim.setTextFill(Color.BLACK);
        HBox dimBox = new HBox(dim);
        dimBox.setAlignment(Pos.CENTER);
        
        HBox sliders = new HBox();
        
        VBox xBox = new VBox();
        xBox.setPrefWidth(width * .3);
        Label l1 = new Label("Width (in ft.)");
        l1.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
        l1.setTextFill(Color.BLACK);
        HBox l1Box = new HBox(l1);
        l1Box.setAlignment(Pos.CENTER);
        HBox s1Box = new HBox(widthBox);
        s1Box.setAlignment(Pos.CENTER);
        xBox.getChildren().addAll(l1Box, s1Box);
        
        VBox yBox = new VBox();
        yBox.setPrefWidth(width * .3);
        Label l2 = new Label("Height (in ft.)");
        l2.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
        l2.setTextFill(Color.BLACK);
        HBox l2Box = new HBox(l2);
        l2Box.setAlignment(Pos.CENTER);
        HBox s2Box = new HBox(heightBox);
        s2Box.setAlignment(Pos.CENTER);
        yBox.getChildren().addAll(l2Box, s2Box);
        
        sliders.getChildren().addAll(xBox, yBox);
        sliderBox.getChildren().addAll(dimBox, sliders);
        
        //Enter Budget Area
        VBox budgetBox = new VBox();
        budgetBox.setSpacing(height * .1);
        budgetBox.setPrefWidth(width * .6);
        
        Label bud = new Label("Enter Your Budget");
        bud.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 36));
        bud.setTextFill(Color.BLACK);
        HBox budBox = new HBox(bud);
        budBox.setAlignment(Pos.CENTER);
        
        HBox budgetButton = new HBox(budget, conditionsNext);
        budget.setPrefSize(width * .3, height * .1);
        budget.setFont(Font.font("Verdana", FontWeight.NORMAL, 25));
        budget.setPromptText("Enter Budget");
        conditionsNext.setPrefSize(width * .1, height * .1);
        conditionsNext.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        budgetButton.setSpacing(10);
        budgetButton.setAlignment(Pos.CENTER);
        
        budgetBox.getChildren().addAll(budBox, budgetButton);
        
        //Spacers in between components
        HBox spacer1 = new HBox();
        spacer1.setPrefHeight(height * .1);
        HBox spacer2 = new HBox();
        spacer2.setPrefHeight(height * .1);
        
        
        //Add To GridPane
        gp.add(conditions, 0, 0);
        gp.add(spacer1, 0, 1);
        gp.add(sliderBox, 0, 2);
        gp.add(spacer2, 0, 3);
        gp.add(budgetBox, 0, 4);
        gp.setAlignment(Pos.CENTER);

        //Set the background
        gp.setBackground(background);
        

        scene = new Scene(gp, width, height);
        stage.setScene(scene);
        stage.show();
    }
    
}


