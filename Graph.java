import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;



public class Graph implements GraphInterface<Town, Road> {

	
	HashMap<Town, Integer> towns;
	ArrayList<ArrayList<Road>> adjList;
	
	private ArrayList<Road> shortestPath = new ArrayList<Road>();
	private int weight[];
	private Set<Town> visited = new HashSet<Town>();
	private PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
	
	
	public Graph() {
		towns = new HashMap<Town, Integer>();
		adjList = new ArrayList<>();
	}
	
	
	
	public Road getEdge(Town source, Town destination) {
		
		for(int i=0; i<adjList.get(towns.get(source)).size(); i++) {
			
			if(adjList.get(towns.get(source)).get(i).getDestination().equals(destination))
				return adjList.get(towns.get(source)).get(i);
			
		}
		
		return null;
		
	}
	
	
	
	public Road addEdge(Town source, Town destination, int weight, String name) {
		if(source == null || destination == null)
			throw new NullPointerException();
	
		if(!(towns.containsKey(source)) ||
				!(towns.containsKey(destination)))
			throw new IllegalArgumentException();
		
		Road r = new Road(source, destination, weight, name);
		
		if(adjList.size() < towns.size()) {
			
			for(int i=adjList.size(); i<towns.size(); i++)
				adjList.add(new ArrayList<Road>());
			
			adjList.get(towns.get(source)).add(r);
			adjList.get(towns.get(destination)).add(r);
			
			return r;
		}
		
		if(adjList.get(towns.get(source)).contains(r)
				|| adjList.get(towns.get(destination)).contains(r))
			return null;
		
		adjList.get(towns.get(source)).add(r);
		return r;
	}
	
	
	
	public boolean addVertex(Town newTown) {
		
		if(newTown == null)
			throw new NullPointerException();
		
		
		if(towns.containsKey(newTown))
			return false;
		
		
		towns.put(newTown, towns.size());
		
		return true;
		
	}
	
	
	
	public boolean containsEdge(Town source, Town destination) {
	
		for(int i=0; i<adjList.size(); i++) {
			for(int j=0; j<adjList.get(i).size(); j++) {
				if(adjList.get(i).get(j).contains(source)
						&& adjList.get(i).get(j).contains(destination))
					return true;
			}
		}
		return false;
	}
	
	
	public boolean containsVertex(Town town) {
		
		if(towns.containsKey(town))
			return true;
		
		return false;
	}
	
	
	public Set<Road> edgeSet(){
		
		Set<Road> r = new HashSet<Road>();
		
		for(int i=0; i<adjList.size(); i++) {
			for(int j=0; j<adjList.get(i).size(); j++) {
				r.add(adjList.get(i).get(j));
			}
		}
		return r;
	}
	
	
	
	
	public Road removeEdge(Town source, Town destination, int weight, String name) {
		
		Road r = new Road(source, destination, weight, name);
		
		for(int i=0; i<adjList.size(); i++) {
			for(int j=0; j<adjList.get(i).size(); j++) {
				
				if(adjList.get(i).get(j).equals(r)) {
					adjList.get(i).remove(j);
					return r;
				}
			}
		}
		return null;
	}
	
	
	
	public boolean removeVertex(Town town) {
		
		if(towns.containsKey(town)) {
			towns.remove(town);
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	public ArrayList<String> shortestPath(Town source, Town destination){
		
		dijkstraShortestPath(source);
		boolean[] isVisited = new boolean[towns.size()];
		allPaths(source, destination, isVisited, shortestPath);
		System.out.println(shortestPath.toString());
		ArrayList<String> strAL = new ArrayList<String>();
		
		for(int i=0; i<shortestPath.size(); i++) {
			String str = shortestPath.get(i).getSource().getName() +
					" via " + shortestPath.get(i).getName() +
					" to " + shortestPath.get(i).getDestination().getName()
					+ " " + shortestPath.get(i).getWeight() + " mi";
			
			strAL.add(str);
		}
		
		return strAL;
		
	}
	
	
	public void dijkstraShortestPath(Town source) {
		weight = new int[towns.size()];
		
		for(int i=0; i<weight.length; i++)
			weight[i] = Integer.MAX_VALUE;
		
		pQueue.add(new Node(source, 0));
		
		weight[towns.get(source)] = 0;
		
		while(visited.size() != towns.size()) {
			
			
			if(pQueue.isEmpty())
				return;
			
			
			Town t = pQueue.remove().getTown();
			
			if(visited.contains(t))
				continue;
			
			visited.add(t);
			neighbors(t);
		}
	}
	
	private void neighbors(Town t) {
		
		int roadWeight = -1;
		int newWeight = -1;
		
		
		for(int i=0; i<adjList.get(towns.get(t)).size(); i++) {
			Road r = adjList.get(towns.get(t)).get(i);
			
			
			if(!(visited.contains(r.getDestination()))
					|| !(visited.contains(r.getSource()))) {
				roadWeight = r.getWeight();
				newWeight = weight[towns.get(t)] + roadWeight;
				
				if(newWeight < weight[towns.get(r.getSource())]) {
					weight[towns.get(r.getSource())] = newWeight;
					pQueue.add(new Node(r.getSource(), weight[towns.get(r.getSource())]));
				}
				else if(newWeight < weight[towns.get(r.getDestination())]) {
					weight[towns.get(r.getDestination())] = newWeight;
					pQueue.add(new Node(r.getDestination(), weight[towns.get(r.getDestination())]));
				}
				
				
				
				                           
			}
		}
	
	}
	
	
	public void allPaths(Town source, Town destination, boolean[] isVisited, ArrayList<Road> paths){
		
		if(source.equals(destination)) {
			int pathWeight = 0;
			for(int i=0; i<paths.size(); i++) {
				pathWeight += paths.get(i).getWeight();
			}
			
			dijkstraShortestPath(source);
			if(pathWeight == weight[towns.get(destination)]) {
				
				shortestPath = paths;
			}
				
		}
		
		isVisited[towns.get(source)] = true;
		
		
		for(int i=0; i<adjList.get(towns.get(source)).size(); i++) {
			
			if(!isVisited[i]) {
				
				paths.add(getEdge(source, adjList.get(towns.get(source)).get(i).getDestination()));
				
				allPaths(adjList.get(towns.get(source)).get(i).getDestination(),
						destination, isVisited, paths);
			}
		}
		
		isVisited[towns.get(source)] = false;
	}




	@Override
	public Set<Town> vertexSet() {
		
		return towns.keySet();
	}



	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> r = new HashSet<Road>();
		
		if(!(towns.containsKey(vertex)))
			throw new IllegalArgumentException();
		
		if(vertex == null)
			throw new NullPointerException();
		
		for(int i=0; i<adjList.size(); i++) {
			for(int j=0; j<adjList.get(i).size(); j++) {
				
				if(adjList.get(i).get(j).contains(vertex))
					r.add(adjList.get(i).get(j));
			}
		}
		
		return r;
	}
	
}
