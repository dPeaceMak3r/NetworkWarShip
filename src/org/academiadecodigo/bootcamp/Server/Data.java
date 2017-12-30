package org.academiadecodigo.bootcamp.Server;

import org.academiadecodigo.bootcamp.Server.Ships.Ship;
import org.academiadecodigo.bootcamp.Server.Ships.ShipFactory;

import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Data {

    private final static int ROWS = 8;
    private final static int COLS = 11;

    private Socket clientSocket;
    private String name;
    private String[][] grid = new String[ROWS][COLS];
    private CopyOnWriteArrayList <Ship> shipList;

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

        //Create the ships
        ShipFactory factory= new ShipFactory();
        shipList=factory.createShips();


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

    public CopyOnWriteArrayList<Ship> getShipList(){
        return this.shipList;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

}
