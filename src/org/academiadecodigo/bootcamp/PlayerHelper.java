package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PlayerHelper implements Runnable {


    private Socket playerSocket;

    public PlayerHelper(Socket clientSocket){
        this.playerSocket=playerSocket;

    }

    @Override
    public void run() {

        //Defines the input
        while(playerSocket.isBound()){
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));

                //CHANGE THIS FOR THE COORDINATES
                String message= input.readLine();

                if(message!=null){
                    System.out.println(message);
                }


            } catch (IOException e) {
                System.err.println("Didn't get a connection.");
            }
        }


    }
}
