package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MarketWindow extends Window{
    Image background;
    Button nextPage;
    Button favorited;
    Button woody;
    Button herb;
    MarketItem[] items;
    Scene scene;
    int width, height;
    Stage stage;

	public MarketWindow(int width, int height, Stage stage) {
        this.width = width;
        this.height = height;
        this.stage = stage;
    }
    @Override
    public void draw() {
        // TODO Auto-generated method stub
        
    }
}

