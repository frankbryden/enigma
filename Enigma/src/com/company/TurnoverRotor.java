package com.company;

public class TurnoverRotor extends BasicRotor {
    int turnoverPosition;
    BasicRotor nextRotor;
    public TurnoverRotor(String type) {
        super(type);
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
}
