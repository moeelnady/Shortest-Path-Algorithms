package Graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Graph g = new Graph();
		System.out.println("Enter Path name:");
		String path = sc.nextLine();
		int [][]adjacencyMatrix=g.initialize(path);
		int[] parent= new int[adjacencyMatrix[0].length];
		while(true) {
		System.out.println("\nChoose one Option");
		System.out.println("1.Shortest path from node to all other nodes\n2.Shortest path between all pair of nodes\n3.Check a negative cycle\n4.Exit");
		int choice = sc.nextInt();
		if(choice==1) {
			System.out.println("Choose an Algorithm");
			System.out.println("1.Dijkstra Algorithm\n2.Bellman-Ford Algorithm\n3.Floyd Warshall Algorithm");
			int choice_1=sc.nextInt();
			if(choice_1==1) {
				while(true){
				System.out.println("\nChoose a source node");
				int src=sc.nextInt();
				
				if(src>=parent.length) {
					System.out.println("Invalid source node");
				}
				
				else {
					g.dijkstra(adjacencyMatrix, src, parent);
					System.out.println("Algorithm is applied successfully");
					System.out.println("\n1.Get a path \n2.Get a cost\n3.Exit\n");
					int choice_2=sc.nextInt();
					if(choice_2==1) {
						System.out.println("Enter a destination node");
							int dest=sc.nextInt();
							if(dest>=adjacencyMatrix[0].length) {
								System.out.println("Invalid destination node");
							}
							else {
								g.printPath(src,dest);
								
							}
					}
					else if (choice_2==2) {
							System.out.println("Enter a destination node");
							int dest=sc.nextInt();
							if(dest>=adjacencyMatrix[0].length) {
								System.out.println("Invalid destination node");
							}
							
							else
							g.printSolution(src, dest);
					  
					}
					else {
						break;
					}
					
				}
			 }
			}
			
			
			else if(choice_1==2) {
				///BELLMAN-FORD ALGORITHM
				while(true) {
				System.out.println("\nChoose a source node");
				int src=sc.nextInt();
				
				if(src>=parent.length) {
					System.out.println("Invalid source node");
				}
				else {
					g.bellman(adjacencyMatrix, src, parent);
					System.out.println("Algorithm is applied successfully");
					System.out.println("\n1.Get a path \n2.Get a cost\n3.Exit\n");
					int choice_2=sc.nextInt();
					if(choice_2==1) {
						System.out.println("Enter a destination node");
							int dest=sc.nextInt();
							if(dest>=adjacencyMatrix[0].length) {
								System.out.println("Invalid destination node");
							}
							else {
								g.printPath(src,dest);
								
							}
					}
					else if (choice_2==2) {
							System.out.println("Enter a destination node");
							int dest=sc.nextInt();
							if(dest>=adjacencyMatrix[0].length) {
								System.out.println("Invalid destination node");
							}
							else
								g.printSolution(src, dest);
					}
					else {
						break;
					}

					
				}
			}
				
			}
			else if(choice_1==3) {
				//FLOYD WARSHALL ALGORITHM
				int[][]cost=new int [adjacencyMatrix[0].length][adjacencyMatrix[0].length];
				int[][]preMatrix=new int [adjacencyMatrix[0].length][adjacencyMatrix[0].length];
				while(true){
				System.out.println("\nChoose a source node");
				int src=sc.nextInt();
				if(src>=parent.length) {
					System.out.println("Invalid source node");
				}
				else {
				g.floyd(cost, preMatrix);
				System.out.println("Algorithm is applied successfully");
				System.out.println("\n1.Get a path \n2.Get a cost\n3.Exit\n");
				int choice_2=sc.nextInt();
				if(choice_2==1) {
					System.out.println("Enter a destination node");
						int dest=sc.nextInt();
						if(dest>=adjacencyMatrix[0].length) {
							System.out.println("Invalid destination node");
						}
						
						else {
							g.print_path(src, dest);
						}
				}
				else if (choice_2==2) {
						System.out.println("Enter a destination node");
						int dest=sc.nextInt();
						if(dest>=adjacencyMatrix[0].length) {
							System.out.println("Invalid destination node");
						}
						else
						g.print_single_cost(src, dest);
				}
				else {
					break;
				}
			  }
			}
			}
			else {
				System.out.println("Invalid Choice");
			}
		
		}
		else if(choice==2) {
			
			System.out.println("Choose an Algorithm");
			System.out.println("1.Dijkstra Algorithm\n2.Bellman-Ford Algorithm\n3.Floyd Warshall Algorithm");
			int choice_1=sc.nextInt();
			if(choice_1==1) {
				while(true) {
					int [][]cost=g.dijkstra_all_pairs(false);
					 System.out.println("Algorithm is applied sucessfully");
						System.out.println("\n1.Get a path \n2.Get a cost\n3.Exit\n");
						int choice_2=sc.nextInt();
						if(choice_2==1) {
							System.out.println("\nChoose a source node");
							int src=sc.nextInt();
							System.out.println("Enter a destination node");
							int dest=sc.nextInt();
							g.print_path_all_pairs(src, dest);
								
								
						}
						else if (choice_2==2) {
							System.out.println("\nChoose a source node");
							int src=sc.nextInt();
							System.out.println("Enter a destination node");
							int dest=sc.nextInt();
							g.print_solution_all_pairs(src, dest); 
						}
						else {
							break;
						}
						
				}
				
			}
			else if(choice_1==2) {
				while(true) {
					int [][]cost=g.bellman_all_pairs(false);
					System.out.println("Algorithm is applied sucessfully");
					System.out.println("\n1.Get a path \n2.Get a cost\n3.Exit\n");
					int choice_2=sc.nextInt();
					
					if(choice_2==1) {
						System.out.println("\nChoose a source node");
						int src=sc.nextInt();
						System.out.println("Enter a destination node");
						int dest=sc.nextInt();
						g.print_path_all_pairs(src, dest);
						
							
					}
					else if (choice_2==2) {
						
						
						System.out.println("\nChoose a source node");
						int src=sc.nextInt();
						System.out.println("Enter a destination node");
						int dest=sc.nextInt();
						g.print_solution_all_pairs(src, dest); 
					  
					}
					else {
						break;
					}
				}
			}
			else if(choice_1==3) {
				int[][]cost=new int [adjacencyMatrix[0].length][adjacencyMatrix[0].length];
				int[][]preMatrix=new int [adjacencyMatrix[0].length][adjacencyMatrix[0].length];
				while(true){
				
				
				g.floyd(cost, preMatrix);
				System.out.println("Algorithm is applied successfully");
				System.out.println("\n1.Get all pathes \n2.Get all costs\n3.Exit\n");
				int choice_2=sc.nextInt();
				if(choice_2==1) {
					
							g.print_all_paths();
							
				}
				else if (choice_2==2) {
						g.print_all_pair_cost();
				}
				else {
					break;
				}
			  
			}
			}
			
			
		}
		else if(choice==3) {
			System.out.println("Choose an Algorithm to check negative cycle");
			System.out.println("1.Bellman Ford Algorithm\n2.Floyd Warshall");
			int choice_1=sc.nextInt();
			if(choice_1==1) {
				boolean flag= g.bellman(adjacencyMatrix, 0, parent);
				if(flag) {
					System.out.println("graph doesn't contain negative cycle");
				}
				else {
					System.out.println("graph contains negative cycle");
				}
			}
			else if(choice_1==2) {
				int[][]cost=new int [adjacencyMatrix[0].length][adjacencyMatrix[0].length];
				int[][]preMatrix=new int [adjacencyMatrix[0].length][adjacencyMatrix[0].length];
				boolean flag= g.floyd(cost, preMatrix);
				if(flag) {
					System.out.println("graph doesn't contain negative cycle");
				}
				else {
					System.out.println("graph contains negative cycle");
				}
			}
		}
		else if (choice==4) {
			break;
		}
		else {
			System.out.println("Invalid Choice");
		}
		}
		
	}

}
