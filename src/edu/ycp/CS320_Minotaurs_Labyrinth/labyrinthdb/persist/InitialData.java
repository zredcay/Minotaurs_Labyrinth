package edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Choice;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Dialogue;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Gear;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.ReadCSV;

public class InitialData {

	public static List<Ability> getAbilities() throws IOException{
		
		List<Ability> abilityList = new ArrayList<Ability>();
		ReadCSV readAbilities = new ReadCSV("Ability.csv");
		try {
			while (true) {
				List<String> tuple = readAbilities.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Ability ability = new Ability(null, null, null, null, 0, 0);
				i.next();
				ability.setName(i.next());
				ability.setDescription(i.next());
				ability.setVariety(i.next());
				ability.setAffectedStat(i.next());
				ability.setEffect(Integer.parseInt(i.next()));
				ability.setCost(Integer.parseInt(i.next()));
				abilityList.add(ability);
			}
			
			System.out.println("abilitiesList loaded from CSV file");			
			return abilityList;
		} finally {
			readAbilities.close();
		}
	
	}
		
	public static List<ArrayList<Ability>> getAbilitiesList() throws IOException{
		
		List<ArrayList<Ability>> abilityList2 = new ArrayList<ArrayList<Ability>>();
		ReadCSV readAbilitiesList = new ReadCSV("AbilityList.csv");
		try {
			while (true) {
				List<String> tuple = readAbilitiesList.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				ArrayList<Ability> abilities = new ArrayList<Ability>();
				ArrayList<Ability> tmpList = (ArrayList<Ability>)getAbilities();
				i.next();
				while(i.hasNext()) {
					
					abilities.add(getAbilitybyString(tmpList, i.next()));
				}
				
				abilityList2.add(abilities);
			
			}
			
			System.out.println("abilitiesList loaded from CSV file");			
			return abilityList2;
		} finally {
			readAbilitiesList.close();
		}
		
	}

	public static ArrayList<Ability> getAbilitiesListByID(int alID) throws IOException{
		ArrayList<Ability> abilities = new ArrayList<Ability>();

		ReadCSV readAbilitiesList = new ReadCSV("AbilityList.csv");
		try {
			while (true) {
				List<String> tuple = readAbilitiesList.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				ArrayList<Ability> tmpList = (ArrayList<Ability>)getAbilities();
				
				if(Integer.parseInt(i.next()) == alID){
					while(i.hasNext()) {
						
						abilities.add(getAbilitybyString(tmpList, i.next()));
					}
					
				}
			}
			
			System.out.println("abilitiesList loaded from CSV file");			
			return abilities;
		} finally {
			readAbilitiesList.close();
		}
		
	}
	
	public static List<Inventory> getInventory() throws IOException{
		
		List<Inventory> inventoryList = new ArrayList<Inventory>();
		ReadCSV readInventory = new ReadCSV("Inventory.csv");
		try {
			while (true) {
				List<String> tuple = readInventory.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Inventory inventory = new Inventory(0, 0, null);
				i.next();
				inventory.setMaxStorage(Integer.parseInt(i.next()));
				inventory.setMaxQuant(Integer.parseInt(i.next()));
				inventory.setInventory((ArrayList<Item>)getItemListForInventory(Integer.parseInt(i.next())));
				
				inventoryList.add(inventory);
			}
			
			System.out.println("inventoryList loaded from CSV file");			
			return inventoryList;
		} finally {
			readInventory.close();
		}
	
		
		
	}
	
	public static Inventory getInventoryById(int invId) throws IOException{
		
		Inventory inventory = new Inventory(0, 0, null);
		ReadCSV readInventory = new ReadCSV("Inventory.csv");
		try {
			while (true) {
				List<String> tuple = readInventory.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				if(Integer.parseInt(i.next()) == invId) {
					inventory.setMaxStorage(Integer.parseInt(i.next()));
					inventory.setMaxQuant(Integer.parseInt(i.next()));
					inventory.setInventory((ArrayList<Item>)getItemListForInventory(Integer.parseInt(i.next())));
				}
			}
			
			System.out.println("inventory loaded from CSV file");			
			return inventory;
		} finally {
			readInventory.close();
		}
	
		
		
	}
	
