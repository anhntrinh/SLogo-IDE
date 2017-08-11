package model.multipleturtle;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import parser.multipleturtleparsing.QueueConstructor;

public class Ask implements MultipleTurtleCommand {
	private AskTellData dataObject; 
	private List<String> parsedData; 
	
	private final int TURTLE_BRACKET_COUNT = 2; 
	private final int COMMANDS_BRACKET_COUNT = 4;
	
	private final String LEFT_BRACKET = "[";
	private final String RIGHT_BRACKET = "]";
	
	
	public Ask(List<String> data, List<Double> turtles){
		parsedData = new ArrayList<String>(data);
	}
	
	
	public AskTellData getData(){
		return dataObject; 
	}
	
	@Override
	public void execute() {
		
		ArrayList<String> turtles = new ArrayList<String>(); 
		ArrayList<String> commands = new ArrayList<String>();
		
		QueueConstructor QC = new QueueConstructor(parsedData);
		Queue<String> commandQ = QC.getParsedQ();
		
		int bracketCount = 0; 
		while (bracketCount< TURTLE_BRACKET_COUNT){
			String st = commandQ.poll(); 
			if ((st.equals("[")) || (st.equals("]"))){
				bracketCount++; 
			}
			else{
				turtles.add(st);
			}
		}
		
		if (bracketCount==TURTLE_BRACKET_COUNT){
			while (bracketCount< COMMANDS_BRACKET_COUNT && !commandQ.isEmpty()){
				String st1 = commandQ.poll(); 
			
				if ((st1.equals(LEFT_BRACKET)) || (st1.equals(RIGHT_BRACKET))){
					bracketCount++; 
				}
				if (bracketCount == COMMANDS_BRACKET_COUNT){
					break;
				}
				else{
					commands.add(st1);
				}
			}
		}
		
		dataObject =  new AskTellData(turtles, commands);
		
	}

}
