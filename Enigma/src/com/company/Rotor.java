package com.company;

public abstract class Rotor {
    protected final int ROTORSIZE = 26;
    protected String name;
    protected int position;
    protected int[] mapping;

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }

    public abstract void initialise(String type);
    public abstract int substitute(int index);
}
