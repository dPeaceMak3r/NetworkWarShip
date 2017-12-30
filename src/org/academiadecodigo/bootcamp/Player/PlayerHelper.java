package org.academiadecodigo.bootcamp.Player;

import org.academiadecodigo.bootcamp.Grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class PlayerHelper implements Runnable {


    private Socket playerSocket;
    private PlayerGrid playerGrid;
    private String name;
    private Grid updatedGrid;
    private volatile boolean signal;



    public PlayerHelper(Socket clientSocket, String name, PlayerGrid playerGrid){

        this.playerSocket=clientSocket;
        this.name=name;
        this.playerGrid=playerGrid;
        this.updatedGrid=new Grid();
    }

    @Override
    public synchronized void run() {


        //Defines the input
        while(playerSocket.isBound()){

            try {

                System.out.println("Waiting for the input.");
                BufferedReader input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));

                String [] message=null;
                String receive;
                String instruction="";

                while ((receive=input.readLine()) != null){ ///////

                    instruction = instruction + receive;

                }

                System.out.println(instruction);


                if(instruction.contains("/startGame") ){

                    System.out.println("You can now play.");
                    signal=true;

                }

                if(instruction!=null){

                    message = instruction.split(" ");
                    String printMessage="";

                    //System.out.println(instruction);
                    //The player receives 2 messages. One telling where the fires he shot landed.
                    //Another with the moves from the opponent, showing the where they hit
                    //So, the server will send two types of message, each with their code.



                    if(message[0].equals("/"+name)){
                        //This is for the fires shooted by the player



                        for(int i=0;i<message.length;i++){
                            printMessage=printMessage+" "+message[i];
                        }

                        System.out.println(printMessage);

                    }
                    if(message[0].equals("/shots")){

                        System.out.println(message[0]);
                        System.out.println(message[1]);
                        System.out.println(message[2]);
                        System.out.println(message[3]);
                        System.out.println(message[4]);
                        System.out.println(message[5]);
                        System.out.println(message[6]);

                        //Print the grid of the player

                        for (int i=1; i<=6;i+=2){

                            int first=Integer.parseInt(message[i]);
                            int second = Integer.parseInt(message[i+1]);
                            playerGrid.updateGrid(first,second);

                        }

                        playerGrid.showGrid();

                        System.out.println("Show the signal.");

                    }
                        if(!message[0].equals("/"+name) && !message[0].equals("/shots")){

                        //When the message is the fires hit or missed

                        for(int i=0;i<message.length;i++){
                            printMessage=printMessage+" "+message[i];
                        }
                        System.out.println(printMessage);


                    }
                    signal=true;

                }


            } catch (IOException e) {
                System.err.println("Didn't get a connection.");
            }
        }


    }


    public void standby(){

        while(!signal){
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                System.err.println("Not enough time.");
            }

        }

    }

  //SETTER
    public void setSignal(){
        this.signal=!signal;
    }


}
