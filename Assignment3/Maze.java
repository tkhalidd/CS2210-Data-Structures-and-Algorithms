import java.io.*;
import java.util.*;

public class Maze {
    private Graph mazeGraph;
    private GraphNode entrance;
    private GraphNode exit;
    private int coins;
    private int width;
    private int length;
    private int scale;

    // Constructor to initialize the maze from an input file
    public Maze(String inputFile) throws MazeException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            // Read maze parameters from the input file
            this.scale = Integer.parseInt(reader.readLine());
            this.width = Integer.parseInt(reader.readLine());
            this.length = Integer.parseInt(reader.readLine());
            this.coins = Integer.parseInt(reader.readLine());

            // Initialize the character array to store the maze layout
            char[][] charArray = new char[length * 2][width * 2];
            int row = 0;
            String line;

            // Read each line of the maze layout from the file
            while (row < (length * 2 - 1) && (line = reader.readLine()) != null) {
                for (int col = 0; col < (width * 2 - 1); col++) {
                    charArray[row][col] = line.charAt(col);
                }
                row++;
            }

            // Build the maze graph from the character array
            buildMaze(charArray);
        } catch (Exception e) {
            // Handle file reading errors
            throw new MazeException(e.getMessage());
        }
    }

    // Constructs the maze graph from the character array
    private void buildMaze(char[][] charArray) throws Exception {
        mazeGraph = new Graph(length * width);

        // Iterate over the character array to build the maze
        for (int row = 0; row < (length * 2 - 1); row++) {
            for (int col = 0; col < (width * 2 - 1); col++) {
                switch (charArray[row][col]) {
                    case 's':
                        // Set entrance node
                        entrance = mazeGraph.getNode((row / 2) * width + (col / 2));
                        break;
                    case 'x':
                        // Set exit node
                        exit = mazeGraph.getNode((row / 2) * width + (col / 2));
                        break;
                    case 'c':
                        // Add corridor edges
                        addMazeEdge(row, col, 0, "corridor", row % 2 == 0);
                        break;
                    default:
                        if (Character.isDigit(charArray[row][col])) {
                            // Add door edges with cost
                            addMazeEdge(row, col, Character.getNumericValue(charArray[row][col]), "door", row % 2 == 0);
                        }
                        break;
                }
            }
        }
    }

    // Adds an edge between nodes in the maze graph
    private void addMazeEdge(int row, int col, int points, String label, boolean horizontal) throws GraphException {
        GraphNode currentNode = mazeGraph.getNode((row / 2) * width + (col / 2));
        GraphNode nextNode;

        if (horizontal) {
            // Connect horizontally
            nextNode = mazeGraph.getNode((row / 2) * width + ((col + 1) / 2));
        } else {
            // Connect vertically
            nextNode = mazeGraph.getNode(((row + 1) / 2) * width + (col / 2));
        }

        mazeGraph.insertEdge(currentNode, nextNode, points, label);
    }

    // Returns the maze graph
    public Graph getGraph() throws MazeException {
        if (mazeGraph == null) {
            throw new MazeException("graph is null");
        }
        return mazeGraph;
    }

    // Solves the maze by finding a path from entrance to exit
    public Iterator<GraphNode> solve() {
        Stack<GraphNode> currentPath = new Stack<>();
        try {
            if (dfs(entrance, exit, currentPath)) {
                return currentPath.iterator();
            }
        } catch (GraphException e) {
            System.out.print("Incident edges cannot be found");
        }
        return Collections.emptyIterator();
    }

    // Depth-first search to find a path from current node to the exit
    private boolean dfs(GraphNode current, GraphNode exit, Stack<GraphNode> currentPath) throws GraphException {
        if (current.isMarked()) {
            return false;
        }

        // Mark the current node as visited and add to the path
        currentPath.push(current);
        current.mark(true);

        if (current.equals(exit)) {
            return true;
        }

        // Iterate over all edges of the current node
        Iterator<GraphEdge> edges = mazeGraph.incidentEdges(current);
        while (edges != null && edges.hasNext()) {
            GraphEdge edge = edges.next();
            GraphNode neighbour = edge.secondEndpoint().equals(current) ? edge.firstEndpoint() : edge.secondEndpoint();

            if (!neighbour.isMarked() && passability(edge)) {
                int originalCoins = this.coins;
                this.coins -= edge.getType();

                // Recursively call dfs to continue searching
                if (dfs(neighbour, exit, currentPath)) {
                    return true;
                } else {
                    // Backtrack by resetting the coin count
                    this.coins = originalCoins;
                }
            }
        }

        // Remove the current node from the path and mark as unvisited
        currentPath.pop();
        current.mark(false);
        return false;
    }

    // Checks if the edge can be passed with the current number of coins
    private boolean passability(GraphEdge edge) {
        return edge.getType() >= 0 && this.coins >= edge.getType();
    }
}
