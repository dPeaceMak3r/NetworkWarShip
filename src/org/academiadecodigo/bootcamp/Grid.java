package org.academiadecodigo.bootcamp;


import java.util.Scanner;

public class Grid {


    public static int ROWS = 8;
    public static int COLS = 11;


    String[][] grid = new String[ROWS][COLS];


    public void createGrid() {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = " - ";
            }
        }
    }


    public void showGrid() {

        boolean printR = true;


        for (int i = 0; i <= COLS; i++) {
            if (i > 10) {
                System.out.print(i + " ");
            } else {
                System.out.print(" " + i + " ");
            }

        }
        System.out.println();

        int r = 1;

        for (int i = 0; i < ROWS; i++) {


            for (int j = 0; j < COLS; j++) {

                if (printR) {
                    System.out.print(" " + r + " ");
                }
                printR = false;
                System.out.print(grid[i][j]);
            }
            System.out.println();
            printR = true;
            r++;

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

        int c = 0;
        while (!(validX && validY)) {

            if (c == 0) {

                System.out.print("x: ");
                xIn = scanner.next();
            } else {
                System.out.print("y: ");
                yIn = scanner.next();
            }


            try {

                if (c == 0) {

                    x = Integer.parseInt(xIn);

                    if (x < 0 || x > COLS) {
                        System.out.println("Enter a number between 0 and " + COLS);
                        continue;
                    }
                } else {

                    y = Integer.parseInt(yIn);

                    if (y < 0 || y > ROWS) {
                        System.out.println("Enter a number between 0 and " + ROWS);
                        continue;
                    }
                }


            } catch (NumberFormatException e) {
                System.out.println("Invalid In-Put (Enter a number)");
                continue;
            }

            if (c == 0) {
                validX = true;
                c++;
                System.out.println("OK");
            } else {
                validY = true;
                c--;
                System.out.println("OK");
            }


        }

    }
}

/*

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

        String direction="";


        valid = false;

       while (!valid){

           System.out.println("Enter direction (up, down, left, right)");
           direction = scanner.next();


           switch (direction){

               case "up":
                   System.out.println("up ok");
                   valid = true;
                   break;

               case "down":
                   System.out.println("down ok");
                   valid = true;
                   break;

               case "left":
                   System.out.println("left ok");
                   valid = true;
                   break;

               case "right":
                   System.out.println("right ok");
                   valid = true;
                   break;

               default:
                   System.out.println("Not valid!");
                   valid = true;
                   continue;

           }


       }




        System.out.println("Direction: ");
        System.out.println(direction);
    }

}
*/