package view;

import java.util.ArrayList;

import controller.Controller;
import enums.Moisture;
import enums.Soil;
import enums.Sun;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MarketWindowWoody extends Window{
    Image img;
    ArrayList<MarketItem> woody;
    Button nextPage;
    Button herb;
    Button applyConditions;
    Button viewFavs;
    Button cond;
    Scene scene;
    int width, height;
    Stage stage;
    Controller cont;
    
    private boolean filter = false;
    private int count;

	public MarketWindowWoody(int width, int height, Stage stage, ArrayList<MarketItem> woody, Button herb, Button nextPage, Button applyConditions, Button viewFavs, Button cond, Controller cont) {
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.woody = woody;
        this.herb = herb;
        this.nextPage = nextPage;
        this.applyConditions = applyConditions;
        this.viewFavs = viewFavs;
        this.cond = cond;
        this.cont = cont;
        img = new Image(getClass().getResourceAsStream("/images/conditions-window.jpg"), width, height, false, true);
    }
	
	public void setFilter() {
		filter = !filter;
	}
	
    @Override
    public void draw() {
    	BackgroundImage bg = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background background = new Background(bg);
        
        GridPane grid = new GridPane();
        Label wood = new Label("Woody");
        wood.setFont(Font.font("Courier New",30));
        ListView<HBox> list = new ListView<HBox>();
        list.setPrefSize(width * .7, height * .7);
        count = 0;
        woody.forEach(m -> {
        	if(filter) {
        		if((cont.getModel().getSun() == m.getPlant().getSun() || cont.getModel().getSun() == Sun.SUN)
        				&& (cont.getModel().getSoil() == m.getPlant().getSoil() || cont.getModel().getSoil() == Soil.SOIL)
        				&& (cont.getModel().getMoisture() == m.getPlant().getMoisture() || cont.getModel().getMoisture() == Moisture.MOISTURE)) {
        			count++;
        			list.getItems().add(m.getComp());
    	        	m.getLabel().setPrefWidth(width * .4);
        		}
        	}else {
        		count++;
	        	list.getItems().add(m.getComp());
	        	m.getLabel().setPrefWidth(width * .4);
        	}
        });
        if(count == 0) {
    		HBox nothing = new HBox(new Label("There are no plants in the system that match your conditions"));
    		list.getItems().add(nothing);
    	}
        HBox buttons = new HBox();
        buttons.getChildren().addAll(herb, applyConditions, viewFavs, cond, nextPage);
        if(filter) {
        	applyConditions.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        }else {
        	applyConditions.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        buttons.setPrefWidth(width * .7);
        grid.add(wood, 1, 0);
        grid.add(buttons, 1, 1);
        grid.add(list, 1, 2);
        grid.setAlignment(Pos.CENTER);
        
        grid.setBackground(background);
        scene = new Scene(grid, width, height);
        stage.setScene(scene);
        stage.show();  
    }
}
