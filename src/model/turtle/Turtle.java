package model.turtle;


/**
 * The class representing the turtle object
 * @author Anh
 *
 */
public class Turtle {
	
	private TurtleState myState; 

	public Turtle(double id, double x, double y, double angle, boolean p, boolean v, double pensize, double pencolindex, double shapeindex){
		myState = new TurtleState(id,x,y,angle,p,v,pensize,pencolindex,shapeindex);
	}
	public Turtle(TurtleState state){
		myState = new TurtleState(state);
	}
	public Turtle(){
		myState = new TurtleState();
	}
	public Turtle(double id){
		myState = new TurtleState(id);
	}
	
	/**
	 * 
	 * @return the read-only version of the turtle state 
	 */
	public State getReadOnlyState(){ 
		return (State) myState;
	}
	/**
	 * 
	 * @return the state of the turtle with full access to setters of properties
	 */
	public TurtleState getState(){ 
		return myState;
	}
	/**
	 *  bring the turtle to the home position
	 */
	public void reset(){
		myState.reset();
	}
	
	public double getID(){
		return myState.getID();
	}
	
}
