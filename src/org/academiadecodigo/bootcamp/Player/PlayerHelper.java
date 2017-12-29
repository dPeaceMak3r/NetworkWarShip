package org.academiadecodigo.bootcamp.Player;

import org.academiadecodigo.bootcamp.Grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PlayerHelper implements Runnable {


    private Socket playerSocket;
    private String[][]playerGrid;
    private String name;
    private Grid updatedGrid;
    private boolean signal;

    //Used to override when synchronizing the threads
    public PlayerHelper(){}

    public PlayerHelper(Socket clientSocket, String name, String [][] playerGrid){
        this.playerSocket=playerSocket;
        this.name=name;
        this.playerGrid=playerGrid;
    }

    @Override
    public void run() {

        //Defines the input
        while(playerSocket.isBound()){
            try {

                BufferedReader input;

                String [] message=null;
                String instruction="";

                System.out.println("TEste");

                synchronized (this){
                    input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));

                    //I HAVE TO SEE THIS BETTER
                    //condition to unlock the player

                    instruction=input.readLine();

                    System.out.println(instruction);

                    if(instruction == "/startGame"){
                        System.out.println("You can now play.");
                        notifyAll();
                        signal=true;
                    }else{
                        message= instruction.split("");

                    }
                }


                //The player receives 2 messages. One telling where the fires he shot landed.
                //Another with the moves from the opponent, showing the where they hit
                //So, the server will send two types of message, each with their code.
                if(message[0].equals("/"+name)){
                    //This is for the fires shooted by the player
                    System.out.println(message.toString());

                }else{
                    //Print the grid of the player
                    for (int i=1; i<7;i+=2){
                        updatedGrid.updateGrid(Integer.parseInt(message[i]),Integer.parseInt(message[i+1]));
                    }

                    updatedGrid.showGrid();
                }








                //Notify the player, after the opponent has made a move
                synchronized (this){
                    notify();

                }

                if(message!=null){
                    System.out.println(message);
                }


            } catch (IOException e) {
                System.err.println("Didn't get a connection.");
            }
        }


    }

    public synchronized void standby(){


        while(!signal){
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Thread locked.");
            }
        }


    }




}
