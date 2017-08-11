package parser.multipleturtleparsing;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import parser.tokenizer.ProtectedTokenList;

public class QueueConstructor {
	
	private final String BREAKPOINT_1 = "ask";
	private final String BREAKPOINT_2 = "tell";
	private final String BREAKPOINT_3 = "askwith";

	
	Queue<String> myQ; // these have ask and tell
	Queue<String> myParsedQ; // parsed queues have no ask n tell 
	
	public QueueConstructor(ProtectedTokenList p){
		myQ = new LinkedList<String>(); 
		myParsedQ = new LinkedList<String>();
		constructQ(p);
	}
		
	
	public QueueConstructor(List<String> literals){
		myQ = new LinkedList<String>(); 
		myParsedQ = new LinkedList<String>();
		constructQ(literals);
		constructParsedQ(literals);
	}
	
	private void constructQ(List<String> literals) {
		for(String s: literals){
			myQ.add(s);
		}
		
	}


	public Queue<String> getQ(){
		return myQ; 
	}
	
	public Queue<String> getParsedQ(){
		return myParsedQ;
	}
	
	private void constructParsedQ(List<String> literals) {
		for (String s : literals){
			if ((!s.equals(BREAKPOINT_1)) && !(s.equals(BREAKPOINT_2)) && !(s.equals(BREAKPOINT_3))){
			myParsedQ.add(s);
			}
		}
		
	}

	private void constructQ (ProtectedTokenList p){
		List<String> literalInput = p.getLiterals(); 
		for (String s : literalInput){
			myQ.add(s);
		}

}
}