	public static List<ArrayList<Item>> getItemList() throws IOException{
		
		List<ArrayList<Item>> itemList2 = new ArrayList<ArrayList<Item>>();
		ReadCSV readItemList = new ReadCSV("ItemList.csv");
		try {
			while (true) {
				List<String> tuple = readItemList.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				ArrayList<Item> items = new ArrayList<Item>();
				ArrayList<Item> tmpList = (ArrayList<Item>) getItems();
				ArrayList<Gear> gearList = (ArrayList<Gear>) getGear();
				i.next();
				while(i.hasNext()) {
					
					String itemName = i.next();
					
					if(!getItembyString(tmpList, itemName).getName().equals("gear")) {
						items.add(getItembyString(tmpList, itemName));
					}else {
						items.add(getGearbyString(gearList, itemName));
					}
				}
				
				itemList2.add(items);
			
			}
			
			System.out.println("ArrayList<Item> loaded from CSV file");			
			return itemList2;
		} finally {
			readItemList.close();
		}
		
	}

	
	public static List<Item> getItems() throws IOException{
		
		List<Item> itemList = new ArrayList<Item>();
		ReadCSV readItem = new ReadCSV("Item.csv");
		try {
			while (true) {
				List<String> tuple = readItem.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Item item = new Item(null, 0, null, null, null, 0, null, null, null);
				i.next();
				item.setDescription(i.next());
				item.setEffect(Integer.parseInt(i.next()));
				
				if(Integer.parseInt(i.next())==0) {
					item.setFlammable(false);
				}else {
					item.setFlammable(true);
				}
								
				if(Integer.parseInt(i.next())==0) {
				
					item.setLit(false);
				}else {
					
					item.setLit(true);
				}
				
				
				
				if(Integer.parseInt(i.next())==0) {
					item.setThrowable(false);
				}else {
					item.setThrowable(true);
				}
				
				item.setValue(Integer.parseInt(i.next()));
				
				item.setName(i.next());
				
				item.setVariety(i.next());
				
				item.setAffectedStat(i.next());
				
				itemList.add(item);
				
			}
			
			System.out.println("itemList loaded from CSV file");			
			return itemList;
		} finally {
			readItem.close();
		}
	}
	
	public static Item getItemByID(int itemID) throws IOException{
		
		Item item = new Item(null, 0, null, null, null, 0, null, null, null);
		ReadCSV readItem = new ReadCSV("Item.csv");
		try {
			while (true) {
				List<String> tuple = readItem.next();
				if (tuple == null) {
					break;
				}
				
				Iterator<String> i = tuple.iterator();
				if(Integer.parseInt(i.next()) == itemID) {
					item.setDescription(i.next());
					item.setEffect(Integer.parseInt(i.next()));
					
					if(Integer.parseInt(i.next())==0) {
						item.setFlammable(false);
					}else {
						item.setFlammable(true);
					}
									
					if(Integer.parseInt(i.next())==0) {
					
						item.setLit(false);
					}else {
						
						item.setLit(true);
					}
					
					
					
					if(Integer.parseInt(i.next())==0) {
						item.setThrowable(false);
					}else {
						item.setThrowable(true);
					}
					
					item.setValue(Integer.parseInt(i.next()));
					
					item.setName(i.next());
					
					item.setVariety(i.next());
					
					item.setAffectedStat(i.next());
				
				}				
			}
			
			System.out.println("item loaded from CSV file");			
			return item;
		} finally {
			readItem.close();
		}
	}

	public static List<Gear> getGear() throws IOException{
		
		List<Gear> gearList = new ArrayList<Gear>();
		ReadCSV readGear = new ReadCSV("Gear.csv");
		try {
			while (true) {
				List<String> tuple = readGear.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Gear gear = new Gear(0, 0, 0, null, null, null, 0, null, null, null, 0, null, null);
				
				i.next();
				
				gear.setAtk(Integer.parseInt(i.next()));
				gear.setDef(Integer.parseInt(i.next()));
				gear.setHP(Integer.parseInt(i.next()));
				gear.setVariety(i.next());
				
				if(Integer.parseInt(i.next())==0) {
					gear.setEquipped(false);
				}else {
					gear.setEquipped(true);
				}
				
				gear.setDescription(i.next());
				gear.setEffect(Integer.parseInt(i.next()));
				
				if(Integer.parseInt(i.next())==0) {
					gear.setFlammable(false);
				}else {
					gear.setFlammable(true);
				}				
				
				
				if(Integer.parseInt(i.next())==0) {
					gear.setLit(false);
				}else {
					gear.setLit(true);
				}
				
				
				if(Integer.parseInt(i.next())==0) {
					gear.setThrowable(false);
				}else {
					gear.setThrowable(true);
				}
				
				gear.setValue(Integer.parseInt(i.next()));
				
				gear.setName(i.next());
				
				gear.setAffectedStat(i.next());
				
				gearList.add(gear);
				
			}
			
			System.out.println("gearList loaded from CSV file");			
			return gearList;
		} finally {
			readGear.close();
		}
	}
	
