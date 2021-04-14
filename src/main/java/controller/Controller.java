package controller;

import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import model.Lep;
import model.Model;
import model.Plant;
import view.View;

import java.io.File;
import java.util.ArrayList;

import enums.CurrentScreen;
import enums.Moisture;
import enums.Soil;
import enums.Sun;

public class Controller{
	Model model;
	View view;
	//Buttons
	Button welcomeNext;
	Button download;
	Button conditionsNext;

	//Drop down menus
	ChoiceBox<Sun> sun;
	ChoiceBox<Soil> soil;
	ChoiceBox<Moisture> moisture;
	

	public Controller(View view){
		this.view = view;
		model = new Model();

		welcomeNext = new Button("Start");
		conditionsNext = new Button("Continue");
		welcomeNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.CONDITIONS); // change back to CONDITIONS
			System.out.println("Button Pressed");
		});

		conditionsNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.END); //Move to market screen
			System.out.println("Onto market");
		});

		//Creating the drop down menu
		sun = new ChoiceBox<>();
		soil = new ChoiceBox<>();
		moisture = new ChoiceBox<>();
		//Adding choices into the drop menu
		sun.getItems().addAll(Sun.FULLSUN,Sun.PARTSUN,Sun.SHADE);
		soil.getItems().addAll(Soil.CLAY,Soil.DIRT,Soil.ROCK);
		moisture.getItems().addAll(Moisture.WET,Moisture.DRY);
		//Starting value that the user sees
		sun.getSelectionModel().select(0);
		soil.getSelectionModel().select(0);
		moisture.getSelectionModel().select(0);

	}



	//Getters
	public Button getWelcomeButton(){
		return welcomeNext;
	}
	
	public Button getDownloadButton() {
		return download;
	}

	public Button getConditionsButton(){
		return conditionsNext;
	}

	public ChoiceBox<Sun> getSun() {
		return sun;
	}

	public ChoiceBox<Soil> getSoil() {
		return soil;
	}

	public ChoiceBox<Moisture> getMoisture() {
		return moisture;
	}
	
	public Model getModel() {
		//This method is used for view to access certain fields such as budget and lep count
		//No fields should be changed or updated from view, it is only to access values.
		return model;
	}

	//Actions
	public EventHandler dragPlant() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	/*
	 * Change Listeners for Sliders can be added after those sliders are created
	 * Need them for: 
	 *     Choosing width
	 *     Choosing height
	 */
	
	public EventHandler enterBudget() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	public EventHandler toOtherPlantType() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
		};
	}
	
	public EventHandler favoritePlant() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
		};
	}
}

