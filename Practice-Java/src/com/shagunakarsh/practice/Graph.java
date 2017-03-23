package com.shagunakarsh.practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph extends AbstractGraph {
	
	Map<Integer, List<Integer>> adjList;

	public Graph(int numberOfNodes) {
		adjList = new HashMap<Integer, List<Integer>>();
		for(int i=1;i<=numberOfNodes; ++i) {
			adjList.put(i, new LinkedList<Integer>());
		}
	}
	
	@Override
	public void setEdge(int source, int destination) {
		adjList.get(source).add(destination);
		adjList.get(destination).add(source);
	}
	
	@Override
	public List<Integer> getEdge(int source) {
		return adjList.get(source);
	}
	
	void dfsUtil(int node, Map<Integer, Boolean> visited) {
		System.out.println("visited: " + node);
		visited.put(node, Boolean.TRUE);
		List<Integer> connectedEdges = adjList.get(node);
		for(Integer edge: connectedEdges) {
			if(!visited.get(edge).equals(Boolean.TRUE)) {
				dfsUtil(edge, visited);
			}
		}
	}
	
	@Override
	void dfs(int startNode, int noOfNodes) {
		Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		
		for(int i=1; i<=noOfNodes; ++i) {
			visited.put(i, Boolean.FALSE);
		}
		
		visited.put(startNode, Boolean.TRUE);
		dfsUtil(startNode, visited);
	}
	
	void bfsUtil(Queue<Integer> queue, Map<Integer, Boolean> visited) {
		
		while(!queue.isEmpty()) {
		
			Integer node = queue.remove();
			visited.put(node, Boolean.TRUE);
			System.out.println("visited: " + node);
			List<Integer> connectedEdges = adjList.get(node);
			for(Integer cNode: connectedEdges) {
				if(!visited.get(cNode).equals(Boolean.TRUE)) {
					queue.add(cNode);
					visited.put(cNode, Boolean.TRUE);
					//System.out.println("adding: " + cNode);
				}
			}
		}
	}
	
	@Override
	void bfs(int startNode, int noOfNodes) {
		Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		
		for(int i=1; i<=noOfNodes; ++i) {
			visited.put(i, Boolean.FALSE);
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startNode);
		bfsUtil(queue, visited);
	}
	
}
