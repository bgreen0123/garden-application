package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class GardenWindow extends Window{
    Image background;
    String budget;
    String totalLeps;
    Button toMarket;
    Pane faveList;
    
    //Getters
    public Image getBackground() {
    	return background;
    }
    
    public String getBudget() {
    	return budget;
    }
    
    public String getTotalLeps() {
    	return totalLeps;
    }
    
    public Button getToMarket() {
    	return toMarket;
    }
    
    public Pane getFaveList() {
    	return faveList;
    }
    
    //Setters
    
    public void setBackground(Image b) {
    	background = b;
    }
    
    public void setBudget(String Budget) {
    	budget = Budget;
    }
    
    public void setTotalLeps(String Leps) {
    	totalLeps = Leps;
    }
    
    public void setToMarket(Button Market) {
    	toMarket = Market;
    }
    
    public void setFaveList(Pane favorite) {
    	faveList = favorite;
    }
}	
