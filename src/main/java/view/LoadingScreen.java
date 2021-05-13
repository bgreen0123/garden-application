package view;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public class LoadingScreen extends Window{
    Scene scene;
    int width, height;
    Stage stage;
    Controller cont;
    GardenWindow gw;
    
    /**
     * loading screen constructor
     * @param stage, stage of loading screen
     * @param width, screen width
     * @param height, screen height
     */
	public LoadingScreen(Stage stage, int width, int height) {
        this.height = height;
        this.stage = stage;
        this.width = width;
    }
	
	/**
	 * contains logic of loading screen. says "Loading..."
	 */
    @Override
    public void draw() {   
        GridPane grid = new GridPane();
        Label load = new Label("Loading...");
        load.setFont(Font.font("Courier New", 30));
        
        grid.add(load, 0, 0);
        grid.setAlignment(Pos.CENTER);
       
        scene = new Scene(grid, width, height);
        stage.setScene(scene);
        
        stage.show(); 	
    }
}
