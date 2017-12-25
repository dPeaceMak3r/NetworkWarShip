package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PlayerHelper implements Runnable {


    private Socket playerSocket;
    private String[][]enemyGrid;

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
                String message= input.readLine();
                //Checks if the message received is the game over message
                if(message.equals("GameOver")){
                    playerSocket.close();
                }else{
                    String []shotsTaken=message.split(" ");
                    for(int i=0; i<shotsTaken.length;i+=2){

                    }

                }









                if(message!=null){
                    System.out.println(message);
                }


            } catch (IOException e) {
                System.err.println("Didn't get a connection.");
            }
        }


    }

}
