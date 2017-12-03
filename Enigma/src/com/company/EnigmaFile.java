package com.company;

import java.io.*;

public class EnigmaFile {

    private static final String ENCODED_FILE_SUFFIX = "_encoded";
    private static final int ASCII_A = 65;
    private static final int ASCII_Z = 90;

    private File enigmaFile, enigmaFileOutput;
    private BufferedReader bufferedReader;
    private String enigmaFileContents;
    private PrintWriter printWriter;
    private EnigmaMachine enigmaMachine;
    private StringBuilder outputBuilder;
    private BasicRotor basicRotor1,basicRotor2, basicRotor3;
    private TurnoverRotor turnoverRotor1, turnoverRotor2, turnoverRotor3;
    private Reflector reflector1;

    public EnigmaFile(String path){

        /* Create file objects */

        /* Input */
        this.enigmaFile = new File(path);

        /* Create buffered reader object and handle any IO exception */
        try {
            this.bufferedReader = new BufferedReader(new FileReader(enigmaFile));
        } catch (FileNotFoundException e) {
            System.err.println("Failed to read file " + e.getMessage());
            System.exit(-1);
        }

        /* Use helper method to clean file (upperCase, remove invalid characters and whitespace) */
        this.enigmaFileContents = cleanFile(this.bufferedReader);

        /* We don't need the bufferedReader anymore - close it so it can be garbage collected and the resource freed */
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Objects used for output */
        this.enigmaFileOutput = new File(getOutputPath(enigmaFile.getPath()));
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
        Reflector reflector = new Reflector("ReflectorI");
        enigmaMachine.addReflector(reflector);

    }

    public String cleanFile(BufferedReader reader){
        /* Helper method to method to clean file (upperCase, remove invalid characters and whitespace) */
        /* Takes in a bufferedReader to file and returns the cleaned string */

        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (char letter : bufferedReader.readLine().toUpperCase().toCharArray()){
                int asciiCode = (int) letter;
                if (asciiCode >= ASCII_A && asciiCode <= ASCII_Z){
                    stringBuilder.append(letter);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private String getOutputPath(String inputPath){
        /*String Builder to hold the final output path */
        StringBuilder outputPathBuilder = new StringBuilder();

        /* Split inputPath to separate file name and extension */
        String[] parts = inputPath.split("\\.");

        /* Start off with file name */
        outputPathBuilder.append(parts[0]);

        /* Add a suffix to make it stand out from input file */
        outputPathBuilder.append(ENCODED_FILE_SUFFIX);

        /* Finish by adding the dot followed by the extension */
        outputPathBuilder.append(".");
        outputPathBuilder.append(parts[1]);

        /* And we have our output file name */
        return outputPathBuilder.toString();
    }

    public void encode(){
        for (char letter : enigmaFileContents.toCharArray()){
            outputBuilder.append(enigmaMachine.encodeLetterWithReturn(letter));
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

    /* Extension to provide functionality to the CommandLineInterface */
    @Override
    public String toString(){
        return this.enigmaMachine.toString();
    }
}
