package org.academiadecodigo.bootcamp.Server;

import java.io.*;

public class ServerHelper implements Runnable {

    private Data[] playerData;
    private int counter = 0;
    private int miss = 0;
    private int shipsDestroyed=0;
    private int shipsHit=0;
    private boolean gameOver;


    public ServerHelper(Data[] playerData){

        this.playerData = playerData;

    }

    public void gameLogic(String[] shots){

        miss = 0;
        shipsDestroyed=0;
        shipsHit=0;

        for (int i=0;i<shots.length;i+=2){

            int firstShot=Integer.parseInt(shots[i]);
            int secondShot=Integer.parseInt(shots[i+1]);

            System.out.println(playerData[counter].getGrid()[firstShot][secondShot]);

            if (!playerData[counter].getGrid()[firstShot][secondShot].equals("null")){


                //Switch for the type of ships
                switch (playerData[counter].getGrid()[firstShot][secondShot]){
                    case "Ca":
                        playerData[counter].getShipList().get(0).hit();
                        shipsHit++;
                        if(playerData[counter].getShipList().get(0).isDestroyed()){
                            shipsDestroyed++;
                        }
                        break;
                    case "Ba":
                        playerData[counter].getShipList().get(1).hit();
                        shipsHit++;
                        if(playerData[counter].getShipList().get(1).isDestroyed()){
                            shipsDestroyed++;
                        }
                        break;
                    case "Cr":
                        playerData[counter].getShipList().get(2).hit();
                        shipsHit++;
                        if(playerData[counter].getShipList().get(2).isDestroyed()){
                            shipsDestroyed++;
                        }
                        break;
                    case "Su":
                        playerData[counter].getShipList().get(3).hit();
                        shipsHit++;
                        if(playerData[counter].getShipList().get(3).isDestroyed()){
                            shipsDestroyed++;
                        }
                        break;
                    case "De":
                        playerData[counter].getShipList().get(4).hit();
                        shipsHit++;
                        if(playerData[counter].getShipList().get(4).isDestroyed()){
                            shipsDestroyed++;
                        }
                        break;

                }

            }else{

                miss++;

            }

        }

        //Check if all ships are destroyed
        for (int i=0; i<5;i++){
            if(!playerData[counter].getShipList().get(i).isDestroyed()){
                break;
            }

            if(i==4){
                gameOver=true;
            }
        }



    }

    @Override
    public synchronized void run() {

        int firstRun=0;

        while (playerData[0].getClientSocket().isConnected() && playerData[1].getClientSocket().isConnected()){

            System.out.println("First entry");

            try {

                System.out.println("first run: "+firstRun);

                if(firstRun<2){
                    System.out.println("Counter vslue: "+counter);
                    PrintWriter unlockMessage = new PrintWriter(playerData[counter].getClientSocket().getOutputStream(), true);
                    System.out.println("message unlock" + counter);
                    unlockMessage.println("/startGame");
                    firstRun++;

                }
                    String[] shots;
                    BufferedReader in = new BufferedReader(new InputStreamReader(playerData[counter].getClientSocket().getInputStream()));
                    shots = in.readLine().split(" ");

                    if (shots==null){

                        playerData[counter].getClientSocket().close();

                    }

                    changeCounter();
                    gameLogic(shots);

                    sendMessage(shots);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public synchronized void sendMessage(String [] shots){

        String nameCode;
        String message="";


        try {

            //Message to the player that got attacked
            if (miss < 3){

                //This happens when one of the ships was destroyed
                if(shipsDestroyed!=0){
                    message = " /Shipsdestroyed "+shipsDestroyed;
                }

                //Message for the ships hit
                message = message +" /Shipshit "+shipsHit;

            }

            message = message + (" /Shotsmiss " + miss);

            String sendShots=shots[0];
            for (int j=1; j<shots.length;j++){

                sendShots=sendShots+" "+shots[j];

            }

            message = message + " /shots "+sendShots;



            //}
            changeCounter();
            nameCode="/"+playerData[counter].getName();
            changeCounter();


            if(gameOver==true){

                String endMessage = "You lost the game";

                PrintWriter out = new PrintWriter(playerData[counter].getClientSocket().getOutputStream(), true);
                out.println("/GameOver "+endMessage);
                changeCounter();
                endMessage = "You won the game";
                out = new PrintWriter(playerData[counter].getClientSocket().getOutputStream(), true);
                out.println("/GameOver "+endMessage);

                playerData[counter].getClientSocket().close();
                changeCounter();
                playerData[counter].getClientSocket().close();
                out.close();
            }else{
                for (int i=0; i<2;i++){

                    PrintWriter out = new PrintWriter(playerData[counter].getClientSocket().getOutputStream(), true);
                    out.println(nameCode+message+" /startGame");
                    changeCounter();
                }
            }






        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void changeCounter(){

        if (counter == 0){

            counter = 1;

        }else {

            counter = 0;
        }

    }

}
