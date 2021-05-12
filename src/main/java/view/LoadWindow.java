package view;

import java.io.*;
import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public class LoadWindow extends Window implements Serializable{
	
	Stage stage;
	Scene scene;
	String fileName;
	int width;
	int height;
	Button toWelcome;

	public LoadWindow(int width, int height, Stage stage, String fileName, Button toWelcome) {
		this.stage = stage;
		this.width = width;
		this.height = height;
		this.fileName = fileName;
		this.toWelcome = toWelcome;
	}
	
	@Override
	public void draw() {
		GridPane gp = new GridPane();
		Label title = new Label("This is where you will load a garden");

		HBox h = new HBox();
		VBox v = new VBox();
		
		toWelcome.setPrefSize(width*.1, height*.1);
		
		h.getChildren().add(toWelcome);
		h.setAlignment(Pos.CENTER);
		v.getChildren().addAll(title,h);
		
		v.setSpacing(height*.1);
		v.setAlignment(Pos.CENTER);
		
		gp.add(v, 0, 1);
		
		scene = new Scene(gp, width, height);
        stage.setScene(scene);
        stage.show(); 
	}
}
