package controller.testers;

import controller.Controller;

public class ControllerTester{
	private Controller myController; 
	public ControllerTester(){
		myController = new Controller(); 
	}
	
	public Controller getControl(){
		return myController;
	}
	
/*	public static void main (String[] args){
		ControllerTester test = new ControllerTester();
		
		test.getControl().processInput("product rt 20 fd 50");
		
		System.out.println("String to print " + test.getControl().getStringOutput());
	}
	*/
}
