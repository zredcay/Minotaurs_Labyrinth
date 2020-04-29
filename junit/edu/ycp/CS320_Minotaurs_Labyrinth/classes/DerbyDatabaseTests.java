package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DatabaseProvider;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DerbyDatabase;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.IDatabase;


public class DerbyDatabaseTests {

	private IDatabase db = null;
	
	ArrayList<Player> Players = null;
	ArrayList<Enemy>   Enemies   = null;
	ArrayList<NPC> NPCs = null;
	ArrayList<Message<String, Integer>>   TextHistory   = null;
	ArrayList<Item> Item = null, ItemList = null;
	ArrayList<Room> Rooms = null;
	ArrayList<Obstacle> Obstacles = new ArrayList<Obstacle>();
	ArrayList<Inventory> Inventorys = new ArrayList<Inventory>();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAllPlayers() {
		System.out.println("\n*** Testing FindAllPlayers ***");
		Players = (ArrayList<Player>) db.findAllPlayers();
		
		if (Players.isEmpty()) {
			System.out.println("No players found in Labyrinth Database");
			fail("No players returned from Library DB");
		}
		
		else {			
			for (Player Plyr : Players) {
				
				System.out.println(Plyr.getAtk() + ", " + Plyr.getDef() + ", " + Plyr.getGold() + ", " + Plyr.getHP() + ", " + Plyr.getMaxHP() + ", " + Plyr.getMaxResource() + ", " + Plyr.getName() + ", " + Plyr.getResource() + ", " + Plyr.getStatus() + ", " + Plyr.getXP() + ", " + Plyr.getAbilities() + ", " + Plyr.getCurrentRoom() + ", " + Plyr.getInventory() + ", " + Plyr.getIsDead());
			}			
		}
		
	}
	
	@Test
	public void testFindAllRooms() {
		System.out.println("\n*** Testing FindAllRooms ***");
		Rooms = (ArrayList<Room>) db.findAllRooms();
		
		if (Rooms.isEmpty()) {
			System.out.println("No rooms found in Labyrinth Database");
			fail("No rooms returned from Library DB");
		}
		
		else {			
			for (Room room : Rooms) {
				
				System.out.println(room.getDescription() + ", " + room.getRoomId() + ", " + room.getInventory() + ", " + room.getIsFound() + ", " + room.getObstacle() + ", " + room.getRoomMap());
			}			
		}
	}
	
	@Test
	public void testFindAllEnemies() {
		System.out.println("\n*** Testing FindAllEnemies ***");
		Enemies = (ArrayList<Enemy>) db.findAllEnemies();
		
		if (Enemies.isEmpty()) {
			System.out.println("No Enemies found in Labyrinth Database");
			fail("No enemies returned from Library DB");
		}
		
		else {			
			for (Enemy e : Enemies) {
				
				System.out.println(e.getAtk() + ", " + e.getDef() + ", " + e.getGold() + ", " + e.getHP() + ", " + e.getMaxHP() + ", " + e.getMaxResource() + ", " + e.getName() + ", " + e.getResource() + ", " + e.getStatus() + ", " + e.getXP() + ", " + e.getAbilities() + ", " + e.getCurrentRoom() + ", " + e.getInventory() + ", " + e.getIsDead() + ", " + e.getDescription() + ", " + e.getDialogue() + ", " + e.getAttitude());
			}			
		}
		
	}
	
	@Test
	public void testFindAllNPCs() {
		System.out.println("\n*** Testing FindAllNPCs ***");
		NPCs = (ArrayList<NPC>) db.findAllNPCs();
		
		if (NPCs.isEmpty()) {
			System.out.println("No NPCs found in Labyrinth Database");
			fail("No NPCs returned from Library DB");
		}
		
		else {			
			for (NPC n : NPCs) {
				
				System.out.println(n.getAtk() + ", " + n.getDef() + ", " + n.getGold() + ", " + n.getHP() + ", " + n.getMaxHP() + ", " + n.getMaxResource() + ", " + n.getName() + ", " + n.getResource() + ", " + n.getStatus() + ", " + n.getXP() + ", " + n.getAbilities() + ", " + n.getCurrentRoom() + ", " + n.getInventory() + ", " + n.getIsDead() + ", " + n.getDescription() + ", " + n.getDialogue() + ", " + n.getAttitude());
			}			
		}
	}
	
	@Test
	public void testFindTextHistory() {
		System.out.println("\n*** Testing FindTextHistory ***");
		TextHistory = (ArrayList<Message<String, Integer>>) db.findTextHistory();
		if (TextHistory.isEmpty()) {
			System.out.println("No TextHistory found in Labyrinth Database");
			fail("No TextHistory returned from Library DB");
		}
		
		else {		
			for (Message<String, Integer> m : TextHistory) {
				
				System.out.print(m.getMessage() + ", ");
			}			
		}
	}
	
	@Test
	public void testFindItems() {
		System.out.println("\n*** Testing FindAllItems ***");
		Item = (ArrayList<Item>) db.findAllItems();
		if (Item.isEmpty()) {
			System.out.println("No Items found in Labyrinth Database");
			fail("No Items returned from Library DB");
		}
		
		else {		
			for (Item item : Item) {
				System.out.println(item.getDescription() + ", " + item.getEffect() + ", "
				+ item.getFlammable() + ", " + item.getLit() + ", " + item.getThrowable() + ", " + item.getValue() + ", " + item.getName() + ", " 
				+ item.getVariety() + ", " + item.getAffectedStat());

			}			
		}
	}
	
	@Test
	public void testFindItemList() {
		System.out.println("\n*** Testing FindItemList ***");
		ItemList = (ArrayList<Item>) db.findItemList(1);
		if (ItemList.isEmpty()) {
			System.out.println("No ItemLists found in Labyrinth Database");
			fail("No ItemLists returned from Library DB");
		}
		
		else {		
			for (Item item : ItemList) {
				System.out.println(item.getDescription() + ", " + item.getEffect() + ", "
				+ item.getFlammable() + ", " + item.getLit() + ", " + item.getThrowable() + ", " + item.getValue() + ", " + item.getName() + ", " 
				+ item.getVariety() + ", " + item.getAffectedStat());

			}			
		}
	}
	
	@Test
	public void testFindInventory() {
		System.out.println("\n*** Testing FindInventory ***");
		Inventorys.add(db.findInventory(1));
		if (Inventorys.isEmpty()) {
			System.out.println("No Inventorys found in Labyrinth Database");
			fail("No Inventorys returned from Library DB");
		}
		
		else {		
			for (Inventory inv : Inventorys) {
				System.out.println(inv.getMaxQuant() + ", " + inv.getMaxStorage() + ", " + inv.getInventory());
			}
		}
	}
	
	@Test
	public void testFindObstacle() {
		System.out.println("\n*** Testing FindObstacle ***");
		Obstacles.add(db.findObstacle(1));
		if (Obstacles.isEmpty()) {
			System.out.println("No Obstacles found in Labyrinth Database");
			fail("No Obstacles returned from Library DB");
		}
		
		else {		
			for (Obstacle obs : Obstacles) {
				System.out.println(obs.getDescription() + ", " + obs.getStatus() + ", " + obs.getRequirement().getName());
			}
		}
	}
	
	
	
}
