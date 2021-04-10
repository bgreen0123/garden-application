package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class MarketEntryWindow extends Window{
	  Image background;
	  Button nextPage;
	  Label instructions;
	  
	  //Getters
	  public Image getBackground() {
	    return background;
	  }
	    
	  public Button getNextPage() {
	    return nextPage;
	  }
	  
	  public Label getLabel() {
		  return instructions;
	  }
	    
	  //Setters
	  public void setBackground(Image b) {
	    background = b;
	  }
	    
	  public void setNextPage(Button b) {
	    nextPage = b;
	  }
	  
	  public void setInstructions(Label i) {
		  instructions = i;
	  }
}
