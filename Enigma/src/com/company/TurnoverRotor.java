package com.company;

import javax.print.DocFlavor;

public class TurnoverRotor extends BasicRotor {
    private int turnoverPosition;
    private BasicRotor nextRotor;
    private String type;
    public TurnoverRotor(String type) {
        super(type);
        this.type = type;
        super.initialise(type);
    }

    public void setNextRotor(BasicRotor nextRotor){
        this.nextRotor = nextRotor;
    }

    @Override
    public void initialise(String type){
        switch(type){
            case "I":
                turnoverPosition = 24;
                break;
            case "II":
                turnoverPosition = 12;
                break;
            case "III":
                turnoverPosition = 3;
                break;
            case "IV":
                turnoverPosition = 17;
                break;
            case "V":
                turnoverPosition = 7;
                break;
        }
    }

    @Override
    public void rotate(){
        position = (position + 1) % ROTORSIZE;
        if (position == turnoverPosition){
            if (nextRotor != null){
                nextRotor.rotate();
            }
        }
    }

    /* Extension to provide functionality to the CommandLineInterface */
    @Override
    public String toString(){
        return String.format("\t\tTurnoverRotor %s, position %d", this.type, this.position);
    }
}
