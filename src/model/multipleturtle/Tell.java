package model.multipleturtle;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import model.command.Command;
import model.turtle.Turtle;
import parser.multipleturtleparsing.QueueConstructor; 

public class Tell implements MultipleTurtleCommand{
	AskTellData data; 
	List<String> commands; 
	
	List<Turtle> activeTurtles; 
	List<Double> activeTurtleIDs;
	
	private final int TURTLE_BRACKET_COUNT = 2; 
		
	private final String LEFT_BRACKET = "[";
	private final String RIGHT_BRACKET = "]";
	
	public Tell (List<String> parsed, List<Double> turtles){
		commands = new ArrayList<String>(parsed);
		activeTurtles = new ArrayList<Turtle>();
		activeTurtleIDs = new ArrayList<Double>(); 
	}

	public AskTellData getData(){
		return data; 
	}
	
	
	@Override
	public void execute() {
		QueueConstructor QC = new QueueConstructor(commands);
		Queue<String> commandQ = QC.getParsedQ();
		
		ArrayList<String> turtles = new ArrayList<String>(); 
		ArrayList<String> commandsToApply = new ArrayList<String>();

		int bracketCount =0; 
		while (bracketCount<TURTLE_BRACKET_COUNT && !(commandQ.isEmpty())){
			
			String st = commandQ.poll();
			
			if ((st.equals(LEFT_BRACKET)) || (st.equals(RIGHT_BRACKET))){
				bracketCount++; 
			}
			
			else{
				turtles.add(st);
			}
		}

		while (!commandQ.isEmpty()){
	
				commandsToApply.add(commandQ.poll());
				
		}
		
		data =  new AskTellData(turtles, commandsToApply); 
		
	}

}
