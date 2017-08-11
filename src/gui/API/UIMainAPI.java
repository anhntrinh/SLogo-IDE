package gui.API;

import gui.UITurtle;
import model.turtle.TurtleState;

public interface UIMainAPI {
	
	public abstract void displayErrorWithMessage(String e);
	public abstract void clearScreen();
	public abstract void addNewOutput(String output);
	public abstract UITurtle addTurtle(TurtleState state);
	public abstract void setPaletteAtIndex(double index, double red, double blue, double green);
	public abstract void setPenColor(double index);
	public abstract void setBackgroundColor(double index);
	public abstract void setPenStrokeWidth(double width);
	
}
