
import org.junit.*;

import data.Data;

import static org.junit.Assert.*;

import enums.*;
import model.*;

public class TestModel {
	//private ArrayList<Plant> plantsTest = new ArrayList<Plant>();
	//private ArrayList<Lep> lepsTest = new ArrayList<Lep>();
	//private int budgetLeft = 30;
	Plant plant1 = new Plant("Plant 1","Plant 1",34,PlantType.WOODY, Soil.CLAY, Sun.FULLSUN, Moisture.WET, "ImageURL");
	Model m = new Model();
	
	@Test 
	public void testAddPlant() {
		m.addPlant(plant1);
		assertEquals(m.getPlants().size(), 1);
	}
	
	@Test
	public void testAddFavoritePlant() {
		m.addFavoritePlant(plant1);
		assertEquals(m.getFavorites().size(), 1);
	}
	
	//DELETE REMOVEPLANT
	
	@Test
	public void testGetThread() {
		assertTrue(m.getThread() instanceof DataThread);
	}
	
	@Test
	public void testGetData() {
		assertTrue(m.getData() instanceof Data);
	}
	
	@Test
	public void testLepCountGetSet() {
		System.out.println("test lepCount getter and setter");
		
		m.updateLepCount(5);
		
		assertEquals(5, m.getNumLeps());
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
}
