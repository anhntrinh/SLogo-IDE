package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Queue;

import controller.BackEndHandler;

import model.command.Command;
import model.command.TreeNode;
import model.turtle.Turtle;
/**
 * this class is responsible for managing all Turtles and executing commands returned from 
 * the parser/compiler component.
 * @authors Anh and Tahia
 *
 */
public class ModelController {
	
	List<Turtle> myTurtles; 
	List<Double> myTurtleIDs; 
	List<Double> myActiveTurtleIDs;

	String myOutput; 
	BackEndHandler myHandler; 
	
	private final Double DEFAULT_TURTLE_ID = 1.0; 

	public ModelController(BackEndHandler handler){	
		myTurtles = new ArrayList<Turtle>();
		myTurtles.add(new Turtle(DEFAULT_TURTLE_ID)); 
		
		myTurtleIDs = new ArrayList<Double>(); 
		myTurtleIDs.add(DEFAULT_TURTLE_ID);
		
		
		myActiveTurtleIDs = new ArrayList<Double>();
		myActiveTurtleIDs.add(DEFAULT_TURTLE_ID);
		
		myHandler = handler; 	 
	}

	/**
	 * this method executes a queue of commands returned from the parser component
	 * @param commandsToExecute queue of commands as tree nodes
	 */
	public void update(Queue<TreeNode> commandsToExecute){
		myOutput = "";

		while(!commandsToExecute.isEmpty()){ 
			TreeNode command= commandsToExecute.remove();
			((Command) command).execute(myHandler);
			myOutput = ((Double) command.getValue()).toString();
			
		}
	}
	
	/**
	 * 
	 * @return unmodifiable list of all turtles
	 */
	public List<Turtle> getTurtles(){
		return Collections.unmodifiableList(myTurtles);  
	}
	
	/**
	 * 
	 * @return unmodifiable list of all turtle ids
	 */
	public List<Double> getTurtleIDs(){
		return Collections.unmodifiableList(myTurtleIDs); 
	}
	/**
	 * 
	 * @return unmodifiable list of active turtle ids
	 */
	public List<Double> getActiveTurtleIDs(){
		return Collections.unmodifiableList(myActiveTurtleIDs);
	}
	/**
	 * 
	 * @return the output to be displayed on the terminal after each execution of user input
	 */
	public String getStringOutput(){
		return myOutput; 
	}

	/**
	 * bring all turtles to home position
	 */
	public void reset() {
		for (Turtle t: myTurtles){
			t.reset();
		}
	}
	/**
	 * 
	 * @param id the id of the turtle to be created
	 * @return the newly created Turtle
	 */
	public Turtle makeNewTurtle(double id){
		Turtle newTurtle = new Turtle(id);
		myTurtles.add(newTurtle);
		myTurtleIDs.add(id);
		myHandler.setRelationship(id);
		return newTurtle;
	}
	/**
	 * 
	 * @param actives the list of ids of turtles to be set as active
	 */
	public void setActiveTurtles(List<Double> actives){
		myActiveTurtleIDs.clear();
		myActiveTurtleIDs.addAll(actives);
	}

}
