/**
 * Written by Gideon Pfeffer
 * Used as a factory to create classes
 */

package parser.reflection;

import java.lang.reflect.Constructor;
import java.util.List;

import controller.SLogoException;
import model.command.TreeNode;
import model.turtle.State;

public class ClassGenerator {
	private PackageLocationHandler PLH;
	private TurtleCommandHandler TCH;
	
	public ClassGenerator(){
		PLH = new PackageLocationHandler();
		TCH = new TurtleCommandHandler();
	}
	
	/**
	 * @param className the name of the class to generate
	 * @param list a List of TreeNodes to be put in the constructor of the Class
	 * @param t The State for the constructor
	 * @return the Class generated from the passed in information
	 */
	public TreeNode generate(String className, List<TreeNode> list, State t) {
		Class<?> clazz = getClass(className);
		Constructor<?> ctor = makeConstructor(clazz, List.class, TCH.isTurtleCommand(className));
		return makeClass(clazz, list, t, TCH.isTurtleCommand(className), ctor);
	}
	
	/**
	 * @param className the name of the class to generate
	 * @param num the double to be put in the constructor
	 * @param t the State for the constructor
	 * @return the class generated
	 */
	public TreeNode generate(String className, Double num, State t) {
		Class<?> clazz = getClass(className);
		Constructor<?> ctor = makeConstructor(clazz, double.class, TCH.isTurtleCommand(className));
		return makeClass(clazz, num, t, TCH.isTurtleCommand(className), ctor);
	}
	
	/**
	 * @param clazz the class you are trying to make an instance of
	 * @param obj the object for the constructor of that class
	 * @param t the State for the constructor of the class
	 * @param isTurtleCommand indicates whether the command be generated is a TurtleCommand
	 * @param ctor the constructor of the class trying to be made
	 * @return an instance of the class
	 */
	private TreeNode makeClass(Class<?> clazz, Object obj, State t,
			boolean isTurtleCommand, Constructor<?> ctor){
		try{
			if(isTurtleCommand){
				return (TreeNode) clazz.cast(ctor.newInstance(obj, t));
			}
			else{
				return (TreeNode) clazz.cast(ctor.newInstance(obj));
			}
		} catch (Exception e){
			throw new SLogoException("invalid syntax: " + clazz.getClass());
		}
	}

	/**
	 * @param className the name of a class
	 * @return the class object created using the String name passed in
	 */
	protected Class<?> getClass(String className){
		Class<?> clazz;
		try {
			clazz = Class.forName(PLH.getLoc(className) + className);
			return clazz;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new SLogoException("invalid syntax: " + className);
	}
	
	/**
	 * @param clazz the class you want the constructor of
	 * @param theClass the class of the object needed for the constructor of the clazz instance
	 * @param isT boolean indicating whether or not the command is a TurtleCommand
	 * @return the constructor generated from the above information
	 */
	private Constructor<?> makeConstructor(Class<?> clazz, Class<?> theClass, boolean isT) {
		Constructor<?> ctor;
		try {
			if(isT){
				ctor = clazz.getDeclaredConstructor(theClass, State.class);
			}
			else{
				ctor = clazz.getDeclaredConstructor(theClass);
			}
			return ctor;
		} 
		catch (Exception e) {
			throw new SLogoException("Invalid command");
		}
	}
}
