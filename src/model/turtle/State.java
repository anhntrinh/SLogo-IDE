package model.turtle;
/**
 * this interface is implemented by TurtleState and provides a read-only access to the properties of turtle 
 * @author Anh
 *
 */
public interface State {
	public double getID();
	public double getX();
	public double getY();
	public double getHeadAngle();
	public boolean getPen();
	public boolean getVisibility();	
	public double getPenSize();
	public double getPenColorIndex();
	public double getShapeIndex();
}
