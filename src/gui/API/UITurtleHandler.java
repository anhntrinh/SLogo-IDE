package gui.API;

import model.turtle.TurtleState;

public interface UITurtleHandler {
	public abstract void turtleStateDidChange(TurtleState s);
}
