
import org.junit.*;

import data.Data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import enums.*;
import model.*;

public class TestModel {
	//private ArrayList<Plant> plantsTest = new ArrayList<Plant>();
	//private ArrayList<Lep> lepsTest = new ArrayList<Lep>();
	//private int budgetLeft = 30;
	Plant plant1 = new Plant("Plant 1","Plant 1",34,PlantType.WOODY, Soil.CLAY, Sun.FULLSUN, Moisture.WET, Spread.MEDIUM, "ImageURL", 45);
	Model m = new Model();
	Data d;
	ArrayList<Plant> p;
	ArrayList<Plant> favp;
	Model m2 = new Model(15,10,d,200,p,favp,Sun.FULLSUN,Soil.CLAY,Moisture.WET,100, true, true);
	
	@Test 
	public void testAddPlant() {
		m.addPlant(plant1);
		assertEquals(m.getPlants().size(), 1);
	}
	
	@Test
	public void testFavoritePlantAddGet() {
		m.addFavoritePlant(plant1);
		m.addFavoritePlant(plant1);
		m.addFavoritePlant(plant1);
		
		assertEquals(m.getFavorites().size(), 3);
	}
	
	@Test
	public void testRemovePlant() {
		m.removePlant(plant1);
		assertEquals(m.getPlants().size(),0);
	}
	
	@Test
	public void testGetData() {
		assertTrue(m.getData() instanceof Data);
	}
	
	@Test
	public void testLepCountGetSetDecrease() {
		System.out.println("test lepCount getter and setter");
		
		m.updateLepCount(5);
		
		assertEquals(5, m.getNumLeps());
		
		m.decreaseLepCount(5);
		
		assertEquals(0, m.getNumLeps());
	}
	
	@Test
	public void testWidthAndHeight() {
		System.out.println("test width/height getter and setter");
		
		m.setHeight(100);
		m.setWidth(200);
		
		assertEquals(100, m.getHeight());
		assertEquals(200, m.getWidth());
	}
	
	@Test
	public void testSunGetSet() {
		System.out.println("test sun getter and setter");
		
		m.setSun(Sun.FULLSUN);
		
		assertEquals(Sun.FULLSUN, m.getSun());
	}
	
	@Test
	public void testSoilGetSet() {
		System.out.println("test soil getter and setter");
		
		m.setSoil(Soil.DIRT);
		
		assertEquals(Soil.DIRT, m.getSoil());
	}
	
	@Test
	public void testMoistureGetSet() {
		System.out.println("test moisture getter and setter");
		
		m.setMoisture(Moisture.WET);
		
		assertEquals(Moisture.WET, m.getMoisture());
	}
	
	@Test
	public void testBudgetGetSet() {
		System.out.println("test budget getter and setter");
		
		m.updateBudget(500);
		
		assertEquals(500, m.getBudget());
	}
	
	@Test
	public void warnedGetSet() {
		System.out.println("Test boolean warned getter and setter");
		
		m.setWarned();
		assertTrue(m.getWarned());
		
		m.setWarned();
		assertFalse(m.getWarned());
	}
	
	@Test
	public void toldAboutDeletionGetSet() {
		System.out.println("Test boolean Deletion to see if the user has learned how to delete");
		
		m.setToldAboutDeletion();
		assertTrue(m.getToldAboutDeletion());
		
		m.setToldAboutDeletion();
		assertFalse(m.getToldAboutDeletion());
	}
	
	
}
