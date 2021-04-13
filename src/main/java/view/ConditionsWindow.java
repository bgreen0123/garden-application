package view;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ConditionsWindow extends Window{
    Scene scene;
    int width, height;
    Stage stage;
    public ConditionsWindow(int width, int height, Stage stage){
        this.width = width;
        this.height = height;
        this.stage = stage;
    }
    @Override
    public void draw() {
    	//Choose a title and set it's font and size
        Label t = new Label("Choose Your Conditions!!");
        t.setFont(Font.font("Verdana",30));
        
        //Background image for conditions window
        Image img = new Image(new File("src/main/java/images/conditions-window.jpg").toURI().toString(),width,height,false,true);
        BackgroundImage bg = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background background = new Background(bg);

        BorderPane border = new BorderPane();
        
        border.setTop(t);
        t.setPadding(new Insets(30,30,0,200));
        border.setBackground(background);

        scene = new Scene(border, width, height);
        stage.setScene(scene);
        stage.show();
    }
    
}
