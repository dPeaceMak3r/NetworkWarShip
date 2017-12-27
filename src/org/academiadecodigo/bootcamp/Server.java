package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final static int PORT_NUMBER = 8080;
    private final static int ROWS = 8;
    private final static int COLS = 11;
    private Data[] playerData = new Data[2];

    public void start(){
        int counter = 0;
        String[] inData;

        ExecutorService clientHandlerThread = Executors.newCachedThreadPool();

        try {

            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

            System.out.println("\nWaiting for a connection...\n");


            while (true){

                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                inData = in.readLine().split(" ");
                playerData[counter] = new Data(clientSocket, inData);
                counter++;
                if (counter == 2){

                    counter = 0;

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public class Data {

        private Socket clientSocket;
        private String name;
        private String[][] grid;


        public Data(Socket clientSocket, String[] data){

            int index = 1;
            this.clientSocket = clientSocket;
            this.name = data[0];

            for (int rows = 0; rows < ROWS; rows++) {

                for (int cols = 0; cols < COLS; cols++) {

                    grid[rows][cols] = data[index];
                    index++;

                }

            }


        }


        public String getName() {

            return name;

        }

    }

}
