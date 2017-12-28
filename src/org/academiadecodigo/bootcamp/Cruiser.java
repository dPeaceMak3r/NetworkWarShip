package org.academiadecodigo.bootcamp;

public class Cruiser extends Ship{


    private int size = 3;
    private boolean isDestroyed;

    public Cruiser(int size, String name) {
        super(size, name);
    }


    @Override
    public void hit() {

        super.hit();

    }


    public int getSize() {
        return size;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

}