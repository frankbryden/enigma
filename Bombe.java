import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Bombe {
    private EnigmaMachine enigmaMachine;
    private String encodedMessage, wordHint;
    private File outputFile;
    public Bombe(){

    }



    public String encodeMessage(String message){
        StringBuilder builder = new StringBuilder();
        for (char letter : message.toCharArray()){
            builder.append(enigmaMachine.encodeLetterWithReturn(letter));
        }
        return builder.toString();
    }


    /* For this challenge, we must find the ends for plugs connected to D and S */
    public void challenge1(String outputPath){
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
        Reflector reflector = new Reflector("ReflectorI");
        enigmaMachine.addReflector(reflector);

        encodedMessage = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
        wordHint = "ANSWER";

        /* Set up file I/O */
        PrintWriter printWriter = null;
        outputFile = new File(outputPath);
        try {
            printWriter = new PrintWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            System.err.printf("Failed to open file (%s) for writing. Error : %s. Exiting.%n", outputFile.toString(), e.getMessage());
            System.exit(-1);
        }

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

    public void challenge2(String outputPath){
        enigmaMachine = new EnigmaMachine();

        enigmaMachine.addPlug('H', 'L');
        enigmaMachine.addPlug('G', 'P');

        /* Add first basic rotor */
        BasicRotor basicRotor1 = new BasicRotor("V");
        enigmaMachine.addRotor(basicRotor1, 0);

        /* Add second basic rotor */
        BasicRotor basicRotor2 = new BasicRotor("III");
        enigmaMachine.addRotor(basicRotor2, 1);

        /* Add third basic rotor */
        BasicRotor basicRotor3 = new BasicRotor("II");
        enigmaMachine.addRotor(basicRotor3, 2);

        /* Add the reflector */
        Reflector reflector = new Reflector("ReflectorI");
        enigmaMachine.addReflector(reflector);

        /* Data given to us by spec */
        encodedMessage = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
        wordHint = "ELECTRIC";

        /* Set up file I/O */
        PrintWriter printWriter = null;
        outputFile = new File(outputPath);
        try {
            printWriter = new PrintWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            System.err.printf("Failed to open file (%s) for writing. Error : %s. Exiting.%n", outputFile.toString(), e.getMessage());
            System.exit(-1);
        }

        /* We need to try every (x, y, z) triplet of possible initial positions for the rotors */
        for (int x = 0; x < basicRotor1.ROTORSIZE; x++){
            for (int y = 0; y < basicRotor2.ROTORSIZE; y++){
                for (int z = 0; z < basicRotor3.ROTORSIZE; z++){

                    /* Set the positions */
                    basicRotor1.setPosition(x);
                    basicRotor2.setPosition(y);
                    basicRotor3.setPosition(z);

                    /* Decode the message using the current settings */
                    String decodedMessage = encodeMessage(encodedMessage);

                    if (decodedMessage.contains(wordHint)){
                        System.out.println("\nFound solution ! ");
                        System.out.printf("(%d, %d, %d) -> %s%n", x, y, z, decodedMessage);
                        printWriter.printf("(%d, %d, %d) -> %s%n", x, y, z, decodedMessage);
                    } else {
                        //System.out.printf("(%c, %c)%n", endx, endy);
                        //System.out.printf("Position : %d%n", enigmaMachine.getRotor(0).position);
                    }
                }
            }
        }
        /* Flush and close file */
        printWriter.flush();
        printWriter.close();
    }

    public void challenge3(String outputPath){
        enigmaMachine = new EnigmaMachine();

        enigmaMachine.addPlug('M', 'F');
        enigmaMachine.addPlug('O', 'I');

        ArrayList<String> rotorTypes = new ArrayList<String>();
        rotorTypes.add("I");
        rotorTypes.add("II");
        rotorTypes.add("III");
        rotorTypes.add("IV");
        rotorTypes.add("V");

         /* Add the reflector */
        Reflector reflector = new Reflector("ReflectorI");
        reflector.initialise("");
        enigmaMachine.addReflector(reflector);

        /* Data given to us by spec */
        encodedMessage = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
        wordHint = "JAVA";

        /* Set up file I/O */
        PrintWriter printWriter = null;
        outputFile = new File(outputPath);
        try {
            printWriter = new PrintWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            System.err.printf("Failed to open file (%s) for writing. Error : %s. Exiting.%n", outputFile.toString(), e.getMessage());
            System.exit(-1);
        }

        for (String type1 : rotorTypes){
            for (String type2 : rotorTypes){
                for (String type3 : rotorTypes){
                    /* Add first basic rotor */
                    BasicRotor basicRotor1 = new BasicRotor(type1);
                    basicRotor1.setPosition(22);
                    enigmaMachine.addRotor(basicRotor1, 0);

                    /* Add second basic rotor */
                    BasicRotor basicRotor2 = new BasicRotor(type2);
                    basicRotor2.setPosition(24);
                    enigmaMachine.addRotor(basicRotor2, 1);

                    /* Add third basic rotor */
                    BasicRotor basicRotor3 = new BasicRotor(type3);
                    basicRotor3.setPosition(23);
                    enigmaMachine.addRotor(basicRotor3, 2);

                    String decodedMessage = encodeMessage(encodedMessage);

                    if (decodedMessage.contains(wordHint)){
                        System.out.println("\nFound solution ! ");
                        System.out.printf("(%s, %s, %s) -> %s%n", type1, type2, type3, decodedMessage);
                        printWriter.printf("(%s, %s, %s) -> %s%n", type1, type2, type3, decodedMessage);
                    } else {
                        //System.out.printf("(%c, %c)%n", endx, endy);
                        //System.out.printf("Position : %d%n", enigmaMachine.getRotor(0).position);
                    }
                }
            }
        }

        /* Flush and close file */
        printWriter.flush();
        printWriter.close();
    }
}

