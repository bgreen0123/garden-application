package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Plant;

public class MarketItem {
	HBox comp;
	Label l;
	Button b;
	Plant p;
	public MarketItem(Plant pl, Button but) {
		this.b = but;
		this.p = pl;
		l = new Label(p.toString());
		Image img = new Image(getClass().getResourceAsStream("/images/favorite_decal.jpg"));
		ImageView v = new ImageView(img);
		v.setFitHeight(30);
		v.setPreserveRatio(true);
		b.setGraphic(v);
		comp = new HBox();
		comp.getChildren().addAll(l, b);
	}
	public Plant getPlant() {
		return p;
	}
	public HBox getComp() {
		return comp;
	}
	public Label getLabel() {
		return l;
	}
	public Button getButton() {
		return b;
	}
}
