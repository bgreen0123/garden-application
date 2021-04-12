package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MarketEntryWindow extends Window{
	Image background;
	Button nextPage;
	Label instructions;
	Scene scene;
    int width, height;
    Stage stage;

	public MarketEntryWindow(int width, int height, Stage stage) {
		this.width = width;
        this.height = height;
        this.stage = stage;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
