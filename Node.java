
public class Node implements Comparable<Node> {
	
	private Town town;
	private int priority;
	
	
	public Node(Town town, int priority) {
		this.town = town;
		this.priority = priority;
	}


	public Town getTown() {
		return town;
	}
	
	public int getPriority() {
		return priority;
	}
	
	
	@Override
	public int compareTo(Node o) {
		
		if(priority < o.getPriority())
			return -1;
		if(priority > o.getPriority())
			return 1;
		return 0;
	}
	
	

}
