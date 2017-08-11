package model.command;
import controller.BackEndHandler;
/** 
 * each command class implements this interface to apply its effect on the turtle by calling execute 
 * @author Anh
 *
 */
public interface Command {
	
	/**
	 * method to apply effect of command on the turtle state it is assigned to. It takes in a BackEndHandler
	 * to support any command that has effect on the GUI components. 
	 * @param myHandler the handler that invokes methods within the Controller which has access to public APIs 
	 * of the GUI
	 * 
	 */
	public void execute(BackEndHandler myHandler);
}
