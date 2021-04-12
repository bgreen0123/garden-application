package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EndWindow extends Window{
    Image background;
    Label spent;
    Label totalLeps;
    Label plants;
    Scene scene;
    int width, height;
    Stage stage;

    public EndWindow(int width, int height, Stage stage) {
        this.width = width;
        this.height = height;
        this.stage = stage;
    }
    @Override
    public void draw() {
        
    }
    
    
}
