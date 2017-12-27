package org.academiadecodigo.bootcamp;


import java.util.Scanner;

public class Grid {



    public static int ROWS = 11;
    public static int COLS = 8;



    String [][] grid = new String [ROWS][COLS];



    public void createGrid(){

        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                grid[j][i] = " - ";
            }
        }
    }


    public void showGrid(){


        for (int i = 0; i < COLS; i++) {

            for (int j = 0; j < ROWS; j++) {
                System.out.print(grid[j][i]);
            }
            System.out.println();
        }


    }

    public static void main(String[] args) {

        Grid g = new Grid();
        g.createGrid();
        g.showGrid();


        g.placeShips();
    }




    public void placeShips() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Place your ships!");

        System.out.println("Carrier (5 holes):");

        boolean valid = false;
        boolean validX = false;
        boolean validY = false;
        String xIn = "";
        String yIn = "";
        int y = 0;
        int x = 0;


            while(!validX){

                System.out.print("x: ");
                xIn = scanner.next();

                try{

                    x = Integer.parseInt(xIn);

                    if(x < 0 || x > COLS){
                        System.out.println("Enter a number between 0 and "+ COLS);
                        continue;
                    }

                }catch (NumberFormatException e){
                    System.out.println("Invalid In-Put (Enter a number)");
                    continue;
                }
                validX = true;

            }





        while(!validY){

            System.out.print("y: ");
            yIn = scanner.next();

            try{
                y = Integer.parseInt(yIn);

                if(y < 0 || y > ROWS){
                    System.out.println("Enter a number between 0 and "+ ROWS);
                    continue;
                }

            }catch (NumberFormatException e){
                System.out.println("Invalid In-Put (Enter a number)");
                continue;
            }
            validY = true;

        }

        System.out.println("x: " + x);
        System.out.println("y: " + y);


    }

}
