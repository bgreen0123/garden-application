
import org.junit.*;

import static org.junit.Assert.*;

import enums.*;
import model.*;

public class TestModel {
	//private ArrayList<Plant> plantsTest = new ArrayList<Plant>();
	//private ArrayList<Lep> lepsTest = new ArrayList<Lep>();
	//private int budgetLeft = 30;
	Plant plant1 = new Plant("Plant 1","Plant 1",34,PlantType.WOODY);
	Lep lep1 = new Lep(0,0,0,0);
	Model m = new Model();
	
	
	@Test
	public void testUpdateVelocities() {
		lep1.updateVelocities(2);
		System.out.println("update x velocity");
		assertEquals(2,lep1.getXVelocity());
		System.out.println("update y velocity");
		assertEquals(2,lep1.getYVelocity());
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
		
		assertEquals(1, Model.getLepCount());
	}
	
	@Test
	public void testRemovePlant() {
		System.out.println("test remove plant");
		Model.removePlant(plant1);
		assertEquals(1, Model.getPlants().size());
	}
	
	@Test
	public void testAddPlant() {
		System.out.println("test add plant");
		
		Model.addPlant(plant1);
		Model.addPlant(plant1);
		
		assertEquals(0, Model.getLeps().size());
		fail();
	}
	
	@Test
	public void testRemoveLeps() {
		System.out.println("test remove lep");
		
		Model.removeLep(lep1);
		
		assertEquals(3, Model.getPlants().size());
		fail();
	}

	@Test
	public void testGardenGetSet() {
		System.out.println("test garden getter and setter");
		
		Gridspace plot[][] = new Gridspace[2][2];
		Model.setGarden(plot);
		
		assertSame(plot, Model.getGarden());
		fail();
	}
	
	@Test
	public void testWidthAndHeight() {
		System.out.println("test width/height getter and setter");
		
		Model.setHeight(100);
		Model.setWidth(200);
		
		assertEquals(100, Model.getHeight());
		assertEquals(200, Model.getWidth());

		fail();
	}
	
	@Test
	public void testSunGetSet() {
		System.out.println("test sun getter and setter");
		
		Model.setSun(Sun.FULLSUN);
		
		assertEquals(Sun.FULLSUN, Model.getSun());

		fail();
	}
	
	@Test
	public void testSoilGetSet() {
		System.out.println("test soil getter and setter");
		
		Model.setSoil(Soil.SAND);
		
		assertEquals(Soil.SAND, Model.getSoil());

		fail();
	}
	
	@Test
	public void testMoistureGetSet() {
		System.out.println("test moisture getter and setter");
		
		Model.setMoisture(Moisture.WET);
		
		assertEquals(Moisture.WET, Model.getMoisture());

		fail();
	}
	
	@Test
	public void testLepCountGetSet() {
		System.out.println("test lepCount getter and setter");
		
		Model.setLepCount(5);
		
		assertEquals(5, Model.getLepCount());

		fail();
	}
	
	@Test
	public void testBudgetGetSet() {
		System.out.println("test budget getter and setter");
		
		Model.setBudget(500);
		
		assertEquals(500, Model.getBudget());

		fail();
	}
	
	@Test
	public void destroy() {
		assertEquals("destroying all plants",0,Model.getPlants().size());
		assertEquals("destroying all plants",0,Model.getLeps().size());
		fail();
	}
	



}
