package Graphs;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class GraphTesting {
      Graph g = new Graph();
	@Test
	void testDijkstra() throws IOException {
		int[][]graph=g.initialize("graph.txt");
		int[]parents=new int[graph[0].length];
		int []dist=g.dijkstra(graph, 0, parents);
		int[]actual= new int[]{0,4,3,6,8,14};
		assertArrayEquals(actual, dist);
	}
	@Test
	void testDijkstra_single_cost() throws IOException {
		int[][]graph=g.initialize("graph.txt");
		int[]parents=new int[graph[0].length];
		int []dist=g.dijkstra(graph, 0, parents);
		assertEquals(14, dist[5]);
	}
	@Test
	void testDijkstra_single_path() throws IOException {
		int[][]graph=g.initialize("graph.txt");
		int[]parents=new int[graph[0].length];
		g.dijkstra(graph, 0, parents);
		List<Integer> path=g.printPath(0, 5);
		List <Integer> actual= new ArrayList<Integer>();
		actual.add(0);
		actual.add(1);
		actual.add(3);
		actual.add(4);
		actual.add(5);
		assertTrue(path.equals(actual) == true);
	}
	

	@Test
	void testPrint_all_solutions_dijkstra() throws IOException {
		g.initialize("graph.txt");
		int[][]cost=g.dijkstra_all_pairs(false);
		int[][]actual=new int[][] { {0,4,3,6,8,14},
									{8,0,5,2,4,10},
									{13,13,0,7,9,15},
									{6,6,9,0,2,8},
									{4,4,7,6,0,6},
									{0,0,0,0,0,0}};
		assertArrayEquals(cost, actual);
	}

	@Test
	void testPrint_all_possible_paths_dijkstra() throws IOException {
		
	}

	@Test
	void testBellman() throws IOException {
		int[][]graph=g.initialize("graph5.txt");
		int[]parents=new int[graph[0].length];
		Assert.assertTrue(g.bellman(graph, 0,parents)==false);
	}
	@Test
	void testBellman_2() throws IOException {
		int[][]graph=g.initialize("graph2.txt");
		int[]parents=new int[graph[0].length];
		Assert.assertTrue(g.bellman(graph, 0,parents)==true);
	}
	@Test
	void testBellman_single_path() throws IOException{
		int[][]graph =g.initialize("graph2.txt");
		int[]parents=new int[graph[0].length];
		g.bellman(graph, 0, parents);
		List<Integer> path=g.printPath(0, 3);
		List <Integer> actual= new ArrayList<Integer>();
		actual.add(0);
		actual.add(2);
		actual.add(1);
		actual.add(3);
		assertTrue(path.equals(actual) == true);
	}
	@Test
	void testbellman_single_cost() throws IOException {
		int[][]graph =g.initialize("graph3.txt");
		int[]parents=new int[graph[0].length];
		g.bellman(graph, 0, parents);
		int dist=g.printSolution(5,0);
		assertTrue(dist==0);
	}

	@Test
	void testBellman_all_pairs() throws IOException {
		g.initialize("graph3.txt");
		int[][]cost=g.bellman_all_pairs(false);
		int [][] actual= {{0,0,1,2,3,4},
				          {2147483647,0,1,2147483647,3,4},
						{2147483647,2147483647,0,2147483647,2,3},
						{2147483647,-2,-1,0,1,2},
						{2147483647,2147483647,2147483647,2147483647,0,1},
						{2147483647,2147483647,2147483647,2147483647,2147483647,0}};
		assertArrayEquals(cost, actual);
	}

	@Test
	void testFloyd() throws IOException {
		int[][]graph=g.initialize("graph7.txt");
		int[][]parents=new int[graph[0].length][graph[0].length];
		Assert.assertTrue(g.floyd(graph,parents)==false);
	}
	@Test
	void testFloyd_2() throws IOException {
		int[][]graph=g.initialize("graph4.txt");
		int[][]parents=new int[graph[0].length][graph[0].length];
		Assert.assertTrue(g.floyd(graph,parents)==true);
	}
	@Test
	void testFloyd_all_pairs() throws IOException {
		int[][]graph=g.initialize("graph4.txt");
		int[][]parents=new int[graph[0].length][graph[0].length];
		int[][]cost=new int[graph[0].length][graph[0].length];
		int [][]actual= new int [][] {{0,3,7,5},{2,0,6,4},{3,1,0,5},{5,3,2,0}};
		g.floyd(cost, parents);
		assertArrayEquals(g.getCost_matrix(), actual);
	}
	@Test
	void testFloyd_single_path() throws IOException {
		int[][]graph=g.initialize("graph4.txt");
		int[][]parents=new int[graph[0].length][graph[0].length];
		int[][]cost=new int[graph[0].length][graph[0].length];
		g.floyd(cost, parents);
		ArrayList<Integer>path=g.print_path(0, 2);
		System.out.println(path.toString());
		List <Integer> actual= new ArrayList<Integer>();
		actual.add(0);
		actual.add(3);
		actual.add(2);
		assertTrue(path.equals(actual) == true);
	}
	@Test
	void testFloyd_single_cost() throws IOException {
		int[][]graph=g.initialize("graph3.txt");
		int[][]parents=new int[graph[0].length][graph[0].length];
		int[][]cost=new int[graph[0].length][graph[0].length];
		g.floyd(cost, parents);
		int val=g.print_single_cost(2, 0);
		assertTrue(val>900);
	}
	
	

}
