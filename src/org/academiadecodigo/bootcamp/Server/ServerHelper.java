package org.academiadecodigo.bootcamp.Server;

import java.io.*;

public class ServerHelper implements Runnable {

    private Data[] playerData;
    private int counter = 0;
    private int miss = 0;
    private String shipsDestroyed="";
    private String shipsHit="";

    public ServerHelper(Data[] playerData){

        this.playerData = playerData;

    }

    public void gameLogic(String[] shots){

        miss = 0;
        shipsDestroyed="";
        shipsHit="";

        for (int i=0;i<shots.length;i+=2){

            int firstShot=Integer.parseInt(shots[i]);
            int secondShot=Integer.parseInt(shots[i+1]);

            if (playerData[counter].getGrid()[firstShot][secondShot] != null){

                //Switch for the type of ships
                switch (playerData[counter].getGrid()[firstShot][secondShot]){
                    case "Ca":
                        playerData[counter].getShipList().get(0).hit();
                        shipsHit=shipsHit+" "+"Carrier hit";
                        if(playerData[counter].getShipList().get(0).isDestroyed()){
                            shipsDestroyed=shipsDestroyed+" "+playerData[counter].getShipList().get(0).getMessage();
                        }
                        break;
                    case "Ba":
                        playerData[counter].getShipList().get(1).hit();
                        shipsHit=shipsHit+" "+"Battleship hit";
                        if(playerData[counter].getShipList().get(1).isDestroyed()){
                            shipsDestroyed=shipsDestroyed+" "+playerData[counter].getShipList().get(1).getMessage();
                        }
                        break;
                    case "Cr":
                        playerData[counter].getShipList().get(2).hit();
                        shipsHit=shipsHit+" "+"Cruiser hit";
                        if(playerData[counter].getShipList().get(2).isDestroyed()){
                            shipsDestroyed=shipsDestroyed+" "+playerData[counter].getShipList().get(2).getMessage();
                        }
                        break;
                    case "Su":
                        playerData[counter].getShipList().get(3).hit();
                        shipsHit=shipsHit+" "+"Submarine hit";
                        if(playerData[counter].getShipList().get(3).isDestroyed()){
                            shipsDestroyed=shipsDestroyed+" "+playerData[counter].getShipList().get(3).getMessage();
                        }
                        break;
                    case "De":
                        playerData[counter].getShipList().get(4).hit();
                        shipsHit=shipsHit+" "+"Destroyer hit";
                        if(playerData[counter].getShipList().get(4).isDestroyed()){
                            shipsDestroyed=shipsDestroyed+" "+playerData[counter].getShipList().get(4).getMessage();
                        }
                        break;

                }

                //playerData[counter].getGrid()[][]

            }else{

                miss++;

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

        String message="";

        try {

            //Message to the player that got attacked
            for (int i = 0; i < playerData.length; i++) {
            PrintWriter out = new PrintWriter(playerData[counter].getClientSocket().getOutputStream(), true);
            if (miss < 3){

                //This happens when one of the ships was destroyed
                if(!shipsDestroyed.equals("")){
                    message = "/Shipsdestroyed "+shipsDestroyed;
                }

                //Message for the ships hit
                message = message +" /Shipshit "+shipsHit;

            }

            message = message + (" /Shotshit " + (3-miss));

            String sendShots=shots[0];
            for (int j=1; j<shots.length;j++){

                sendShots=sendShots+" "+shots[j];

            }

            message = message + " /shots "+sendShots;

            out.print(message);
            out.flush();

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
