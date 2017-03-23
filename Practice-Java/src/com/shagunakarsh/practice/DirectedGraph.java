package com.shagunakarsh.practice;

public class DirectedGraph extends Graph {

	public DirectedGraph(int numberOfNodes) {
		super(numberOfNodes);
	}
	
	@Override
	public void setEdge(int source, int destination) {
		adjList.get(source).add(destination);
	}
}
