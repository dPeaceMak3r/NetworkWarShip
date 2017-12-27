package org.academiadecodigo.bootcamp;

import java.util.concurrent.CopyOnWriteArrayList;

public class ShipFactory {



public CopyOnWriteArrayList createShips(){

    CopyOnWriteArrayList <Ship> shipList = new CopyOnWriteArrayList();



    Ship carrier = new Carrier(5);
    Ship battleship = new Battleship(4);
    Ship cruiser = new Cruiser(3);
    Ship submarine = new Submarine(3);
    Ship destroyer = new Destroyer(2);

    shipList.add(carrier);
    shipList.add(battleship);
    shipList.add(cruiser);
    shipList.add(submarine);
    shipList.add(destroyer);


    return shipList;  //To access ship by index:
                        // shipList.get(ShipTypes.theShipYouWant.getIndex());

}

}
