/*
 * Maze.java
 * Chapter 12 Exercise 7
 * Lawrenceville Press
 * June 24, 2005
 */
 
  
 public class Maze { 	
    public static void main(String[] args) {

            Labyrinth puzzle = new Labyrinth("maze.txt");
            puzzle.displayMaze();
            puzzle.displayPath();
    }
}
