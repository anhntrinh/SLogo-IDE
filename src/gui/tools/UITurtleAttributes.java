package gui.tools;

public class UITurtleAttributes {
	private double x;
	private double y;
	private double angle;
	public UITurtleAttributes(double x, double y, double a){
		setAll(x,y,a);
	}
	
	public void setAll(double x, double y, double a){
		this.x = x;
		this.y =y;
		this.angle = a;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getAngle(){
		return angle;
	}
}
