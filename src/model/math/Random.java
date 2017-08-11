package model.math;

import java.util.List;

import controller.BackEndHandler;
import model.command.Command;
import model.command.LogicCommand;
import model.command.TreeNode;
/**
 * 
 * @author Anh
 *
 */

public class Random extends LogicCommand implements Command {



	
	public Random(List<TreeNode> args){
		children = args;
		
		
	}
	
	private double getRandomValue() {
		double max = children.get(0).getValue();
		double val = -1;
		java.util.Random rand = new java.util.Random();
		while (val <0 || val == max ){
			val = 0 + (max - 0) * rand.nextDouble(); //http://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
		}
		return val;
	}

	public void execute(BackEndHandler myHandler){
		
	}
	
	public double getValue(){
		return getRandomValue();
	}
	
}
