import java.util.ArrayList;
import java.util.Collections;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph g;
	
	public TownGraphManager() {
		g = new Graph();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		Road r;
		
		if(g.containsVertex(t1) &&
				g.containsVertex(t2)) {
			r = g.addEdge(t1, t2, weight, roadName);
			if(r != null)
				return true;
		}
		return false;
		
		
	}

	@Override
	public String getRoad(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		
		return g.getEdge(t1, t2).getName();
	}

	@Override
	public boolean addTown(String v) {
		Town t = new Town(v);
		
		return g.addVertex(t);
	}

	@Override
	public Town getTown(String name) {
		Town t = new Town(name);
		boolean suc = g.vertexSet().contains(t);
		
		if(suc != true)
			return t;
		return null;
	}

	
	@Override
	public boolean containsTown(String v) {
		Town t = new Town(v);
		return g.vertexSet().contains(t);
	}

	
	@Override
	public boolean containsRoadConnection(String town1, String town2) {

		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		
		return g.containsEdge(t1, t2);
	}

	
	
	@Override
	public ArrayList<String> allRoads() {
		
		ArrayList<String> roadNames = new ArrayList<String>();
		ArrayList<Road> roadsAL = new ArrayList<Road>();
		
		for(Road s : g.edgeSet())
			roadsAL.add(s);
		
		for(int i=0; i<roadsAL.size(); i++) {
			roadNames.add(roadsAL.get(i).getName());
		}
		
		Collections.sort(roadNames);
		return roadNames;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		
		if(g.removeEdge(t1, t2, 0, road) != null)
			return true;
		return false;
		
	}

	@Override
	public boolean deleteTown(String v) {
		
		Town t = new Town(v);
		
		return g.removeVertex(t);
	}

	@Override
	public ArrayList<String> allTowns() {
		
		ArrayList<String> towns = new ArrayList<String>();
		ArrayList<Town> townsAL = new ArrayList<Town>();
		for(Town t : g.vertexSet())
			townsAL.add(t);
		
		for(int i=0; i<townsAL.size(); i++)
			towns.add(townsAL.get(i).getName());
		
		Collections.sort(towns);
		
		return towns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		
		ArrayList<String> path = new ArrayList<String>();
		
		path = g.shortestPath(t1, t2);
		if(path.isEmpty())
			return null;
		return path;
	}

}
