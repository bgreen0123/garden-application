
import org.junit.*;

import static org.junit.Assert.*;

import enums.*;
import model.*;

public class TestModel {
	//private ArrayList<Plant> plantsTest = new ArrayList<Plant>();
	//private ArrayList<Lep> lepsTest = new ArrayList<Lep>();
	//private int budgetLeft = 30;
	Plant plant1 = new Plant("Plant 1","Plant 1",34,PlantType.WOODY, Soil.CLAY, Sun.FULLSUN, Moisture.WET, "ImageURL");
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
		m.updateBudget(29);
		assertEquals(0, m.getBudget());
	}
	
	@Test
	public void testAddLeps() {
		System.out.println("test case add leps");
		m.addLeps(lep1);
		
		assertEquals(1, m.getNumLeps());
	}
	
	@Test
	public void testRemovePlant() {
		System.out.println("test remove plant");
		m.removePlant(plant1);
		assertEquals(1, m.getPlants().size());
	}
	
	@Test
	public void testAddPlant() {
		System.out.println("test add plant");
		
		m.addPlant(plant1);
		m.addPlant(plant1);
		
		assertEquals(0, m.getLeps().size());
		fail();
	}
	
	@Test
	public void testRemoveLeps() {
		System.out.println("test remove lep");
		
		m.removeLep(lep1);
		
		assertEquals(3, m.getPlants().size());
		fail();
	}

	@Test
	public void testGardenGetSet() {
		System.out.println("test garden getter and setter");
		
		Gridspace plot[][] = new Gridspace[2][2];
		m.setGarden(plot);
		
		assertSame(plot, m.getGarden());
		fail();
	}
	
	@Test
	public void testWidthAndHeight() {
		System.out.println("test width/height getter and setter");
		
		m.setHeight(100);
		m.setWidth(200);
		
		assertEquals(100, m.getHeight());
		assertEquals(200, m.getWidth());

		fail();
	}
	
	@Test
	public void testSunGetSet() {
		System.out.println("test sun getter and setter");
		
		m.setSun(Sun.FULLSUN);
		
		assertEquals(Sun.FULLSUN, m.getSun());

		fail();
	}
	
	@Test
	public void testSoilGetSet() {
		System.out.println("test soil getter and setter");
		
		m.setSoil(Soil.DIRT);
		
		assertEquals(Soil.DIRT, m.getSoil());

		fail();
	}
	
	@Test
	public void testMoistureGetSet() {
		System.out.println("test moisture getter and setter");
		
		m.setMoisture(Moisture.WET);
		
		assertEquals(Moisture.WET, m.getMoisture());

		fail();
	}
	
	@Test
	public void testLepCountGetSet() {
		System.out.println("test lepCount getter and setter");
		
		m.updateLepCount(5);
		
		assertEquals(5, m.getNumLeps());

		fail();
	}
	
	@Test
	public void testBudgetGetSet() {
		System.out.println("test budget getter and setter");
		
		m.updateBudget(500);
		
		assertEquals(500, m.getBudget());

		fail();
	}
	
	@Test
	public void destroy() {
		assertEquals("destroying all plants",0,m.getPlants().size());
		assertEquals("destroying all plants",0,m.getLeps().size());
		fail();
	}
	



}
