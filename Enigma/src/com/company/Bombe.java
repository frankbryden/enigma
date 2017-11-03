package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Bombe {
    EnigmaMachine enigmaMachine;
    String encodedMessage, wordHint;
    File outputFile;
    PrintWriter printWriter;
    public Bombe(String outputPath){
        outputFile = new File(outputPath);
        try {
            printWriter = new PrintWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            System.err.printf("Failed to open file (%s) for writing. Error : %s. Exiting.%n", outputFile.toString(), e.getMessage());
            System.exit(-1);
        }
    }

    /* takes a string and encodes it, and tests to see if wordHint is in the encoded string */
    public Boolean tryWord(String word){
        StringBuilder builder = new StringBuilder();
        for (char letter : word.toCharArray()){
            builder.append(enigmaMachine.encodeLetter(letter));
        }
        return builder.toString().contains(wordHint);
    }


    public String encodeMessage(String message){
        StringBuilder builder = new StringBuilder();
        for (char letter : message.toCharArray()){
            builder.append(enigmaMachine.encodeLetter(letter));
        }
        return builder.toString();
    }

    /* For this challenge, we must find the ends for plugs connected to D and S */
    public void challenge1(){
        enigmaMachine = new EnigmaMachine();

        /* Add first basic rotor */
        BasicRotor basicRotor1 = new BasicRotor("IV");
        basicRotor1.setPosition(8);
        enigmaMachine.addRotor(basicRotor1, 0);

        /* Add second basic rotor */
        BasicRotor basicRotor2 = new BasicRotor("III");
        basicRotor2.setPosition(4);
        enigmaMachine.addRotor(basicRotor2, 1);

        /* Add third basic rotor */
        BasicRotor basicRotor3 = new BasicRotor("II");
        basicRotor3.setPosition(21);
        enigmaMachine.addRotor(basicRotor3, 2);

        /* Add the reflector */
        Reflector reflector = new Reflector();
        reflector.initialise("ReflectorI");
        enigmaMachine.addReflector(reflector);

        encodedMessage = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
        wordHint = "ANSWER";

        /* We need to try every (x, y) pair of letters (where x and y are letters) as ends to the two plugs */
        for (int i = 0; i < 26; i++){
            for (int j = 0; j < 26; j++){
                enigmaMachine.clearPlugboard();
                char endx = (char) (i + 65);
                char endy = (char) (j + 65);
                enigmaMachine.addPlug('D', endx);
                enigmaMachine.addPlug('S', endy);
                String decodedMessage = encodeMessage(encodedMessage);
                if (decodedMessage.contains(wordHint)){
                    System.out.println("\nFound solution ! ");
                    System.out.printf("D -> %c, S -> %c -- %s%n", endx, endy, decodedMessage);
                    printWriter.printf("D -> %c, S -> %c -- %s%n", endx, endy, decodedMessage);
                } else {
                    //System.out.printf("(%c, %c)%n", endx, endy);
                    //System.out.printf("Position : %d%n", enigmaMachine.getRotor(0).position);
                }
            }
        }
        printWriter.flush();
        printWriter.close();

    }
}
