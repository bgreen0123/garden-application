package controller;

import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import model.Lep;
import model.Model;
import model.Plant;
import view.View;

import java.io.File;
import java.util.ArrayList;

import enums.CurrentScreen;

public class Controller{
	Model model;
	View view;
	Font font = Font.font("Courier New", FontWeight.BOLD, 24);
	//Buttons
	Button welcomeNext;
	Button download;
	

	public Controller(View view){
		this.view = view;
		model = new Model();

		welcomeNext = new Button("Start");
		welcomeNext.setMaxSize(200, 50);
		welcomeNext.setFont(font);
		welcomeNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.END); // change back to CONDITIONS
			System.out.println("Button Pressed");
		});
	}

	//Getters
	public Button getWelcomeButton(){
		return welcomeNext;
	}
	
	public Button getDownloadButton() {
		return download;
	}
	
	public int Budget() {
		return model.getBudget();
	}
	
	public ArrayList<Plant> Plants() {
		return model.getPlants();
	}
	
	public ArrayList<Lep> Leps() {
		return model.getLeps();
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