package org.academiadecodigo.bootcamp;

import java.util.concurrent.CopyOnWriteArrayList;

public class Temp {


    public static void main(String[] args) {




        ShipFactory sf = new ShipFactory();

        CopyOnWriteArrayList <Ship> shipList = sf.createShips();


        System.out.println("list size: " + shipList.size());
        Destroyer d = new Destroyer(3, "Destroyer");
        System.out.print("Destr: ");
        d.hit();





        for (int i = 0; i < 5; i++) {
            System.out.print(i + " - "); System.out.println(shipList.get(i).toString());

            for (int j = 0; j < 5; j++) {
                shipList.get(i).hit();
            }

            System.out.println(shipList.get(i).isDestroyed());


            System.out.print("size: ");System.out.println(shipList.get(i).getSize());
            System.out.println();
            System.out.println(shipList.get(i).getMessage());
            System.out.println();

        }


        System.out.println();
        System.out.println();


        System.out.println(shipList.get(ShipTypes.BATTLESHIP.getIndex()).toString());




    }
}