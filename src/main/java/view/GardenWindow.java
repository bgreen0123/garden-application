package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GardenWindow extends Window{
    Image background;
    String budget;
    String totalLeps;
    Button toMarket;
    Pane faveList;
    Scene scene;
    int width, height;
    Stage stage;

    public GardenWindow(int width, int height, Stage stage) {
        this.width = width;
        this.height = height;
        this.stage = stage;
    }
    
    @Override
    public void draw() {
        // TODO Auto-generated method stub
        
    }
    
    
}	
