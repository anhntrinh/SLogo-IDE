package controller.testers;

import java.util.List;
import java.util.Map;

import parser.multipleturtleparsing.StringListCreator;
import parser.tokenizer.ProtectedTokenList;

public class MultipleTurtleTester {
	public static void main (String[] args){
	//String testCode = "fd 50 ask [ 4 ] [ rt 90 fd 10 ] tell [ 1 3 ] fd 100 ask [ 5 ] [ rt 90 ] fd 100"; 
	String testCode = "ask [ 1 ] fd 50";
	StringListCreator SLC = new StringListCreator(testCode.split(" "));
	
	List<List<String>> subs = SLC.getSublists();
	
	System.out.println(subs);
	
/*	SubListProcessor SLP = new SubListProcessor(subs);
	
	List<String> remainingComms = SLP.getRemainingCommands();
	List<String> precedingComms = SLP.getPrecedingCommands();
	System.out.println("preceding are " + precedingComms);
	System.out.println("remaining are " + remainingComms);
	List<Object> comms = SLP.getCommandObjects();*/
	


	/*MapMaker MM = new MapMaker(comms, precedingComms,  remainingComms);
	System.out.println(MM.getLiteralMap());
	
	Map<Double, ProtectedTokenList> p = MM.getPTLMap();
	for (Double key : p.keySet()){
		System.out.println("turtle is " + key);
		System.out.println("literals are " + p.get(key).getLiterals());
		System.out.println("logo is " + p.get(key).getLogo() );
	}*/
}
}
