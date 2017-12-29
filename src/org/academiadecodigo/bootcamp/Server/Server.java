package org.academiadecodigo.bootcamp.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final static int PORT_NUMBER = 8080;
    private Data[] playerData = new Data[2];

    public void serverStart(){
        int counter = 0;
        String[] inData;

        ExecutorService clientHandlerThread = Executors.newCachedThreadPool();

        try {

            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

            System.out.println("\nWaiting for a connection...\n");


            while (true){

                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                try {
                    inData = in.readLine().split(" ");
                }catch (NullPointerException e){
                    System.out.println("Connection lost.");
                    clientSocket.close();
                    in.close();
                    continue;
                }

                playerData[counter] = new Data(clientSocket, inData);
                counter++;
                if (counter == 2){

                    clientHandlerThread.submit(new ServerHelper(playerData));
                    System.out.println("Server in.");
                    counter = 0;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}