package com.company;

public class EnigmaMachine {
    Plugboard plugboard;
    BasicRotor[] slots;
    Reflector reflector;

    public EnigmaMachine(){
        plugboard = new Plugboard();
        slots = new BasicRotor[3];
    }
    public void addPlug(char end1, char end2){
        plugboard.addPlug(end1, end2);
    }

    public void clearPlugboard(){
        plugboard.clear();
    }

    public void addRotor(BasicRotor basicRotor, int slot){
        slots[slot] = basicRotor;
    }

    public BasicRotor getRotor(int slot){
        return slots[slot];
    }


    public void addReflector(Reflector reflector){
        this.reflector = reflector;
    }


    public Reflector getReflector(){
        return reflector;
    }


    public void setPosition(int slot, int position){
        slots[slot].setPosition(position);
    }

    public char encodeLetter(char letter){
        Log.log("1" + letter);
        letter = plugboard.substitute(letter);

        int index = ((int) letter) - 65;
        //System.out.printf("Letter int %d, letter str %c%n", (int) letter, letter);

        index = getRotor(0).substitute(index);

        index = getRotor(1).substitute(index);
        //                   System.out.println(index);
        index = getRotor(2).substitute(index);
        //                  System.out.println(index);
        index = getReflector().substitute(index);
        index = getRotor(2).substituteBack(index);
        index = getRotor(1).substituteBack(index);
        index = getRotor(0).substituteBack(index);

        char result = (char) (index + 65);
        Log.log("2" + result);
        result = plugboard.substitute(result);
        Log.log("3" + result);

        getRotor(0).rotate();
        System.out.print(result);
        return result;
    }

    public void decodeLetter(char letter){

    }
}