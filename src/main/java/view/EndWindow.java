package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class EndWindow extends Window{
    Image background;
    Label spent;
    Label totalLeps;
    Label plants;
    
    //Getters
    public Image getBackground() {
    	return background;
    }
    
    public Label getSpent() {
    	return spent;
    }
    
    public Label getTotalLeps() {
    	return totalLeps;
    }
    
    public Label getPlants() {
    	return plants;
    }
    
    //Setters
    public void setBackground(Image b) {
    	background = b;
    }
    
    public void setSpent(Label s) {
    	spent = s;
    }
    
    public void setTotalLeps(Label t) {
    	totalLeps = t;
    }
    
    public void setPlants(Label plant) {
    	plants = plant;
    }
}
