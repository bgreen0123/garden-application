

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import enums.PlantType;
import model.*;

public class TestModel {
	//private ArrayList<Plant> plantsTest = new ArrayList<Plant>();
	//private ArrayList<Lep> lepsTest = new ArrayList<Lep>();
	//private int budgetLeft = 30;
	Plant plant1 = new Plant("Plant 1","Plant 1",34,PlantType.WOODY);
	Lep lep1 = new Lep(0,0,0,0);
	
	
	@Test
	public void testUpdateVelocities() {
		System.out.println("update x velocity");
		assertEquals(0,6);
		System.out.println("update y velocity");
		assertEquals(0,6);
	}
	
	@Test
	public void testUpdateLocation() {
		System.out.println("update x location");
		assertEquals(25,27);
		System.out.println("update y location");
		assertEquals(25,27);
	}
	
	@Test
	public void testUpdateBudget() {
		System.out.println("test case update budget");
		Model.updateBudget(29);
		assertEquals(0, Model.getBudget());
	}
	
	@Test
	public void testAddLeps() {
		System.out.println("test case add leps");
		Model.addLeps(lep1);
		
		assertEquals(30, Model.getLeps().size());
	}
	
	@Test
	public void testRemovePlant() {
		System.out.println("test remove leps");
		Model.removePlant(plant1);
		assertEquals("removing 1 plant from list", 1, Model.getLeps().size());
	}
	
	@Test
	public void testAddPlant() {
		System.out.println("test add plant");
		
		Model.getPlants().add(plant1);
		Model.getPlants().add(plant1);
		
		assertEquals("adding 1 plant to list", 2, Model.getPlants().size());
		fail();
	}
	@Test
	public void destroy() {
		assertEquals("destroying all plants",0,Model.getPlants().size());
		assertEquals("destroying all plants",0,Model.getLeps().size());
	}
	



}
