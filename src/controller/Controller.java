package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import gui.UIMain;
import gui.tableviews.UIVariablesView;
import model.*;
import model.command.TreeNode;
import model.turtle.State;
import model.turtle.Turtle;
import model.turtle.TurtleState;
import parser.main.NewParser;
import parser.multipleturtleparsing.MapMaker;
import parser.multipleturtleparsing.StringListCreator;
import parser.multipleturtleparsing.SubListProcessor;
import parser.storage.*;
import parser.storage.VariableStorage;
import parser.tokenizer.ProtectedTokenList;
import parser.main.Compiler;

public class Controller {


	public class myHandler implements ControlHandler{ // front end to controller 
		@Override
		public void handleTextInput(String input){
			processInput(input);
		}
		@Override
		public void handleReset(){
			reset();
		}

		public void setLanguage(String language){

			changeLanguage(language);
		}

	}

	public class modelHandler implements BackEndHandler{ // controller to model 

		@Override
		public void setBackground(double index) {
			changeBackground(index); 

		}

		@Override
		public void setPalette(double index, double r, double g, double b) {
			changePalette(index, r,g,b);	
		}

		public void handleReset(){
			reset(); 
		}

		@Override
		public double getNumTurtle() {
			// TODO Auto-generated method stub
			return getNums(); 
		}
		
	

		public void setRelationship(Double id){
			setRelation( id); 
		}

	}


	private ModelController myModel; 
	private List<Turtle> myTurtles;

	private Map<Double,List<String>> myLiteralMap;

	private NewParser myParser;
	private Compiler compiler;

	private UIMain myViewController;
	private String output; 
	private final String languageLocation = "resources.languages/";
	private StringBuilder currentLang; 
	private final Double DEFAULT_TURTLE_ID = 1.0; 


	public Controller(){
		myModel = new ModelController(new modelHandler()); 
		myTurtles = myModel.getTurtles();

		myLiteralMap = new HashMap<Double,List<String>>();
		myLiteralMap.put(DEFAULT_TURTLE_ID, new ArrayList<String>());
		
		myViewController = new UIMain(new myHandler(), "English"); // handler currently Front to Back

		// set the observable/observer relationship for the first turtle - we can make this into a method. 
		myTurtles.get(0).getState().addObserver(myViewController.addTurtle(myTurtles.get(0).getState())); 

		changeLanguage("English");
		compiler = new Compiler(); 
		configureVariableStorage(); 
	}
	private String getLang(){
		return currentLang.toString();
	}
	private void setRelation(Double id){
		 findTurtle(id).addObserver(myViewController.addTurtle(findTurtle(id)));
	}
	
	private double getNums() {
		// TODO Auto-generated method stub
		return myModel.getTurtles().size();
	}
	
	private void configureVariableStorage() {
		VariableStorage vars = compiler.getVars(); 
		UIVariablesView variableBox = myViewController.getVariableView();
		vars.addObserver(variableBox);

	}

	private void changeBackground(double index){
		myViewController.setBackgroundColor(index);
	}


	private void changePalette(double index, double r, double g, double b){
		myViewController.setPaletteAtIndex(index,r,g,b);
	}

	private void changeLanguage(String language) {
		currentLang = new StringBuilder(); 
		currentLang.append(languageLocation);
		currentLang.append(language);
		myParser = new NewParser(currentLang.toString());
		System.out.println(currentLang.toString());
	}


	private void processInput(String input){
		try{
			ProtectedTokenList list = myParser.parse(input);
			// clear literalMAp here 
			myLiteralMap.clear();
			
			
			StringListCreator SLC = new StringListCreator(list.getLiterals());
			List<List<String>> subs = SLC.getSublists();
			List<List<String>> cleanSubs = cleanSubLists(subs);
			
			SubListProcessor SLP = new SubListProcessor(cleanSubs, myModel);
			
			

			List<String> remainingComms = SLP.getRemainingCommands();
			List<String> precedingComms = SLP.getPrecedingCommands();
			List<Object> comms = SLP.getCommandObjects();
			
			
			
			MapMaker MM = new MapMaker(comms, precedingComms,  remainingComms, myLiteralMap, myModel,getLang());
			
			
			
			Map<Double, ProtectedTokenList> turtlesToCommands = MM.getPTLMap();
			
			
			
			printTokenList(turtlesToCommands);
			

			// refactor into a method 
			
			for (Double turtleId: turtlesToCommands.keySet()){
				
				ProtectedTokenList commandsToApply = turtlesToCommands.get(turtleId);
				
				TurtleState t = findTurtle(turtleId);
				Queue<TreeNode> Q = compiler.compile(t, commandsToApply); 
				

				myModel.update(Q);
				
				output = myModel.getStringOutput();
				myViewController.addNewOutput(output);
				
			}
			
			myViewController.getFunctionsBox().setFunctionBox(compiler.getFunctionList());

		}
		catch (SLogoException e){ 
			myViewController.displayErrorWithMessage(e.getMessage());
		}
		output = myModel.getStringOutput();
	}

	private List<List<String>> cleanSubLists(List<List<String>> subs) {
		List<List<String>> cleanLists = new ArrayList<List<String>>(); 
		for (List<String> l : subs){
			if (!(l.isEmpty())){
				cleanLists.add(l);
			}
		}
		return cleanLists; 
	}

	private void printTokenList(Map<Double, ProtectedTokenList> p) {
		for (Double key : p.keySet()){
			System.out.println("turtle is " + key);
			System.out.println("literals are " + p.get(key).getLiterals());
			System.out.println("logo is " + p.get(key).getLogo() );
		}
		
	}

	private TurtleState findTurtle (Double turtleId) {
		try{
		for (Turtle t: myModel.getTurtles()){
			if (t.getID() == turtleId){
				return t.getState();
			}
		}
		}
		catch(SLogoException e){
			throw new SLogoException("turtle does not exist");
		}
		return null; 
	}
 

	public String getStringOutput(){
		return output; 
	}

	private void reset(){
		myModel.reset(); // order important 
 		myViewController.clearScreen();
		
	}
	
	public UIMain getViewController(){
		return myViewController;
	}

}