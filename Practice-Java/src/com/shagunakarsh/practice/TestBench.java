package com.shagunakarsh.practice;

import java.util.List;

/**
 * 
 * @author sakarsh@travelguru.com
 *
 */
public class TestBench {
	
	public static void main(String args[]) {
		int numberOfNodes = 5;
		Graph g = new Graph(numberOfNodes);
		g.setEdge(1, 2);
		g.setEdge(2, 3);
		g.setEdge(2, 4);
		g.setEdge(4, 1);
		g.setEdge(4, 5);
		
		for(int i=1;i<=numberOfNodes; ++i) {
			List<Integer> edges = g.getEdge(i);
			System.out.print(i + "->");
			for(Integer edge: edges) {
				System.out.print(edge + "->");
			}
			System.out.println();
		}
		
		System.out.println("DFS:");
		g.dfs(1, numberOfNodes);
		System.out.println("BFS:");
		g.bfs(1, numberOfNodes);
	}

}
