/**
 * Written by Gideon Pfeffer
 * Used to test the commans generatoed by the Compiler
 * Can be used to print the clases generated as well
 * Change testCode to try different tests
 */

package parser.tests;

import java.util.Queue;

import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.main.Compiler;
import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;

public class CompilerTest {
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode = "to sq [ :x ] [ product :x :x ] "
				+ "to addAll [ :x :y :z ] [ sum sum :x :y :z ]";
		String testCode2 = "fd sq 10 rt addAll 10 20 30";
		
		NewParser p = new NewParser(language);
		Compiler c = new Compiler();
		
		ProtectedTokenList PTL = p.parse(testCode);
		
		Queue<TreeNode> q = c.compile(new TurtleState(), PTL);
		
		while(!q.isEmpty()){
			System.out.println("+++");
			System.out.println(q.remove().getValue());
			System.out.println("+++");
		}
		
		PTL = p.parse(testCode2);
		
		q = c.compile(new TurtleState(), PTL);
		
		for(String s: c.getFunctionList()){
			System.out.println(s);
		}
	}

}
