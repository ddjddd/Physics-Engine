package com.engine.simulation;

import java.util.ArrayList;

import com.engine.thing.Thing;
import com.engine.wall.Wall;

public class Manager {
	ArrayList<Thing> things;
	ArrayList<Wall> walls;
	private int numOfThings;
	private int numOfWalls;
	static Manager manager;
	private static final int CAPACITY = 3;
	
	private Manager() {
		things = new ArrayList<>(CAPACITY);
		walls = new ArrayList<>(CAPACITY);
		numOfThings = 0;
		numOfWalls = 0;
	}
	
	public static Manager getInstance() {
		if(manager==null)
			manager = new Manager();
		return manager;
	}
	
	//PUBLIC METHOD
	public int getNumberOfThings() {
		return this.numOfThings;
	}
	
	public int getNumberOfWalls() {
		return this.numOfWalls;
	}
	public void addThing(Thing thing) {
		things.add(thing);
		numOfThings++;
	}
	
	public void addWall(Wall wall) {
		walls.add(wall);
		numOfWalls++;
	}
	
	public void removeThing(int index) {
		if(numOfThings==0) {
			System.out.println("Manager.removeThing) manager.list is empty.");
		} else if(index>=numOfThings || index<0) {
			System.out.println("Manager.removeThing) out of boundary index.");
		} else {
			things.remove(index);
			numOfThings--;
		}
	}
	
	public void removeWall(int index) {
		if(numOfWalls==0) {
			System.out.println("Manager.removeThing) manager.list is empty.");
		} else if(index>=numOfWalls || index<0) {
			System.out.println("Manager.removeThing) out of boundary index.");
		} else {
			walls.remove(index);
			numOfWalls--;
		}
	}
	
	public Thing getThing(int index) {
		if(numOfThings==0) {
			System.out.println("Manager.getThing) manager.list is empty.");
			return null;
		} 
		else if(index>=numOfThings || index<0) {
			System.out.println("Manager.getThing) out of boundary index.");
			return null;
		}
		return things.get(index);
	}
	
	public Wall getWall(int index) {
		if(numOfWalls==0) {
			System.out.println("Manager.getWall) manager.walls is empty.");
			return null;
		} 
		else if(index>=numOfWalls || index<0) {
			System.out.println("Manager.getWall) out of boundary index.");
			return null;
		}
		return walls.get(index);
	}
	
//	public boolean isStop(int index) {
//		if(numOfThings==0) {
//			System.out.println("Manager.isStop) manager.list is empty.");
//			return false;
//		} else if(index>=numOfThings || index<0) {
//			System.out.println("Manager.isStop) out of boundary index.");
//			return false;
//		}
//		return this.things.get(index).isStop();
//	}
	
//	public void printInitialStates(int frame) {
//		for(int i=0; i<numOfThings; i++)
//			things.get(i).printCurrentState(frame);
//	}
}