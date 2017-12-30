package org.academiadecodigo.bootcamp.Server.Ships;

import java.util.concurrent.CopyOnWriteArrayList;

public class ShipFactory {



public CopyOnWriteArrayList createShips(){

    CopyOnWriteArrayList <Ship> shipList = new CopyOnWriteArrayList();



    Ship carrier = new Carrier(5, "Carrier");
    Ship battleship = new Battleship(4, "Battleship");
    Ship cruiser = new Cruiser(3, "Cruiser");
    Ship submarine = new Submarine(3,"Submarine");
    Ship destroyer = new Destroyer(2, "Destroyer");

    shipList.add(carrier);
    shipList.add(battleship);
    shipList.add(cruiser);
    shipList.add(submarine);
    shipList.add(destroyer);


    return shipList;  //To access ship by index:
                        // shipList.get(ShipTypes.theShipYouWant.getIndex());

}

}
