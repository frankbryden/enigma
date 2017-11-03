package com.company;

import java.io.*;

public class EnigmaFile {

    private static final String ENCODED_FILE_SUFFIX = "_encoded";

    File enigmaFile, enigmaFileOutput;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    EnigmaMachine enigmaMachine;
    StringBuilder outputBuilder;
    //BasicRotor basicRotor1,basicRotor2, basicRotor3;
    TurnoverRotor turnoverRotor1, turnoverRotor2, turnoverRotor3;
    Reflector reflector1;

    public EnigmaFile(String path){

        /* Create file objects */

        /* Input */
        this.enigmaFile = new File(path);

        /* Create buffered reader object while handling IO exception */
        try {
            this.bufferedReader = new BufferedReader(new FileReader(enigmaFile));
        } catch (FileNotFoundException e) {
            System.err.println("Failed to read file" + e.getMessage());
            System.exit(-1);
        }

        /* Output */
        this.enigmaFileOutput = new File(path + ENCODED_FILE_SUFFIX);
        this.outputBuilder = new StringBuilder();

        /* Create print writer object while handling IO exception */
        try {
            this.printWriter = new PrintWriter(new FileWriter(enigmaFileOutput));
        } catch (IOException e) {
            System.err.println("Failed to create print writer object " + e.getMessage());
            System.exit(-1);
        }

        /* Create Enigma machine */
        enigmaMachine = new EnigmaMachine();

        /* Add the plugs */
        enigmaMachine.addPlug('Q', 'F');

        /* Add first basic rotor */
        turnoverRotor1 = new TurnoverRotor("I");
        turnoverRotor1.setPosition(23);
        enigmaMachine.addRotor(turnoverRotor1, 0);

        /* Add second basic rotor */
        turnoverRotor2 = new TurnoverRotor("II");
        turnoverRotor2.setPosition(11);
        enigmaMachine.addRotor(turnoverRotor2, 1);

        /* Add third basic rotor */
        turnoverRotor3 = new TurnoverRotor("III");
        turnoverRotor3.setPosition(7);
        enigmaMachine.addRotor(turnoverRotor3, 2);

        /* Add next rotor members */
        turnoverRotor1.setNextRotor(turnoverRotor2);
        turnoverRotor2.setNextRotor(turnoverRotor3);

        /* Add reflector */
        Reflector reflector = new Reflector();
        reflector.initialise("ReflectorI");
        enigmaMachine.addReflector(reflector);

    }

    public void encode(){
        try {
            while (bufferedReader.ready()){
                for (char letter : bufferedReader.readLine().toCharArray()){
                    outputBuilder.append(enigmaMachine.encodeLetter(letter));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(){
        /* Get the string from the string builder */
        String output = outputBuilder.toString();

        /* Write to the file */
        printWriter.print(output);
        printWriter.flush();

        /* Close the resource */
        printWriter.close();
    }
}
