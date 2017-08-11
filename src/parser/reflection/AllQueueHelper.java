/**
 * Written by Gideon Pfeffer
 * Helps to reorder the queue based on whether or not the command is nested in other commands
 */

package parser.reflection;

import java.util.LinkedList;
import java.util.Queue;

import model.command.TreeNode;

public class AllQueueHelper {
	Queue<TreeNode> fixedQueue;

	public AllQueueHelper(Queue<TreeNode> q){
		fixedQueue = new LinkedList<>();
		fix(new LinkedList<>(q));
	}
	
	/**
	 * @param q the queue you want to reorder
	 * reorders the queue and saves it in fixedQueue
	 */
	private void fix(Queue<TreeNode> q){
		while(!q.isEmpty()){
			TreeNode cur = q.remove();
			if(cur.isRoot()){
				fixedQueue.add(cur);
			}
		}
	}
	
	/**
	 * @return the reordered queue with nested commands appearing before their parents
	 */
	public Queue<TreeNode> getQueue(){
		return fixedQueue;
	}
}
