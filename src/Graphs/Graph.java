package Graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
	int [] parent;
	int []distances;
	int [][] adjacency_matrix;
	int size;
	int V;
	int E;
	int [][]cost_matrix;
	int [][] pre_matrix;
	boolean flag=false;
	public int[][] initialize(String path) throws IOException {
		List<String> listOfStrings= new ArrayList<String>();
		BufferedReader bf = new BufferedReader(new FileReader(path));
		String line = bf.readLine();
		while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }
		bf.close();
		String first_line=listOfStrings.get(0);
		String strArray[] = first_line.split(" ");
		 V=Integer.parseInt(strArray[0]);
		 E=Integer.parseInt(strArray[1]);
		int[][] adjacencyMatrix=new int[V][V];
		for(int i=1;i<E+1;i++) {
			String str=listOfStrings.get(i);
			String strArr[] = str.split(" ");
			//System.out.println(str);
		adjacencyMatrix[Integer.parseInt(strArr[0])][Integer.parseInt(strArr[1])]=Integer.parseInt(strArr[2]);
		}
//		for (int i = 0; i < adjacencyMatrix.length; i++) {
//		 
//       // Loop through all elements of current row
//       for (int j = 0; j < adjacencyMatrix[i].length; j++) {
//           System.out.print(adjacencyMatrix[i][j] + " ");
//       }
//       System.out.println("\n");
//	}
		adjacency_matrix=adjacencyMatrix;
		return adjacencyMatrix;
	}
	public int[] dijkstra(int[][]graph,int src,int [] parents) {
		//size of graph
		size =graph[0].length;
		int[] dist = new int[size];
		boolean [] visited= new boolean[size];
		
		//intializing all distances with infinity
		for(int i=0;i<size;i++) {
			dist[i]=Integer.MAX_VALUE;
			visited[i]=false;
		}
		
		dist[src]=0;
		parents=new int[size];
		parents[src]=-1;
	for (int i=1;i<size;i++) {
		int min=Integer.MAX_VALUE;
		int min_index=-1;
		for(int j=0;j<size;j++) {
			if(!visited[j] && dist[j]< min) {
				min=dist[j];
				min_index=j;
				
			}
		}
		if(min_index<0) {
			System.out.println("no paths");
			parent=parents;
			distances=dist;
			flag=true;
			return null;
		}
		visited[min_index]=true;
		for(int k=0;k<size;k++) {
			int edge_dist=graph[min_index][k];
			if(edge_dist>0 && (edge_dist+min)<dist[k]) {
				parents[k]=min_index;
				dist[k]=min+edge_dist;
			}
		}	
		
	 }
//	for(int i=0;i<size;i++)
//		System.out.print(dist[i]+" " );
//	System.out.println();
//	for(int i=0;i<size;i++)
//		System.out.print(parents[i]+" " );
	parent=parents;
	distances=dist;
	return dist;
	}
	public ArrayList<Integer> printPath(int source,int destination) {
		if (distances[destination] == Integer.MAX_VALUE) {
            System.out.println("No path from " + source + " to " + destination);
        } else {
            ArrayList<Integer> path = new ArrayList<Integer>();
            int vertex = destination;
            while (vertex != source) {
                path.add(vertex);
                vertex = parent[vertex];
            }
            path.add(source);
            Collections.reverse(path);
            System.out.print("Path from " + source + " to " + destination + ": ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i != path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            
            return path;
        }
		return null;
	}
	public void printPath(int source) {
		for (int i = 0; i < V; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("No path from " + source + " to " + i);
            } else {
                List<Integer> path = new ArrayList<>();
                int vertex = i;
                while (vertex != source) {
                    path.add(vertex);
                    vertex = parent[vertex];
                }
                path.add(source);
                Collections.reverse(path);
                System.out.print("Path from " + source + " to " + i + ": ");
                for (int j = 0; j < path.size(); j++) {
                    System.out.print(path.get(j));
                    if (j != path.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }
    }

	public int printSolution(int startVertex,int endVertex) {
		System.out.print("\n" + startVertex + " -> ");
        System.out.print(endVertex + " \t\t ");
        System.out.print(distances[endVertex] + "\t\t\n");
        return distances[endVertex];
	}
	public void print_all_solutions(int src) {
		for(int i=0;i<size;i++) {
			if (i != src)
            {
                System.out.print("\n" + src + " -> ");
                System.out.print(i + " \t\t ");
                System.out.print(distances[i] + "\t\t\n");
            }
		}
	}
	public boolean bellman(int[][] graph, int src , int[] parents) {
		size =graph[0].length;
		int[] dist = new int[size];
		
		//intializing all distances with infinity
		for(int i=0;i<size;i++) {
			dist[i]=Integer.MAX_VALUE;
		}
		dist[src]=0;
		parents=new int[size];
		parents[src]=-1;
		for (int i = 0; i < size- 1; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (graph[j][k] != 0) {
                        if (dist[j] != Integer.MAX_VALUE && dist[j] + graph[j][k] < dist[k]) {
                            dist[k] = dist[j] + graph[j][k];
                            //System.out.println(dist[k]);
                            parents[k]=j;
                        }
                    }
                }
            }
        }
		distances=dist;
		parent=parents;
		
		for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                if (graph[j][k] != 0) {
                    if (dist[j] != Integer.MAX_VALUE && dist[j] + graph[j][k] < dist[k]) {
                        return false;
                    }
                }
            }
        }	
		
		return true;
	}
	public int[][] getCost_matrix() {
		return cost_matrix;
	}
	public boolean floyd(int [][]cost, int [][] preMatrix) {
		size=adjacency_matrix[0].length;
		
		for (int i = 0; i < size; i++)
		      for (int j = 0; j < size; j++) {
		    	  if(i!=j && adjacency_matrix[i][j]==0) {
		    		  cost[i][j]=999;
		    	  }
		    	  else
		        cost[i][j] = adjacency_matrix[i][j];
		        
		      }

		
		preMatrix=new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				if (i != j)
					preMatrix[i][j] = i;
		}
		
		
		for (int k=0;k<size;k++)
			  for (int i=0;i<size;i++)
			    for (int j=0;j<size;j++)
			      if (cost[i][k] + cost[k][j] < cost[i][j]) {
			        cost[i][j] = cost[i][k]+cost[k][j];
			        preMatrix[i][j] = preMatrix[k][j];
			      }
		pre_matrix=preMatrix;
		cost_matrix=cost;
		
        ////check the negative cycle		
		for (int i = 0; i < V; i++)
            if (cost[i][i] < 0)
                return false;
		return true;
	}
	ArrayList<Integer> path= new ArrayList<>(); 
	public ArrayList<Integer> print_path (int i, int j) {
		  if (i!=j)
		    print_path(i,pre_matrix[i][j]);
		  path.add(j);
		  System.out.print(j+" ");
		  return path;
		}
	public int print_single_cost(int src, int dest) {
		System.out.print("\n" + src+ " -> ");
        System.out.print(dest + " \t\t ");
        System.out.print(cost_matrix[src][dest] + "\t\t");
        return cost_matrix[src][dest];
	}
	public void print_all_pair_cost() {
		for (int i = 0; i < V; ++i) 
          for (int j = 0; j < V; ++j) {
        	  System.out.print("\n" + i+ " -> ");
              System.out.print(j + " \t\t ");
              System.out.print(cost_matrix[i][j] + "\t\t");
	   }
	}
	public void print_all_paths() {
		for (int i = 0; i < V; ++i) 
	          for (int j = 0; j < V; ++j) {
	        	  print_path(i,j);
	        	  System.out.println("\n");
		   }
	}
	
	
	
	//////////////////////ALL PAIRS FOR DIJKSTRA AND BELLMAN FORD 
	public int[][] dijkstra_all_pairs(boolean choice){
		flag=false;
		int [][] all_pairs= new int[adjacency_matrix[0].length][adjacency_matrix[0].length];
		int [][]all_parents=new int[adjacency_matrix[0].length][adjacency_matrix[0].length];
	   for(int i=0;i<adjacency_matrix[0].length;i++) {
		   dijkstra(adjacency_matrix, i, parent);
		   if(choice) {
			   printPath(i);
		   }
		   if(flag) {
				continue;
			}
		   for(int j=0;j<adjacency_matrix[0].length;j++) {
			   all_pairs[i][j]=distances[j];
			   all_parents[i][j]=parent[j];
		   }
	   }
	   cost_matrix=all_pairs;
	   pre_matrix=all_parents;
     return all_pairs;
	}
	public void print_all_pair_cost(int [][]cost) {
		flag=false;
		for (int i = 0; i < adjacency_matrix[0].length; ++i) 
          for (int j = 0; j < adjacency_matrix[0].length; ++j) {
        	  System.out.print("\n" + i+ " -> ");
              System.out.print(j + " \t\t ");
              System.out.print(cost[i][j] + "\t\t");
	   }
	}
	
	public int [][] bellman_all_pairs(boolean choice){
		flag=false;
		int [][] all_pairs= new int[adjacency_matrix[0].length][adjacency_matrix[0].length];
		int [][]all_parents=new int[adjacency_matrix[0].length][adjacency_matrix[0].length];
		for(int i=0;i<adjacency_matrix[0].length;i++) {
			bellman(adjacency_matrix, i, parent);
			if(choice) {
				   printPath(i);
			   }
			for(int j=0;j<adjacency_matrix[0].length;j++) {
				   all_pairs[i][j]=distances[j];
				   all_parents[i][j]=parent[j];
			   }
		}
		 cost_matrix=all_pairs;
		 pre_matrix=all_parents;
		return all_pairs;
	}
	public void print_solution_all_pairs(int startVertex,int endVertex) {
		int []dist=cost_matrix[startVertex];
		System.out.print("\n" + startVertex + " -> ");
        System.out.print(endVertex + " \t\t ");
        System.out.print(dist[endVertex] + "\t\t\n");
	}
	public ArrayList<Integer> print_path_all_pairs(int source,int destination) {
		int []dist=cost_matrix[source];
		int[]parents=pre_matrix[source];
		if (dist[destination] == Integer.MAX_VALUE || dist[destination]==0) {
            System.out.println("No path from " + source + " to " + destination);
        } else {
            ArrayList<Integer> path = new ArrayList<Integer>();
            int vertex = destination;
            while (vertex != source) {
                path.add(vertex);
                vertex = parents[vertex];
            }
            path.add(source);
            Collections.reverse(path);
            System.out.print("Path from " + source + " to " + destination + ": ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i != path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            
            return path;
        }
		
		return null;
	}
}
