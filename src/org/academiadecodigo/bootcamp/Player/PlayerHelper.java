package org.academiadecodigo.bootcamp.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PlayerHelper implements Runnable {


    private Socket playerSocket;
    private String[][]enemyGrid;

    //Used to override when synchronizing the threads
    public PlayerHelper(){}

    public PlayerHelper(Socket clientSocket){
        this.playerSocket=playerSocket;
    }

    @Override
    public void run() {

        //Defines the input
        while(playerSocket.isBound()){
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));

                //I HAVE TO SEE THIS BETTER
                String [] message= input.readLine().split("/");

                //The player recieves 2 messages. One telling where the fires he shot landed.
                //Another with the moves from the opponent, showing the where they hitted
                //So, the server will send two types of message, wich with their code.
                if(message[0].equals("00")){
                    System.out.println("Your hits: "+message[1]);
                }else if(message[0].equals("01")){
                    System.out.println(message);
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
        try {
            wait();
        } catch (InterruptedException e) {
            System.err.println("Thread locked.");
        }
    }

}
