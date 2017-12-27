package org.academiadecodigo.bootcamp;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Player {

    public static void main(String[] args) {

        String [][] grid=null;
        boolean [][]hitGrid = new boolean[PlayerGrid.ROWS][PlayerGrid.COLUMNS];

        try {
            Socket playerSocket = new Socket("localhost",8080);

            //Now it needs to declare the output
            PrintWriter output = new PrintWriter(playerSocket.getOutputStream(), true);

            //This is to read from the terminal
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            //This handles the name of the client
            System.out.println("===========================================");
            System.out.println("        ## What is your name? ##");
            System.out.println("===========================================");
            String name=buffer.readLine();
            output.println(name);

            //This is a test to clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();

            //It now call the method that handles the put of the ships in the grid
            PlayerGrid playerGrid=new PlayerGrid();
            //playerGrid.shipsGrid();

            //Loads the grid
            grid=playerGrid.getGrid();

            //Now sends the grid to the server
            //WEAK SPOT, MAY NOT WORK WITH A MATRIX
            output.println(grid);

            //Then, it creates a new thread in the client helper to receive the messages
            //This will be created with the Executor Frame Work
            ExecutorService cachedPool = Executors.newCachedThreadPool();
            cachedPool.submit(new PlayerHelper(playerSocket));

            //Creates a PlayerHelper for the use of standby method
            PlayerHelper helper=new PlayerHelper();


            while (playerSocket.isBound()){


                String shots;
                while(true){
                    //This handles the introduction of the shots to be fired
                    System.out.println("===========================================");
                    System.out.println("        First shot");
                    System.out.println("  Rows(0-8); Columns(0-11)");
                    System.out.println("===========================================");
                    String []tryShots=buffer.readLine().split(" ");

                    hitGrid[0][0]=true;

                    //Now it needs to check if the input coordinates were inside the grid, or was already said
                    if(checkCoordinates(tryShots,hitGrid)){
                        hitGrid[Integer.parseInt(tryShots[0])][Integer.parseInt(tryShots[1])]=true;
                        shots=tryShots[0]+" "+tryShots[1];
                        break;
                    }
                }

                while(true){
                    //This handles the introduction of the shots to be fired
                    System.out.println("===========================================");
                    System.out.println("        Second shot");
                    System.out.println("  Rows(0-8); Columns(0-11)");
                    System.out.println("===========================================");
                    String []tryShots=buffer.readLine().split(" ");

                    //Now it needs to check if the input coordinates were inside the grid, or was already said
                    if(checkCoordinates(tryShots,hitGrid)){
                        hitGrid[Integer.parseInt(tryShots[0])][Integer.parseInt(tryShots[1])]=true;
                        shots=shots+" "+tryShots[0]+" "+tryShots[1];
                        break;
                    }
                }

                while(true){
                    //This handles the introduction of the shots to be fired
                    System.out.println("===========================================");
                    System.out.println("        Third shot");
                    System.out.println("  Rows(0-8); Columns(0-11)");
                    System.out.println("===========================================");
                    String []tryShots=buffer.readLine().split(" ");

                    //Now it needs to check if the input coordinates were inside the grid, or was already said
                    if(checkCoordinates(tryShots,hitGrid)){
                        hitGrid[Integer.parseInt(tryShots[0])][Integer.parseInt(tryShots[1])]=true;
                        shots=shots+" "+tryShots[0]+" "+tryShots[1];
                        break;
                    }
                }

                //You have to use the println to terminate the output
                output.println(shots);

                //Calls the standby method, so it gets on pause until the opponent makes he's play
                helper.standby();


            }


            playerSocket.close();
            output.close();

        } catch (IOException e) {
            System.err.println("It wasn't possible to establish a connection.");
        }


    }

    public static boolean checkCoordinates(String [] tryShots, boolean [][] hitGrid){

        //First we test to see if the given coordinates were inside the grid
        if(Integer.parseInt(tryShots[0])>PlayerGrid.ROWS ||Integer.parseInt(tryShots[1])>PlayerGrid.COLUMNS ){
            System.out.println("The given coordinates are out of the grid. Please put new ones");
            return false;
        }


        //Then check if the coordinates were already said another time
        if(hitGrid[Integer.parseInt(tryShots[0])][Integer.parseInt(tryShots[1])]){
            System.out.println("You have already said these coordinates");
            return false;
        }

        return true;
    }



}
