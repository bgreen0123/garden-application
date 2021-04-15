package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Plant;

public class MarketItem {
	HBox comp;
	Label l;
	public MarketItem(Plant p, Button b) {
		l = new Label(p.toString());
		comp = new HBox();
		comp.setSpacing(50);
		comp.getChildren().addAll(l, b);
	}
	
	public HBox getComp() {
		return comp;
	}
}
