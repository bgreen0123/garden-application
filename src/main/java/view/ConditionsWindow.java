package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConditionsWindow extends Window{
    Scene scene;
    int width, height;
    Stage stage;
    public ConditionsWindow(int width, int height, Stage stage){
        this.width = width;
        this.height = height;
        this.stage = stage;
    }
    @Override
    public void draw() {
        Label l = new Label("This is the conditions window");

        Group g = new Group();
        g.getChildren().add(l);

        scene = new Scene(g, width, height);
        stage.setScene(scene);
        stage.show();
    }
    
}
