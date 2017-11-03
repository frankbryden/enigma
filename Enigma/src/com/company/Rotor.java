package com.company;

public abstract class Rotor {
    final int ROTORSIZE = 26;
    String name;
    int position;
    int[] mapping;

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }

    public abstract void initialise(String type);
    public abstract int substitute(int index);
}
