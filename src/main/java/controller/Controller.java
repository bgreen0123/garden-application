package controller;

import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.Node;

import model.Model;
import view.View;

public class Controller{
	Model model;
	View view;
	
	public EventHandler dragPlant() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	public EventHandler toNextPage() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
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