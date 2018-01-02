// Name:Minjie Zhao
// USC loginid: minjiezh
// CS 455 PA3
// Spring 2017


import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;


/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 *      java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row, followed by the start location, 
 * and ending with the exit location. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze, with start location as the top left, and exit location as the bottom right
 * (we count locations from 0, similar to Java arrays):
 * 
 * 3 4 
 * 0111
 * 0000
 * 1110
 * 0 0
 * 2 3
 * 
 */

public class MazeViewer {
   
   private static final char WALL_CHAR = '1';
   private static final char FREE_CHAR = '0';
   private static int rowNum = 0;
   private static int colNum = 0;
   private static int startRow = 0;
   private static int startCol = 0;
   private static int endRow = 0;
   private static int endCol = 0;

   public static void main(String[] args)  {

      String fileName = "";

      try {

         if (args.length < 1) {
            System.out.println("ERROR: missing file name command line argument");
         }
         else {
            fileName = args[0];
            
            JFrame frame = readMazeFile(fileName);



            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);


         }

      }
      catch (FileNotFoundException exc) {
         System.out.println("File not found: " + fileName);
      }
      catch (IOException exc) {
         exc.printStackTrace();
      }
   }

   /**
    readMazeFile reads in maze from the file whose name is given and 
    returns a MazeFrame created from it.
   
   @param fileName
             the name of a file to read from (file format shown in class comments, above)
   @returns a MazeFrame containing the data from the file.
        
   @throws FileNotFoundException
              if there's no such file (subclass of IOException)
   @throws IOException
              (hook given in case you want to do more error-checking --
               that would also involve changing main to catch other exceptions)
   */
   private static MazeFrame readMazeFile(String fileName) throws IOException {
      FileInputStream fis = new FileInputStream(fileName);
      Scanner in = new Scanner(fis);

      getRowCol(in);
      //System.out.println(rowNum+" "+colNum);

      boolean[][] fileBoolean = new boolean[rowNum][colNum];
      for(int i = 0; i < rowNum; i++){

         String input = in.nextLine();
         char[] strs = input.toCharArray();
         for(int j = 0;j < strs.length; j++){
            if(FREE_CHAR == (strs[j])){
               fileBoolean[i][j]=false;
            }else if(WALL_CHAR == strs[j]){
               fileBoolean[i][j]=true;
            }
            //System.out.print(""+fileBoolen[i][j]+" ");
         }
         //System.out.println();


      }
      getStartLoc(in);
      getEndLoc(in);

      MazeCoord startPoint = new MazeCoord(startRow,startCol);
      MazeCoord endPoint = new MazeCoord(endRow,endCol);
      //System.out.println(" "+startPoint);
      return new MazeFrame(fileBoolean, startPoint, endPoint);


   }

   private static void getRowCol(Scanner in){
      if(in.hasNextLine()){
         String input = in.nextLine();
         String[] strs = input.trim().split("\\s+");
         rowNum = Integer.parseInt(strs[0]);
         colNum = Integer.parseInt(strs[1]);
      }
   }
   private static void getStartLoc(Scanner in){
      if(in.hasNextLine()){
         String input = in.nextLine();
         String[] strs = input.trim().split("\\s+");
         startRow = Integer.parseInt(strs[0]);
         startCol = Integer.parseInt(strs[1]);
      }
   }
   private static void getEndLoc(Scanner in){
      if(in.hasNextLine()){
         String input = in.nextLine();
         String[] strs = input.trim().split("\\s+");
         endRow = Integer.parseInt(strs[0]);
         endCol = Integer.parseInt(strs[1]);
      }
   }


}