	public static List<Item> getItemListForInventory(int invId) throws IOException{
		ArrayList<Item> items = new ArrayList<Item>();
		ReadCSV readItemList = new ReadCSV("ItemList.csv");
		try {
			while (true) {
				List<String> tuple = readItemList.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				
				if(Integer.parseInt(i.next()) == invId) {
					
					ArrayList<Item> tmpList = (ArrayList<Item>) getItems();
					ArrayList<Gear> gearList = (ArrayList<Gear>) getGear();
					
					String itemName = i.next();
					
					if(!getItembyString(tmpList, itemName).getName().equals("gear")) {
						items.add(getItembyString(tmpList, itemName));
					}else {
						items.add(getGearbyString(gearList, itemName));
					}
				}
			
			}
			
			System.out.println("ArrayList<Item> loaded from CSV file");			
			return items;
			} finally {
			readItemList.close();
		}
		
	}
	
	public static List<Room> getRooms() throws IOException{
		
		List<Room> roomList = new ArrayList<Room>();
		ReadCSV readRooms = new ReadCSV("Room.csv");
		try {
			while (true) {
				List<String> tuple = readRooms.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Room room = new Room(null, null, null, null, false, 0);
				
				
				room.setRoomId(Integer.parseInt(i.next()));
				
				room.setDescription(i.next());
				
				
				room.setInventory(getInventoryById(Integer.parseInt(i.next())));
				
				List<Obstacle> tmpList2 = getObstacles();
				room.setObstacle(tmpList2.get(Integer.parseInt(i.next())-1));
				
				if(Integer.parseInt(i.next())==0) {
					room.setIsFound(false);
				}else {
					room.setIsFound(true);	
				}
				
				HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
				
				while(i.hasNext()) {
					tmpMap.put(i.next(), Integer.parseInt(i.next()));
				}
				
				room.setRoomMap(tmpMap);
				
				roomList.add(room);
			}
			
			System.out.println("roomList loaded from CSV file");			
			return roomList;
		} finally {
			readRooms.close();
		}
	
	}
	
	public static Room getRoomByID(int roomID) throws IOException{
		
		Room room = new Room(null, null, null, null, false, 0);
		ReadCSV readRooms = new ReadCSV("Room.csv");
		try {
			while (true) {
				List<String> tuple = readRooms.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				int roomNum = Integer.parseInt(i.next());
				if(roomNum == roomID) {
					room.setRoomId(roomNum);
					
					room.setDescription(i.next());
					
					
					room.setInventory(getInventoryById(Integer.parseInt(i.next())));
					
					List<Obstacle> tmpList2 = getObstacles();
					room.setObstacle(tmpList2.get(Integer.parseInt(i.next())-1));
					
					if(Integer.parseInt(i.next())==0) {
						room.setIsFound(false);
					}else {
						room.setIsFound(true);	
					}
					
					HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
					
					while(i.hasNext()) {
						tmpMap.put(i.next(), Integer.parseInt(i.next()));
					}
					
					room.setRoomMap(tmpMap);
				}
			}
			
			System.out.println("room loaded from CSV file");			
			return room;
		} finally {
			readRooms.close();
		}
	
	}
	
