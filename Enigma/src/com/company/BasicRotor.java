package com.company;

public class BasicRotor extends Rotor {
    private String type;
    public BasicRotor(String type){
        initialise(type);
        this.type = type;
    }

    @Override
    public void initialise(String type){

        switch (type){
            case "I":
                mapping = new int[] { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
                break;
            case "II":
                mapping = new int[] { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
                break;
            case "III":
                mapping = new int[] { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
                break;
            case "IV":
                mapping = new int[] { 4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };
                break;
            case "V":
                mapping = new int[] { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
                break;
            default:
                System.out.println("Type is invalid " + type);
        }
    }

    @Override
    public int substitute(int index){
        //Add the position to the index
        //Bring it back to a 0-25 range in case it overlaps
        index -= position;
        if (index < 0){
            index += ROTORSIZE;
        }

        if (mapping == null){
            System.err.println("MAPPING IS NULL!!");
        }
        /* Use the new index to get mapping */
        int mapped_char = mapping[index];


        return (mapped_char + position) % ROTORSIZE;
    }

    public int substituteBack(int index){
        int[] inverseMapping = new int[ROTORSIZE];
        for (int i = 0; i < ROTORSIZE; i++){
            /* Construct an inverse mapping by creating an array where the indices are the values of the mapping array, and the values are the indices of the mapping array */
            inverseMapping[mapping[i]] = i;
        }
        /* Add the position to the index */
        /* Bring it back to a 0-25 range in case it overlaps */
        index = (index - position + ROTORSIZE) % ROTORSIZE;

        /* Use the new index to get mapping */
        int mapped_char = inverseMapping[index];

        return (mapped_char + position) % ROTORSIZE;
    }

    public void rotate(){
        position = (position + 1) % ROTORSIZE;
    }

    /* Extension to provide functionality to the CommandLineInterface */
    @Override
    public String toString(){
        return String.format("BasicRotor %s, position %d", this.type, this.position);
    }
}
