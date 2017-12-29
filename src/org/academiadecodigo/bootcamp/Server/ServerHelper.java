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
    public void run() {



        while (playerData[0].getClientSocket().isConnected() && playerData[1].getClientSocket().isConnected()){

            System.out.println("First entry");

            try {
                PrintWriter unlockMessage = new PrintWriter(playerData[counter].getClientSocket().getOutputStream(), true);
                System.out.println("message unlock");
                unlockMessage.print("/startGame");
                String[] shots;
                BufferedReader in = new BufferedReader(new InputStreamReader(playerData[counter].getClientSocket().getInputStream()));
                shots = in.readLine().split(" ");

                if (shots.equals(null)){

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

    public void sendMessage(String [] shots){


        try {

            //Message to the player that got attacked
            PrintWriter out = new PrintWriter(playerData[counter].getClientSocket().getOutputStream(), true);
            if (miss < 3){

                //This happens when one of the ships was destroyed
                if(!shipsDestroyed.equals("")){
                    out.println("Ships destroyed: "+shipsDestroyed);
                }

                //Message for the ships hit
                out.println("Ships hit: "+shipsHit);

            }
            out.println("Number of ships hit: " + (3-miss));

            //Send the shots so the opponent won't see them
            out.println("/shots "+shots);


            //Message to the player that attacked
            changeCounter();
            out = new PrintWriter(playerData[counter].getClientSocket().getOutputStream(), true);
            if (miss < 3){

                //This happens when one of the ships was destroyed
                if(!shipsDestroyed.equals("")){
                    out.println("Ships destroyed: "+shipsDestroyed);
                }

                //Message for the ships hit
                out.println("Ships hit: "+shipsHit);

            }

            out.println("Number of ships hit: " + (3-miss));

            //Last change counter
            changeCounter();

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
