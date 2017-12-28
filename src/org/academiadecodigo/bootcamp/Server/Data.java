package org.academiadecodigo.bootcamp.Server;

import java.net.Socket;

public class Data {

    private final static int ROWS = 8;
    private final static int COLS = 11;

    private Socket clientSocket;
    private String name;
    private String[][] grid = new String[ROWS][COLS];

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

    public Socket getClientSocket() {
        return clientSocket;
    }

    public String[][] getGrid() {
        return grid;
    }

    public String getName() {

        return name;

    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

}
