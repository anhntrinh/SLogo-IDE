package model.command;
/**
 * This class extends the TreeNode abstract class to represent a constant. 
 * @author Anh
 *
 */
public class Constant extends TreeNode{
	double myConstant; 
	
	public Constant(double num){
		myConstant = num; 
	}
	
	public double getValue(){
		return myConstant; 
	}
	
}
