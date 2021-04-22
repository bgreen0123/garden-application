package view;

import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MarketWindowWoody extends Window{
    Image img;
    ArrayList<MarketItem> woody;
    Button nextPage;
    Button herb;
    Scene scene;
    int width, height;
    Stage stage;

	public MarketWindowWoody(int width, int height, Stage stage, ArrayList<MarketItem> woody, Button herb, Button nextPage) {
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.woody = woody;
        this.herb = herb;
        this.nextPage = nextPage;
        img = new Image(getClass().getResourceAsStream("/images/conditions-window.jpg"), width, height, false, true);
    }
    @Override
    public void draw() {
    	BackgroundImage bg = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background background = new Background(bg);
        
        GridPane grid = new GridPane();
        Label wood = new Label("Woody");
        wood.setPadding(new Insets(5,5,5,5));
        nextPage.setPadding(new Insets(5,5,5,5));
        wood.setFont(Font.font("Courier New",30));
        ListView<HBox> list = new ListView<HBox>();
        list.setPrefSize(width * .7, height * .7);
        woody.forEach(m -> {
        	list.getItems().add(m.getComp());
        	m.getLabel().setPrefWidth(width * .4);
        });
        grid.add(herb, 0, 0);
        grid.add(wood, 1, 0);
        grid.add(list, 1, 1);
        grid.add(nextPage, 2, 2 );
        grid.setAlignment(Pos.CENTER);
        
        grid.setBackground(background);
        scene = new Scene(grid, width, height);
        stage.setScene(scene);
        stage.show();  
    }
}
