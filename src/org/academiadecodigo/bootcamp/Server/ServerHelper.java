package org.academiadecodigo.bootcamp.Server;

public class ServerHelper implements Runnable {

    private Data[] playerData;

    public ServerHelper(Data[] playerData){

        this.playerData = playerData;

    }

    @Override
    public void run() {

    }

}