	public static List<Obstacle> getObstacles() throws IOException{
		
		List<Obstacle> obstacleList = new ArrayList<Obstacle>();
		ReadCSV readObstacle = new ReadCSV("Obstacle.csv");
		try {
			while (true) {
				List<String> tuple = readObstacle.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Obstacle obstacle = new Obstacle(null, null, null, null);
				i.next();
				obstacle.setDescription(i.next());
				
				obstacle.setStatus(i.next());
								
				obstacle.setRequirement(getItemByID(Integer.parseInt(i.next())));
				
				obstacle.setName(i.next());
				
				obstacleList.add(obstacle);
				
			}
			
			System.out.println("obstacleList loaded from CSV file");			
			return obstacleList;
		} finally {
			readObstacle.close();
		}
	
	}

	
	public static List<Enemy> getEnemies() throws IOException {
		List<Enemy> enemyList = new ArrayList<Enemy>();
		ReadCSV readEnemies = new ReadCSV("Enemy.csv");
		try {
			while (true) {
				List<String> tuple = readEnemies.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Enemy enemy = new Enemy(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, false);
				i.next();
				enemy.setMaxHP(Integer.parseInt(i.next()));
				enemy.setHP(Integer.parseInt(i.next()));
				enemy.setMaxResource(Integer.parseInt(i.next()));
				enemy.setResource(Integer.parseInt(i.next()));
				enemy.setAtk(Integer.parseInt(i.next()));
				enemy.setDef(Integer.parseInt(i.next()));
				enemy.setGold(Integer.parseInt(i.next()));
				enemy.setXP(Integer.parseInt(i.next()));

				enemy.setAbilities(getAbilitiesListByID(Integer.parseInt(i.next())));
				enemy.setStatus(i.next());
				enemy.setDialogue(i.next());
				enemy.setAttitude(Integer.parseInt(i.next()));
				enemy.setDescription(i.next());
				enemy.setName(i.next());
				
				enemy.setInventory(getInventoryById(Integer.parseInt(i.next())));
				
				enemy.setCurrentRoom(getRoomByID(Integer.parseInt(i.next())));
				
				if(Integer.parseInt(i.next())==0) {
					enemy.setIsDead(false);
				}else {
					enemy.setIsDead(true);
				}
				
				enemyList.add(enemy);
			
			}
				
			
			System.out.println("enemyList loaded from CSV file");			
			return enemyList;
		} finally {
			readEnemies.close();
		}
	}
	
	public static List<Player> getPlayers() throws IOException {
	List<Player> playerList = new ArrayList<Player>();
	ReadCSV readPlayers = new ReadCSV("Player.csv");
	try {
		while (true) {
			List<String> tuple = readPlayers.next();
			if (tuple == null) {
				break;
			}
			Iterator<String> i = tuple.iterator();
			Player player = new Player(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, false, null);
			i.next();
			player.setMaxHP(Integer.parseInt(i.next()));
			player.setHP(Integer.parseInt(i.next()));
			player.setMaxResource(Integer.parseInt(i.next()));
			player.setResource(Integer.parseInt(i.next()));
			player.setAtk(Integer.parseInt(i.next()));
			player.setDef(Integer.parseInt(i.next()));
			player.setGold(Integer.parseInt(i.next()));
			player.setXP(Integer.parseInt(i.next()));
			player.setAbilities(getAbilitiesListByID(Integer.parseInt(i.next())));
			player.setStatus(i.next());
			
			player.setInventory(getInventoryById(Integer.parseInt(i.next())));

			
			player.setCurrentRoom(getRoomByID(Integer.parseInt(i.next())));
			if(Integer.parseInt(i.next())==0) {
				player.setIsDead(false);
			}else {
				player.setIsDead(true);
			}
			player.setName(i.next());

			playerList.add(player);
		
		}
			
		
		System.out.println("playerList loaded from CSV file");			
		return playerList;
	} finally {
		readPlayers.close();
	}
}

	public static List<NPC> getNPCs() throws IOException {
		List<NPC> npcList = new ArrayList<NPC>();
		ReadCSV readNPCs = new ReadCSV("NPC.csv");
		try {
			while (true) {
				List<String> tuple = readNPCs.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				NPC npc = new NPC(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, false);
				i.next();
				npc.setMaxHP(Integer.parseInt(i.next()));
				npc.setHP(Integer.parseInt(i.next()));
				npc.setMaxResource(Integer.parseInt(i.next()));
				npc.setResource(Integer.parseInt(i.next()));
				npc.setAtk(Integer.parseInt(i.next()));
				npc.setDef(Integer.parseInt(i.next()));
				npc.setGold(Integer.parseInt(i.next()));
				npc.setXP(Integer.parseInt(i.next()));
				npc.setAbilities(getAbilitiesListByID(Integer.parseInt(i.next())));
				npc.setStatus(i.next());
				npc.setDialogue(i.next());
				npc.setAttitude(Integer.parseInt(i.next()));
				npc.setDescription(i.next());
				npc.setName(i.next());
				
				npc.setInventory(getInventoryById(Integer.parseInt(i.next())));
				
				npc.setCurrentRoom(getRoomByID(Integer.parseInt(i.next())));
				
				if(Integer.parseInt(i.next())==0) {
					npc.setIsDead(false);
				}else {
					npc.setIsDead(true);
				}
				
				npcList.add(npc);
			
			}
				
			
			System.out.println("enemyList loaded from CSV file");			
			return npcList;
		} finally {
			readNPCs.close();
		}
	}

