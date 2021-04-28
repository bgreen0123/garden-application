package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoadingScreen extends Window{
    Scene scene;
    int width, height;
    Stage stage;
    
	public LoadingScreen(Stage stage, int width, int height) {
        this.height = height;
        this.stage = stage;
        this.width = width;
    }
	
    @Override
    public void draw() {     
        GridPane grid = new GridPane();
        Label load = new Label("Loading...");
        load.setFont(Font.font("Courier New",30));
        
        grid.add(load, 0, 0);
        grid.setAlignment(Pos.CENTER);
       
        scene = new Scene(grid, width, height);
        stage.setScene(scene);
        stage.show();  
    }
}
