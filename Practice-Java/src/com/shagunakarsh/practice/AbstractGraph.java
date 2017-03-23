package com.shagunakarsh.practice;

import java.util.List;

public abstract class AbstractGraph {

	abstract void setEdge(int source, int destination);
	abstract List<Integer> getEdge(int source);
	abstract void dfs(int source, int numberOfNodes);
	abstract void bfs(int source, int numberOfNodes);
}
