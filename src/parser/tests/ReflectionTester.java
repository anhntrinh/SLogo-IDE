/**
 * Written by Gideon Pfeffer
 * Provides the framework, taken from the class demo, to try making different classes using reflection
 */
package parser.tests;

import java.lang.reflect.Constructor;
import java.util.List;

import model.command.TreeNode;

public class ReflectionTester {
	
	public static void main(String args[]) throws ClassNotFoundException{
        Class<?> clazz = Class.forName("model.Forward");
        makeAdvancedClass(clazz);
	}
	
	public static void makeAdvancedClass(Class<?> clazz){
        try {
            // the more robust way
            Constructor<?> ctor = clazz.getDeclaredConstructor(List.class);
            Object o = ctor.newInstance();
            TreeNode f = (TreeNode) clazz.cast(o);
            System.out.println("Printing: " + f.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
