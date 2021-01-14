/**
 * Labyrinth class.
 */

import java.io.*;

public class Labyrinth {
	private char PATH = ' ', NON_PATH = 'X',  GOAL = '$';
	private char[][] mazeData;
	private boolean pathFound;
		
	/**
	 * constructor
	 * pre: Maze file contains valid maze data in the format:
	 * first line: length of maze
	 * second line: width of maze
	 * remaining lines: maze data
	 * post: Maze data has been loaded from maze file.
	 */
	public Labyrinth(String l) {
		
		try {
			File mazeFile = new File(l);
			FileReader in = new FileReader(mazeFile);
			BufferedReader readMaze = new BufferedReader(in);
			int length = Integer.parseInt(readMaze.readLine());
			int width = Integer.parseInt(readMaze.readLine());
			mazeData = new char[length][width];
			for (int row = 0; row < length; row++) {
				for (int col = 0; col < width; col++) {
					mazeData[row][col] = (char)readMaze.read();
				}
				readMaze.readLine();	//read past end-of-line characters
			}
    		readMaze.close();
    		in.close();
    	} catch (FileNotFoundException e) {
			System.out.println("File does not exist or could not be found.");
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Problem reading file.");
    		System.err.println("IOException: " + e.getMessage());
    	}
    	
    	pathFound = false;
    }
    
    
    /**
	 * Determines a path through the maze
	 * pre: none
	 * post: The coordinates of the path have been displayed.
	 */
	private void findPath(int row, int col){
                /* check if row and col don't exceed the length of maze AND don't go below 0 */
            if (row >= 0 && col >= 0 && row < mazeData.length+1 && col < mazeData[0].length+1){  
                if (row < mazeData.length && col < mazeData[0].length) {
                    if (mazeData[row][col] == GOAL) {
                            pathFound = true;
                            System.out.println("(" + row + "," + col + ") ");
                    } else if (mazeData[row][col] == PATH){
                        mazeData[row][col] = NON_PATH;
                        if (!pathFound) {
                            findPath(row+1, col);	
                        }
                        if (!pathFound) {
                            findPath(row-1, col);
                        }	
                        if (!pathFound) {
                            findPath(row, col+1);
                        }
                        if (!pathFound) {
                            findPath(row, col-1);
                        }
                        if (pathFound) {
                            System.out.println("(" + row + "," + col + ") ");
                        }
                    }	
                }
            }
	}


    /**
	 * Analyzes a maze for a path and displays the path
	 * pre: The maze has a border
	 * post: A path through the maze has been displayed.
	 */
	public void displayPath() {
		
		System.out.println("\nPath: ");
		findPath(1, 1);					//start at the corner
	}
	
	
	/**
	 * Displays the maze.
	 * pre: none
	 * post: Maze data has been displayed.
	 */
	public void displayMaze() {
		
		for (int row = 0; row < mazeData.length; row++) {
			for (int col = 0; col < mazeData[0].length; col++) {
				System.out.print(mazeData[row][col]);
			}
			System.out.println();
		}
	}
}
