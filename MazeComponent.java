// Name:Minjie Zhao
// USC loginid:minjiezh
// CS 455 PA3
// Spring 2017

import java.awt.*;
import java.util.LinkedList;
import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{

   private static final int START_X = 10; // top left of corner of maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   

   private Maze maze;
   private MazeCoord startLoc;
   private MazeCoord exitLoc;
   private LinkedList<MazeCoord> result;
   //private boolean[][] mazeData;
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {
      this.maze = maze;
      //this.mazeData = maze.mazeData;
      startLoc = maze.getEntryLoc();
      exitLoc = maze.getExitLoc();

   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
      //show the maze, entryLoc and exitLoc
      result = maze.getPath();
      for(int row = 0; row < maze.numRows(); row++){
         for(int col = 0; col < maze.numCols(); col++){
            Color color;
            MazeCoord currentCoord = new MazeCoord(row,col);//locate current position
            if(maze.hasWallAt(currentCoord)){
               color = Color.black;
               g.setColor(color);
               g.fillRect(START_X+BOX_WIDTH*col,START_Y+BOX_HEIGHT*row,BOX_WIDTH,BOX_HEIGHT);
            }else if(startLoc.equals(new MazeCoord(row,col))){
               drawRect(g,Color.YELLOW,row,col);
            }else if(exitLoc.equals(new MazeCoord(row,col))){
               drawRect(g,Color.GREEN,row,col);
            }else{
               drawRect(g,Color.white,row,col);
            }
         }
      }
      drawOutLine(g,maze.numRows(),maze.numCols());
      //show the route
      for(int i = 0; i < result.size()-1; i++){
         g.setColor(Color.BLUE);
         g.drawLine(result.get(i).getCol()*BOX_WIDTH+BOX_WIDTH/2+START_X,
                    result.get(i).getRow()*BOX_HEIGHT+BOX_HEIGHT/2+START_Y,
                    result.get(i+1).getCol()*BOX_WIDTH+BOX_WIDTH/2+START_X,
                    result.get(i+1).getRow()*BOX_HEIGHT+BOX_HEIGHT/2+START_Y);
      }
   }

   private void drawRect(Graphics g,Color color,int row, int col){
      g.setColor(Color.white);
      g.fillRect(START_X+BOX_WIDTH*col,START_Y+BOX_HEIGHT*row,BOX_WIDTH,BOX_HEIGHT);
      g.setColor(color);
      g.fillRect(START_X+BOX_WIDTH*col+INSET/2,START_Y+BOX_HEIGHT*row+INSET/2,BOX_WIDTH-INSET,BOX_HEIGHT-INSET);
   }

   private void drawOutLine(Graphics g, int numRows, int numCols){
      g.setColor(Color.black);
      g.drawLine(START_X,START_Y,START_X+BOX_WIDTH*numCols,START_Y);
      g.drawLine(START_X,START_Y+BOX_HEIGHT*numRows,START_X+BOX_WIDTH*numCols,START_Y+BOX_HEIGHT*numRows);
      g.drawLine(START_X,START_Y,START_X,START_Y+BOX_HEIGHT*numRows);
      g.drawLine(START_X+BOX_WIDTH*numCols,START_Y,START_X+BOX_WIDTH*numCols,START_Y+BOX_HEIGHT*numRows);
   }
}



