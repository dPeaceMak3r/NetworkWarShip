package org.academiadecodigo.bootcamp.Server.Ships;

public enum ShipTypes {


    CARRIER (0),
    BATTLESHIP(1),
    CRUSIER(2),
    SUBMARINE(3),
    DESTROIER(4);

    private int index;




    ShipTypes(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
