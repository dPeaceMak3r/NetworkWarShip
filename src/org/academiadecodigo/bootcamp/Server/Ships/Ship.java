package org.academiadecodigo.bootcamp.Server.Ships;

public class Ship implements Shootable{

    private int size ;
    private boolean isDestroyed;
    private String name;


    public Ship(int size, String name){
        this.size = size;
        this.name = name;

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

    public String getMessage(){

        return "Your " + name + " has been destroyed!";
    }
}
