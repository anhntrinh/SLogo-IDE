package model.multipleturtle;

import java.util.ArrayList;
import java.util.List;

import controller.SLogoException;

public class AskTellData {
	private ArrayList<String> turtles; 
	private ArrayList<String> commands; 
	private ArrayList<Double> turtleIDs; 
	
	public AskTellData(ArrayList<String> t, ArrayList<String> c){
		turtles = t; 
		commands = c; 
		turtleIDs = new ArrayList<Double>();
		configTurtleIDs(); 
		
		
		
	}
	private void configTurtleIDs() throws SLogoException{
		for (String turtle: turtles){
			try{
				turtleIDs.add(Double.parseDouble(turtle));
			}
			catch(SLogoException e){
				throw new SLogoException("invalid Turtle ID");
			}
			
		}
		
	}
	public List<String> getTurtles(){
		return turtles; 
	}
	public List<String> getCommands(){
		return commands; 
	}
	
	public List<Double> getTurtleIDS(){
		return turtleIDs; 
	}
	
	public void printCommands(){
		System.out.println(commands);
	}
	
	
}
