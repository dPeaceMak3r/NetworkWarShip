package org.academiadecodigo.bootcamp;

import com.sun.tools.corba.se.idl.toJavaPortable.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Player {

    public static void main(String[] args) {

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

            //It now call the method that handles the put of the ships in the grid
            PlayerGrid playerGrid=new PlayerGrid();
            playerGrid.shipsGrid();

            //Then, it creates a new thread in the client helper to receive the messages
            //This will be created with the Executor Frame Work
            ExecutorService cachedPool = Executors.newCachedThreadPool();
            cachedPool.submit(new PlayerHelper(playerSocket));

            while (playerSocket.isBound()){

                //SEE THIS AFTER
                String text=buffer.readLine();

                //You have to use the println to terminate the output
                output.println(text);

            }



        } catch (IOException e) {
            System.err.println("It wasn't possible to establish a connection.");
        }


    }


}
