// Name:Minjie Zhao
// USC loginid:minjiezh
// CS 455 PA3
// Spring 2017

import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls

 */

public class Maze {
   
   private static final boolean FREE = false;
   private static final boolean WALL = true;
   private static boolean[][] mazeData;
   private static MazeCoord startLoc;
   private static MazeCoord exitLoc;
   private static LinkedList<MazeCoord> result ;
   private static LinkedList<MazeCoord> visited;

   private static int rowChange = 0;
   private static int colChange = 1;
   private boolean isWay;


   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments above for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param exitLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
      this.mazeData = mazeData;
      this.startLoc = startLoc;
      this.exitLoc = exitLoc;
   }


   /**
      Returns the number of rows in the maze
      @return number of rows
   */
   public int numRows() {
      return mazeData.length;   // DUMMY CODE TO GET IT TO COMPILE
   }

   
   /**
      Returns the number of columns in the maze
      @return number of columns
   */   
   public int numCols() {
      return mazeData[0].length;   // DUMMY CODE TO GET IT TO COMPILE
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
      if(mazeData[loc.getRow()][loc.getCol()] == WALL){
         return true;
      }else{
         return false;
      }
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return startLoc;   // DUMMY CODE TO GET IT TO COMPILE
   }
   
   
   /**
     Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return exitLoc;   // DUMMY CODE TO GET IT TO COMPILE
   }

   
   /**
      Returns the path through the maze. First element is start location, and
      last element is exit location.  If there was not path, or if this is called
      before a call to search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {
      if(!hasWallAt(startLoc)) {
         if(isWay){
            return result;//if a path is found, return this path
         }else{
            return new LinkedList<MazeCoord>();//return empty list
         }
      }else {
         return new LinkedList<MazeCoord>();   //return empty list
      }
   }


   /**
      Find a path from start location to the exit location (see Maze
      constructor parameters, startLoc and exitLoc) if there is one.
      Client can access the path found via getPath method.

      @return whether a path was found.
    */
   public boolean search()  {
      result = new LinkedList<>();
      visited = new LinkedList<>();//initialize two lists
      isWay = checkAround(startLoc.getRow(),startLoc.getCol(),rowChange,colChange);
      result.add(startLoc);
      return isWay;

   }

   private boolean checkAround(int row,int col,int dRow,int dCol) {
      int currRow = row;
      int currCol = col;

      for(int i = 0; i < visited.size(); i++){
         if(visited.get(i).equals(new MazeCoord(currRow,currCol))||mazeData[currRow][currCol] == WALL){
            return false;
         }
      }
      visited.add(new MazeCoord(currRow,currCol));

      if(!exitLoc.equals(new MazeCoord(currRow,currCol))){
         if((0 <= currRow+dCol && currRow+dCol < numRows())
                 && (0 <= currCol-dRow && currCol-dRow < numCols())
                 && checkAround(currRow+dCol,currCol-dRow,dCol,-dRow)){
            result.add(new MazeCoord(currRow+dCol,currCol-dRow));
            return true;
         }else if((0 <= currRow+dRow && currRow+dRow < numRows())
                 &&(0 <= currCol+dCol && currCol+dCol < numCols())
                 && checkAround(currRow+dRow,currCol+dCol,dRow,dCol)){
            result.add(new MazeCoord(currRow+dRow,currCol+dCol));
            return true;
         }else if((0 <= currRow-dCol && currRow-dCol < numRows())
                 && (0 <= currCol+dRow && currCol+dRow < numCols())
                 && checkAround(currRow-dCol,currCol+dRow,-dCol,dRow)){
            result.add(new MazeCoord(currRow-dCol,currCol+dRow));
            return true;
         }else if((0 <= currRow-dRow && currRow-dRow < numRows())
                 && (0 <= currCol-dCol && currCol-dCol < numCols())
                 && checkAround(currRow-dRow,currCol-dCol,-dRow,-dCol)){
            result.add(new MazeCoord(currRow-dRow,currCol-dCol));
            return true;
         }else{
            return false;
         }
      }else{
         return true;
      }
   }
      //getCurrentDirection(direction);
//      for(int i = 0; i < temp.size(); i++){
//         if(temp.get(i).equals(new MazeCoord(currRow,currCol))||mazeData[currRow][currCol] == WALL){
//            return false;
//         }
//      }

//      if(!exitLoc.equals(new MazeCoord(currRow,currCol)))
//         if(    ((currCol+rowChange < numCols()) && (checkAround(currRow-colChange,currCol+rowChange))))
//                 ||((currRow-1 >= 0) && (checkAround(currRow-1,currCol)))
//              ||((currCol-1 >= 0) && (checkAround(currRow,currCol-1)))
//              ||((currRow+1 < numRows()) && (checkAround(currRow+1,currCol)))){
//            result.add(new MazeCoord(currRow,currCol));
//            return true;
//         }else{
//            return false;
//      }else {
//         result.add(new MazeCoord(currRow,currCol));
//         return true;
//      }




//   public void getCurrentDirection(int direction){
//      switch(direction){
//         case 1://RIGHT
//            rowChange = 0;
//            colChange = 1;
//         case 2://UP
//            rowChange = -1;
//            colChange = 0;
//         case 3://LEFT
//            rowChange = 0;
//            colChange = -1;
//         case 4://DOWN
//            rowChange = 1;
//            colChange = 0;
//      }
//   }
//
//   public  int turnRight(int directionNow){
//      if (directionNow == RIGHT)
//         direction = DOWN;
//      else if (directionNow == UP)
//         direction = RIGHT;
//      else if(directionNow == LEFT)
//         direction = UP;
//      else
//         direction = LEFT;
//
//   }

//      boolean hasWay = false;
//      for(int i = 1;i <= 4 && !hasWay; i++){
//         // 1 = RIGHT, 2 = UP, 3 = LEFT, 4 = DOWN
//         if(i != noGoDirection){
//            switch (i){
//               case 1://keep searching right first, so that it will not stuck in a cycle
//                  if((currCol+1)<numCols() && mazeData[currRow][currCol+1]==FREE) {
//                     currCol++;
//                     result.add(new MazeCoord(currRow, currCol));
//                     hasWay = checkAround(result, LEFT);
//                  }
//               case 2:
//                  if((currRow-1)>0 && mazeData[currRow-1][currCol]==FREE) {
//                     currRow--;
//                     result.add(new MazeCoord(currRow,currCol));
//                     hasWay = checkAround(result, DOWN);
//                  }
//               case 3:
//                  if((currCol-1)>0 && mazeData[currRow][currCol-1]==FREE) {
//                     currCol--;
//                     result.add(new MazeCoord(currRow,currCol));
//                     hasWay = checkAround(result, RIGHT);
//                  }
//               case 4:
//                  if((currRow+1)<numRows() && mazeData[currRow+1][currCol]==FREE) {
//                     currRow++;
//                     result.add(new MazeCoord(currRow,currCol));
//                     hasWay = checkAround(result, UP);
//                  }
//            }
//         }
//      }
//      if(hasWay == false){
//         result.removeLast();//there is no way to continue
//      }
//
//      return true;



}