	public static List<Message<String, Integer>> getTextHistory() throws IOException{
		
		List<Message<String, Integer>> messageList = new ArrayList<Message<String, Integer>>();
		ReadCSV readTextHistory = new ReadCSV("TextHistory.csv");
		try {
			while (true) {
				List<String> tuple = readTextHistory.next();
				if (tuple == null) {
					break;
				}
				
				
				Iterator<String> i = tuple.iterator();
				
				Message<String, Integer> message = new Message<String, Integer>(null, 0);
				
				i.next();
				
				message.setMessage(i.next());
				
				message.setPlayerAction(Integer.parseInt(i.next()));
				
				messageList.add(message);
				
			}
			
			System.out.println("textHistory loaded from CSV file");			
			return messageList;
		} finally {
			readTextHistory.close();
		}
	}
	
	public static List<Dialogue> getDialogues() throws IOException{
		
		List<Dialogue> dialogueList = new ArrayList<Dialogue>();
		ReadCSV readDialogue = new ReadCSV("Dialogue.csv");
		try {
			while (true) {
				List<String> tuple = readDialogue.next();
				if (tuple == null) {
					break;
				}
				
				
				Iterator<String> i = tuple.iterator();
				
				Dialogue dialogue = new Dialogue(null, 0, 0, 0, 0);
				
				i.next();
				
				dialogue.setNPC_ID(Integer.parseInt(i.next()));
				
				dialogue.setQuestion(i.next());
				
				dialogue.setChoice1(Integer.parseInt(i.next()));
				dialogue.setChoice2(Integer.parseInt(i.next()));
				dialogue.setChoice3(Integer.parseInt(i.next()));

				
				dialogueList.add(dialogue);
				
			}
			
			System.out.println("Dialogue loaded from CSV file");			
			return dialogueList;
		} finally {
			readDialogue.close();
		}
	}

	public static List<Choice> getChoices() throws IOException{
	
		List<Choice> choiceList = new ArrayList<Choice>();
		ReadCSV readChoices = new ReadCSV("Choices.csv");
		try {
			while (true) {
				List<String> tuple = readChoices.next();
				if (tuple == null) {
					break;
				}
			
			
				Iterator<String> i = tuple.iterator();
			
				Choice choice = new Choice(0, 0, null, null, null);
			
				choice.setNPC_ID(Integer.parseInt(i.next()));
			
				choice.setChoice_ID(Integer.parseInt(i.next()));
				
				choice.setChoice(i.next());
			
				choice.setResponse(i.next());
				
				choice.setStatus(i.next());
				
				choiceList.add(choice);
			
			}
		
			System.out.println("Choices loaded from CSV file");			
			return choiceList;
		} finally {
			readChoices.close();
		}
	}
	
	public static Ability getAbilitybyString(ArrayList<Ability> abilities, String name) {
        for(int i = 0; i < abilities.size(); i++) {
            if(abilities.get(i).getName().equals(name)) {
                return abilities.get(i);
            }
        }
        return null;
    }


	public static Item getItembyString(ArrayList<Item> Item, String name) {
        for(int i = 0; i < Item.size(); i++) {
            if(Item.get(i).getName().equals(name)) {
                return Item.get(i);
            }
        }
        
        return new Item(null, 0, null, null, null, 0, "gear", null, null);
    }
	
	public static Gear getGearbyString(ArrayList<Gear> Gear, String name) {
        for(int i = 0; i < Gear.size(); i++) {
            if(Gear.get(i).getName().equals(name)) {
                return Gear.get(i);
            }
        }
        return new Gear(0, 0, 0, null, null, null, 0, null, null, null, 0, "item", null);
    }
}

