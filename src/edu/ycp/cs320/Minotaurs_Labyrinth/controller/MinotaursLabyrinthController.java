package edu.ycp.cs320.Minotaurs_Labyrinth.controller;

import edu.ycp.cs320.Minotaurs_Labyrinth.model.Minotaur;

public class MinotaursLabyrinthController {
	
	private Minotaur model;
	
	public void setModel(Minotaur model) {
		this.model = model;
	}
	
	public Minotaur getModel() {
		return this.model;
	}
	
	//Calls model methods that create the map, rooms, and various game objects
	/*public void initModel() {
		//might do something later
	}*/
}
