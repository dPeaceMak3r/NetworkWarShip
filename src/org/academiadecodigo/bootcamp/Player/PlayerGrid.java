package org.academiadecodigo.bootcamp.Player;

import org.academiadecodigo.bootcamp.Grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerGrid {

    public static final int ROWS=8;
    public static final int COLUMNS =11;
    private final String [][] grid=new String[ROWS][COLUMNS];
    private final String [][] interfaceGrid=new String[ROWS][COLUMNS];
    private Grid drawGrid;

    public void shipsGrid(){

        //This is to read from the terminal
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        this.drawGrid=new Grid();

        inputCoordinates(buffer, "Carrier"," Ca",5);
        //inputCoordinates(buffer, "Battleship"," Ba",4);
        //inputCoordinates(buffer,"Cruiser"," Cr",3);
        //inputCoordinates(buffer,"Submarine"," Su",3);
        //inputCoordinates(buffer, "Destroyer"," De",2);




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
                if(!checkFirstCoordinates(firstPosition,shipType)){
                    //To see if the first position is within boundaries
                    continue;
                }

                //Now comes the final coordinates
                System.out.println("============================================================");
                System.out.println("## What is the final position of the "+shipName+" ("+holes+" holes) ##");
                System.out.println("      ## Separate the position with a white space ##");
                System.out.println("============================================================");

                String [] secondPosition=buffer.readLine().split(" ");
                if(checkSecondCoordinates(firstPosition,secondPosition,shipType,holes)){
                    createGrid(grid);
                    showGrid();
                    break;
                }else{
                    //To reset the grid, if the second coordinates were wrong
                    grid[Integer.parseInt(firstPosition[0])][Integer.parseInt(firstPosition[1])]=null;
                }



            }


        } catch (IOException e) {
            System.err.println("Wasn't able to read from the file.");
        }

    }

    private boolean checkFirstCoordinates(String [] position, String shipType){
        //It checks if the characters typed are numbers
        try{
            Integer.parseInt(position[0]);
            Integer.parseInt(position[1]);
        }catch (NumberFormatException e){
            System.out.println("Not a valid number, introduce a number.");
            return false;
        }



        //To check if the coordinates given belong to the grid
        if(Integer.parseInt(position[0])>=ROWS ||Integer.parseInt(position[1])>=COLUMNS){
            System.out.println("Those coordinates are out of bound. Please choose new ones.");
            return false;
        }else{
            int row=Integer.parseInt(position[0]);
            int col=Integer.parseInt(position[1]);


            //Now it needs to check if the coordinates are already occupied
           if(grid[row][col]!=null){
               //It means there is already a ship there
               System.out.println("These coordinates are already in use, please choose new ones");
               return false;
           }

            grid[row][col]=shipType;

            return true;
        }
    }

    private boolean checkSecondCoordinates(String [] firstPosition, String [] secondPosition, String shipType, int holes){

        //It checks if the characters typed are numbers
        try{
            Integer.parseInt(firstPosition[0]);
            Integer.parseInt(firstPosition[1]);
            Integer.parseInt(secondPosition[0]);
            Integer.parseInt(secondPosition[1]);
        }catch (NumberFormatException e){
            System.out.println("Not a valid number, introduce a number.");
            return false;
        }

        //The first condition is to check if the coordinates are within the grid
        if(Integer.parseInt(secondPosition[0])>=ROWS ||Integer.parseInt(secondPosition[1])>=COLUMNS) {
            System.out.println("Those coordinates are out of bound. Please choose new ones.");
            return false;
        }

        //Now it has to check if the coordinates given are the exact same number of holes between the first coordinates
        int firstRow=Integer.parseInt(firstPosition[0]);
        int firstCol=Integer.parseInt(firstPosition[1]);
        int secondRow=Integer.parseInt(secondPosition[0]);
        int secondCol=Integer.parseInt(secondPosition[1]);
        if((firstRow==secondRow && (Math.abs(firstCol-secondCol)==(holes-1))) || (firstCol==secondCol && (Math.abs(firstRow-secondRow)==(holes-1)))){
            //This means that the ship placed is in the right place
            //Then fills the grid with the position of the ship
            //It will first fill the final position

            //verify first if there is a ship along those coordinates
            for (int i =1; i<holes;i++) {
                if (secondRow == firstRow) {
                    if (secondCol > firstCol) {
                        if (grid[secondRow][firstCol + i] != null) {
                            System.out.println("1 - There is already a ship between the initial and final coordinates");
                            return false;
                        }
                    }else{
                        if (grid[secondRow][firstCol - i] != null) {
                            System.out.println("2 - There is already a ship between the initial and final coordinates");
                            return false;
                        }

                    }
                }else if(secondCol==firstCol){
                    if(secondRow>firstRow) {
                        if (grid[firstRow + i][secondCol] != null) {
                            System.out.println("3 - There is already a ship between the initial and final coordinates");
                            return false;
                        }
                    }else{
                        if (grid[firstRow - i][secondCol] != null) {
                            System.out.println("4 - There is already a ship between the initial and final coordinates");
                            return false;
                        }
                    }
                }
            }


            for (int i=1; i<holes;i++){
                if(secondRow==firstRow){
                    //It needs to check all the coordinates im line to see if they are already taken
                    //The logic is to only use one operation to either fill to the right, or to the left
                    if(secondCol>firstCol){
                        grid[secondRow][firstCol+i] = shipType;
                    }else{
                        grid[secondRow][firstCol-i] = shipType;
                    }

                }else if(secondCol==firstCol){
                    //It needs to check all the coordinates im line to see if they are already taken
                    //The logic is to only use one operation to either fill up or down
                    if(secondRow>firstRow) {
                        grid[firstRow + i][secondCol] = shipType;

                    }else{
                        grid[firstRow - i][secondCol] = shipType;
                    }
                }
            }
            grid[secondRow][secondCol]=shipType;
            return true;
        }else{
            System.out.println("The last coordinates are not right, please introduce new ones.");
            return false;
        }


    }

    public void createGrid( String [][] playerGrid) {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if(playerGrid[i][j]!=null){
                    interfaceGrid[i][j]=playerGrid[i][j];
                }else{
                    interfaceGrid[i][j] = " - ";

                }
            }
        }
    }

    public void showGrid() {

        boolean printR = true;

        System.out.print("   ");


        for (int i = 0; i < COLUMNS; i++) {
            if (i > 10) {
                System.out.print(i + " ");
            } else {
                System.out.print(" " + i + " ");
            }

        }
        System.out.println();

        int r = 0;

        for (int i = 0; i < ROWS; i++) {


            for (int j = 0; j < COLUMNS; j++) {

                if (printR) {
                    System.out.print(" " + r + " ");
                }
                printR = false;
                System.out.print(interfaceGrid[i][j]);
            }
            System.out.println();
            printR = true;
            r++;

        }


    }

    public void updateGrid(int x, int y){

        if(interfaceGrid[x][y].equals(" - ")){
            interfaceGrid[x][y] = " O ";
        }else{
            interfaceGrid[x][y] = " X ";
        }


    }

    //GETTER
    public String [][] getGrid(){
        return this.grid;
    }

}
