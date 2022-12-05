import java.util.List;

public class Town extends Object implements Comparable<Town> {

	private String name;
	private List<Town> adjTowns;
	
	
	
	public Town(String name) {
		
		this.name = name;
	}
	
	
	public Town(Town templateTown) {
		
		name = templateTown.getName();
		adjTowns = templateTown.adjTowns;
	}
	
	
	
	public String getName() {
		
		return name;
	}
	
	public int compareTo(Town o) {
		
		if(name.equals(o.getName()))
			return 0;
		else
			return 1;
	}
	
	@Override
	public String toString() {
		
		return name;
	}
	
	@Override
	public int hashCode() {
		
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == this)
			return true;
		if(!(obj instanceof Town))
			return false;
		
		
		Town town = (Town)obj;
		
		return name.hashCode()== town.hashCode();
	}
	
	
}
