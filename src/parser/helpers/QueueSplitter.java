/**
 * Written by Gideon Pfeffer
 * Another form of a Queue rearranger
 * puts queues into a list with each index being the root of a new queue
 * then, rebuilds a queue using the list
 */

package parser.helpers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.command.TreeNode;

public class QueueSplitter {
	Queue<TreeNode> queue;
	Queue<TreeNode> newQueue;
	List<Queue<TreeNode>> list;
	
	public QueueSplitter(Queue<TreeNode> queue){
		this.queue = new LinkedList<>(queue);
		list = new ArrayList<>();
		newQueue = new LinkedList<>();
		fix();
		remakeQueue();
	}
	
	/**
	 * Makes a list of queues with each list index being a new root
	 */
	private void fix(){
		Queue<TreeNode> q = new LinkedList<>();
		while(!queue.isEmpty()){
			TreeNode t = queue.remove();
			q.add(t);
			if(t.isRoot()){
				addToList(q);
				q = new LinkedList<>();
			}
		}
	}
	
	/**
	 * rebuilds the queue from the list of queues
	 */
	private void remakeQueue(){
		for(Queue<TreeNode> q : list){
			while(!q.isEmpty()){
				newQueue.add(q.remove());
			}
		}
	}
	
	/**
	 * @param q the queue that you want to add to the list
	 */
	private void addToList(Queue<TreeNode> q){
		list.add(0,q);
	}
	
	/**
	 * @return the queue generated after redistributing
	 */
	public Queue<TreeNode> getQueue(){
		return newQueue;
	}

}
