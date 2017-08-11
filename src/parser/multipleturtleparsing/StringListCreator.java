package parser.multipleturtleparsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class StringListCreator {
	
	private List<String> toParse; 
	private List<List<String>> sublists; 
	
	private final String BREAKPOINT_1 = "ask";
	private final String BREAKPOINT_2 = "tell";
	private final String BREAKPOINT_3 = "askwith";
	
	
	
	public StringListCreator(List<String> literals){
		toParse = new ArrayList<String>(literals);
		sublists = new ArrayList<List<String>>();
		createSublists(); 
	}
	
	public StringListCreator(String[] split) {
		toParse = new ArrayList<String>();
		for (String s: split){
			toParse.add(s);
		}

		sublists = new ArrayList<List<String>>();
		createSublists();
	}

	private void createSublists() {
		QueueConstructor QC = new QueueConstructor(toParse);
		Queue<String> commandQ = QC.getQ();
		
		List<String> currentSublist = new ArrayList<String>();
		
		while(!(commandQ.isEmpty())){
			
			String literal = commandQ.poll();
			
			// tell 
			if ((literal.equals(BREAKPOINT_2)) ){
				
				sublists.add(currentSublist);
				currentSublist = new ArrayList<String>(); 
				currentSublist.add(literal);

			}
			
			// ask and ask with 
			else if ((literal.equals(BREAKPOINT_1)) || (literal.equals(BREAKPOINT_3))){

				
				sublists.add(currentSublist);
				currentSublist = new ArrayList<String>(); 
				
				currentSublist.add(literal);
				
				int bracketCount = 0; 
				while(bracketCount < 4 && !commandQ.isEmpty()){
					
					String newString = commandQ.poll();
					if((newString.equals("[")) || (newString.equals("]"))){
						bracketCount++;
					}
					currentSublist.add(newString);		
				}
				
				sublists.add(currentSublist);
				currentSublist = new ArrayList<String>(); 
			}
			
			else{
				currentSublist.add(literal);
			}	
		}
		
		if (!currentSublist.isEmpty()){
			sublists.add(currentSublist);
		}
		
	}

	public List<List<String>> getSublists(){
		return sublists; 
	}
	
}
