

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import model.*;

public class TestModel {
	private ArrayList<Plant> plantsTest = new ArrayList<Plant>();
	private ArrayList<Lep> lepsTest = new ArrayList<Lep>();
	private int budgetLeft = 30;

	@Before
	public void init() {
		plantsTest.add("Plant 1","Plant 1",34,"Woody");
		plantsTest.add("Plant 2");
		plantsTest.add("Plant 3");
		
		lepsTest.add("Lep 1");
		lepsTest.add("lep 2");
		lepsTest.add("lep 3");
	}
	@Test
	public void testUpdateVelocities() {
		System.out.println("test case update velocity");
	}
	
	@Test
	public void testUpdateLocation() {
		System.out.println("test case update location");
	}
	
	@Test
	public void testUpdateBudget() {
		System.out.println("test case update budget");
		assertEquals(0, Calculation.updateBudget(new int 30));
	}
	
	@Test
	public void testAddLeps() {
		System.out.println("test case add leps");
		lepsTest.add("lep 4");
		assertEquals("adding 1 more lep to list", 4, lepsTest.size());
	}
	
	@Test
	public void testRemovePlant() {
		System.out.println("test remove leps");
		plantsTest.remove("plant 3");
		assertEquals("removing 1 plant from list", 2, plantsTest.size());
	}
	
	@Test
	public void testAddPlant() {
		System.out.println("test add plant");
		plantsTest.add("plant 4");
		assertEquals("adding 1 plant to list", 4, plantsTest.size());
	}
	@After
	public void destroy() {
		plantsTest.removeAll();
		lepsTest.removeAll();
		assertEquals("destroying all plants",0,plantsTest.size());
		assertEquals("destroying all plants",0,lepsTest.size());
	}
	



}
