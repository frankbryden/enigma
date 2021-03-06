package com.company;

public class EnigmaMachine {
    private Plugboard plugboard;
    private BasicRotor[] slots;
    private Reflector reflector;

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

    public void encodeLetter(char letter){
        letter = plugboard.substitute(letter);

         /* Convert ascii code to 0-25 alphabet index */
        int index = ((int) letter) - 65;

         /* Pass the char through every rotor */
        index = getRotor(0).substitute(index);

        index = getRotor(1).substitute(index);

        index = getRotor(2).substitute(index);


        /* then through the reflector */
        index = getReflector().substitute(index);


        /* And back down the other way */
        index = getRotor(2).substituteBack(index);

        index = getRotor(1).substituteBack(index);

        index = getRotor(0).substituteBack(index);


        char result = (char) (index + 65);
        result = plugboard.substitute(result);

        getRotor(0).rotate();

        /* We display the resulting encoded char */
        System.out.print(result);
    }


    public char encodeLetterWithReturn(char letter){
        letter = plugboard.substitute(letter);

         /* Convert ascii code to 0-25 alphabet index */
        int index = ((int) letter) - 65;

         /* Pass the char through every rotor */
        index = getRotor(0).substitute(index);
        index = getRotor(1).substitute(index);
        index = getRotor(2).substitute(index);

        /* then through the reflector */
        index = getReflector().substitute(index);

        /* And back down the other way */
        index = getRotor(2).substituteBack(index);
        index = getRotor(1).substituteBack(index);
        index = getRotor(0).substituteBack(index);

        char result = (char) (index + 65);
        result = plugboard.substitute(result);

        getRotor(0).rotate();

        /* We display the resulting encoded char */
        return result;
    }


    /* Extension to provide functionality to the CommandLineInterface */
    public Plugboard getPlugboard(){
        return this.plugboard;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Plugs ");
        builder.append(this.plugboard.toString() + "\n");
        builder.append("Rotors\n");
        for (int i = 0; i < 3; i++){
            builder.append(String.format("\t\tslot %d = %s%n", i, getRotor(i).toString()));
        }
        builder.append(getReflector().toString() + "\n");
        return builder.toString();
    }
}