import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by zhaominjie on 3/5/17.
 */
public class MazeTester {



    private static final char WALL_CHAR = '1';
    private static final char FREE_CHAR = '0';
    private static int rowNum = 0;
    private static int colNum = 0;
    private static int startRow = 0;
    private static int startCol = 0;
    private static int endRow = 0;
    private static int endCol = 0;

    public static void main(String[] args)throws IOException {
        String fileName = "testfiles/bigMaze";
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
        //return new MazeFrame(fileBoolean, startPoint, endPoint);
        Maze maze = new Maze(fileBoolean,startPoint,endPoint);
        maze.getPath();


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
