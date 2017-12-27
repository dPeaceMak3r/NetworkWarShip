package org.academiadecodigo.bootcamp;

public class Ship implements Shootable{

    private int size ;
    private boolean isDestroyed;


    public Ship(int size){
        this.size = size;
    }

    @Override
    public void hit() {

        if(size>1){
            size--;
            System.out.println("hit");
        }else{
            size--;
            isDestroyed = true;
            System.out.println("destroyed");
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
