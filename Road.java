
public class Road extends Object implements Comparable<Road> {

	private Town source, destination;
	private int weight;
	private String name;
	
	
	public Road(Town source, Town destination, int weight, String name) {
		
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.name = name;
	}
	
	
	public Road(Town source, Town destination, String name) {
		
		this.source = source;
		this.destination = destination;
		this.name = name;
		
		weight = 1;
	}
	
	
	public void setSource(Town source) {
		this.source = source;
	}
	
	public void setDestination(Town destination) {
		this.destination = destination;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public boolean contains(Town town) {
		
		if(source.equals(town) ||
				destination.equals(town))
			return true;
		
		else
			return false;
	}
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
	public String getName() {
		
		return name;
	}
	
	
	public Town getDestination() {
		
		return destination;
	}
	
	
	public Town getSource() {
		
		return source;
	}
	
	
	public int compareTo(Road o) {
		
		if(name.equals(o.getName()))
			return 0;
		else
			return 1;
	}
	
	
	public int getWeight() {
		
		return weight;
	}
	
	
	@Override
	public boolean equals(Object r) {
		
		if(r == this)
			return true;
		
		if(!(r instanceof Road))
			return false;
		
		Road road = (Road)r;
		
		return source.compareTo(road.getSource()) == 0
				&& destination.compareTo(road.getDestination()) == 0;
		
	}
}
