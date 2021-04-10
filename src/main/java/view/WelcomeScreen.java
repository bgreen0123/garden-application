package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class WelcomeScreen extends Window{
    Image background;
    Button nextPage;
    
    //Getters
    public Image getBackground() {
    	return background;
    }
    
    public Button getNextPage() {
    	return nextPage;
    }
    
    //Setters
    public void setBackground(Image b) {
    	background = b;
    }
    
    public void setNextPage(Button b) {
    	nextPage = b;
    }
}
