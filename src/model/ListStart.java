package model;

import java.util.List;

import controller.BackEndHandler;
import model.command.Command;
import model.command.TreeNode;
import model.turtle.State;


public class ListStart extends TreeNode implements Command{
	
	public ListStart(List<TreeNode> list, State t){
		
	}

	@Override
	public void execute(BackEndHandler myHandler) {
		// TODO Auto-generated method stub
		
	}
	

}
