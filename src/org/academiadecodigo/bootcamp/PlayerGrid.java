package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerGrid {

    public static final int ROWS=8;
    public static final int COLUMNS =10;
    private final String [][] grid=new String[ROWS][COLUMNS];

    public void shipsGrid(){

        //This is to read from the terminal
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        inputCoordinates(buffer, "Carrier","Ca",5);
        inputCoordinates(buffer, "Battleship","Ba",4);
        inputCoordinates(buffer,"Cruiser","Cr",3);
        inputCoordinates(buffer,"Submarine","Su",3);
        inputCoordinates(buffer, "Destroyer","De",2);


    }

    //Method to input the coordinates
    private void inputCoordinates(BufferedReader buffer, String shipName, String shipType, int holes){

        try {


            while(true){

                System.out.println("============================================================");
                System.out.println("## What is the initial position of the "+shipName+" ("+holes+" holes) ##");
                System.out.println("         ## Separate the position with a white space ##");
                System.out.println("============================================================");
                String [] firstPosition=buffer.readLine().split(" ");
                if(checkFirstCoordinates(firstPosition,shipType)){
                    //To see if the first position is within boundaries
                    continue;
                }

                //Now comes the final coordinates
                System.out.println("============================================================");
                System.out.println("## What is the final position of the "+shipName+" ("+holes+" holes) ##");
                System.out.println("      ## Separate the position with a white space ##");
                System.out.println("============================================================");

                String [] secondPosition=buffer.readLine().split(" ");
                if(checkSecondCoordinates(firstPosition,secondPosition,"Ca",5)){
                    break;
                }

            }


        } catch (IOException e) {
            System.err.println("Wasn't able to read from the file.");
        }

    }

    private boolean checkFirstCoordinates(String [] position, String shipType){
        //To check if the coordinates given belong to the grid
        if(Integer.parseInt(position[0])>ROWS ||Integer.parseInt(position[1])>COLUMNS){
            System.out.println("Those coordinates are out of bound. Please choose new ones.");
            return false;
        }else{
            int row=Integer.parseInt(position[0]);
            int col=Integer.parseInt(position[1]);
            grid[row][col]=shipType;

            //Now it needs to check if the coordinates are already occupied
           if(grid[row][col]!=null){
               //It means there is already a ship there
               System.out.println("These coordinates are already in use, please choose new ones");
               return false;
           }

            return true;
        }
    }

    private boolean checkSecondCoordinates(String [] firstPosition, String [] secondPosition, String shipType, int holes){

        //The first condition is to check if the coordinates are within the grid
        if(Integer.parseInt(secondPosition[0])>ROWS ||Integer.parseInt(secondPosition[1])>COLUMNS) {
            System.out.println("Those coordinates are out of bound. Please choose new ones.");
            return false;
        }

        //Now it has to check if the coordinates given are the exact same number of holes between the first coordinates
        int firstRow=Integer.parseInt(firstPosition[0]);
        int firstCol=Integer.parseInt(firstPosition[1]);
        int secondRow=Integer.parseInt(secondPosition[0]);
        int secondCol=Integer.parseInt(secondPosition[1]);
        if(firstRow==secondRow && (firstCol-secondCol==holes) || firstCol==secondCol && (firstRow-secondRow==holes)){
            //This means that the ship placed is in the right place
            //Then fills the grid with the position of the ship
            //It will first fill the final position
            grid[secondRow][secondCol]=shipType;
            for (int i=1; i<holes;i++){
                if(secondRow==firstRow){
                    //It needs to check all the coordinates im line to see if they are already taken
                    //The logic is to only use one operation to either fill to the right, or to the left
                    if(grid[secondRow][Math.abs((firstCol-secondCol-i))]!=null){
                        grid[secondRow][Math.abs((firstCol-secondCol-i))]=shipType;
                    }else{
                        System.out.println("There is already in ship between the inital and final coordinates");
                        return false;
                    }

                }else{
                    //It needs to check all the coordinates im line to see if they are already taken
                    //The logic is to only use one operation to either fill up or down
                    if(grid[Math.abs((firstCol-secondCol-i))][secondCol]!=null){
                        grid[Math.abs((firstRow-secondRow-i))][secondCol]=shipType;
                    }else{
                        System.out.println("There is already in ship between the inital and final coordinates");
                        return false;
                    }

                }
            }
            return true;
        }else{
            System.out.println("The last coordinates are not right, please introduce new ones.");
            return false;
        }


    }

    //GETTER
    public String [][] getGrid(){
        return this.grid;
    }

}
