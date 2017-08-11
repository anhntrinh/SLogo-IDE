package model.turtle;
import java.util.Observable; 

/**
 * this class holds all turtle's properties, providing their getters and setters. 
 * @author Anh
 *
 */
public class TurtleState extends Observable implements State {
	private double myX; 
	private double myY; 
	private double myAngle; 
	private boolean myPen; 
	private boolean myVisibility;
	private double myID;
	private double myPenSize;
	private double myPenColorIndex;
	private double myShapeIndex; 
	
	public TurtleState(double id, double x, double y, double headAngle, boolean pen,boolean v,double pensize,double pencol,double shape){
		myID = id;
		myX = x; 
		myY = y; 
		myAngle = headAngle; 
		myPen = pen;
		myVisibility = v;
		myPenSize = pensize;
		myPenColorIndex = pencol;
		myShapeIndex = shape;
	}
	
	public TurtleState(){
		this(1,0,0,0, true, true,3,0,0);
	}
	
	public TurtleState(double id){
		this(id,0,0,0, true, true,3,0,0);
	}
	
	public TurtleState(TurtleState o){
		myID = o.getID();
		myX = o.getX();
		myY = o.getY();
		myAngle = o.getHeadAngle();
		myPen = o.getPen();
		myVisibility = o.getVisibility();	
		myPenSize = o.getPenSize();
		myPenColorIndex = o.getPenColorIndex();
		myShapeIndex = o.getShapeIndex();
	}
	
	public void setID(double id){
		myID = id;
		setChanged();
		notifyObservers();
	}
		
	public void setX(double num){
		myX = num; 
		setChanged();
		notifyObservers();
	}
	
	public void setY(double num){
		myY = num; 
		setChanged();
		notifyObservers();
	}
	
	public void setAll(double x, double y, double angle){
		myX = x;
		myY = y; 
		myAngle = angle;
		setChanged();
		notifyObservers();
	}
	
	public void setPosition(double x, double y){
		myX = x;
		myY = y;
		setChanged();
		notifyObservers();
		
	}
	
	public void setHeadAngle(double num){
		myAngle = num; 
		setChanged();
		notifyObservers();	
	}
	
	public void setPen(boolean p){
		myPen = p; 
		setChanged();
		notifyObservers();
	}
	
	public void setVisibility(boolean v){
		myVisibility = v; 
		setChanged();
		notifyObservers();
	}
	
	public void setPenSize(double pensize){
		myPenSize = pensize;
		setChanged();
		notifyObservers();
		
	}
	
	public void setPenColorIndex (double colindex){
		myPenColorIndex = colindex;
		setChanged();
		notifyObservers();
	}
	
	public void setShapeIndex (double shapeindex){
		myShapeIndex = shapeindex;
		setChanged();
		notifyObservers();
	}
	
	public double getID(){
		return myID;
	}
	
	public double getX(){
		return myX ;
	}
	
	public double getY(){
		return myY; 
	}
	
	public double getHeadAngle(){
		return myAngle;
	}
	
	public boolean getPen(){
		return myPen; 
	}

	public boolean getVisibility(){
		return myVisibility; 
	}
	
	public double getPenSize(){
		return myPenSize;
	}
	public double getPenColorIndex(){
		return myPenColorIndex;
	}
	public double getShapeIndex(){
		return myShapeIndex; 
	}

	public void reset(){
		myX = 0; 
		myY = 0; 
		myAngle = 0; 
		myPen = true;
		myVisibility = true;
		myPenSize = 3;
		myPenColorIndex = 0;
		myShapeIndex = 0; 
		setChanged(); 
		notifyObservers(); 
	}
}
