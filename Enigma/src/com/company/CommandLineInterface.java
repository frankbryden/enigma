package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineInterface {
    private EnigmaMachine enigmaMachine;
    private Scanner scanner;
    private String encodedMessage = null;

    public CommandLineInterface(){
        this.enigmaMachine = new EnigmaMachine();
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("======== Command Line Interface ========");
        this.configure();
    }

    private void encode(){
        if (this.encodedMessage == null){
            System.err.println("encodedMessage is null, it must be set before running this method!");
            return;
        }
        for (char letter : this.encodedMessage.toCharArray()){
            this.enigmaMachine.encodeLetter(letter);
        }
    }

    private void configure(){
        this.setPlugs();
        this.setRotors();
        this.setReflector();
        System.out.println("Enigma machine configured !");
        System.out.println(enigmaMachine.toString());
        this.setEncodedMessage();
        this.encode();
    }

    private void setPlugs(){
        System.out.println("Add your plugs. Format is G-T. F when you are finished.");
        Pattern plugPattern = Pattern.compile("^([A-Z]{1})-([A-Z]{1})$");
        String userInput = "";
        boolean havePlugs = false;
        while(!havePlugs){
            /* Get user input and sanitize it */
            userInput = scanner.nextLine().trim().toUpperCase();
            Matcher matcher = plugPattern.matcher(userInput);
            if (matcher.find()){
                char end1 = matcher.group(1).toCharArray()[0];
                char end2 = matcher.group(2).toCharArray()[0];
                System.out.printf("Added plug with end 1 : %s, end 2 : %s%n", end1, end2);
                this.enigmaMachine.addPlug(end1, end2);
            } else if (userInput.compareTo("F") == 0){
                havePlugs =  true;
            } else {
                System.out.println("Sorry, invalid input.");
            }
        }

        System.out.printf("There are now %d plugs in your plugboard.%n", this.enigmaMachine.getPlugboard().getNumPlugs());
        System.out.println("Finished adding plugs.");
    }

    private void setRotors(){
        System.out.printf("Add rotors now.%n");
        for (int slot = 0; slot < 3; slot++){
            System.out.printf("New rotor in slot %d%n", slot);

            /* What rotor does the user want ? */
            System.out.printf("0 - BasicRotor%n1 - TurnoverRotor%n");
            int rotorChoice = getIntInRange(0, 1);

            /* Ask user for rotor type */
            System.out.printf("1 - typeI%n2 - typeII%n3 - typeIII%n4 - typeIV%n5 - typeV%n");
            int typeChoice = getIntInRange(1, 5);

            /* Ask user for initial position */
            System.out.println("Initial position ? 0 - 25");
            int initialPosition = getIntInRange(0, 25);

            /* Add rotor to enigma machine according to user's choices */
            switch (rotorChoice){
                case 0:
                    BasicRotor basicRotor = new BasicRotor(indexToNumeral(typeChoice));
                    basicRotor.setPosition(initialPosition);
                    enigmaMachine.addRotor(basicRotor, slot);
                case 1:
                    TurnoverRotor turnoverRotor = new TurnoverRotor(indexToNumeral(typeChoice));
                    turnoverRotor.setPosition(initialPosition);
                    enigmaMachine.addRotor(turnoverRotor, slot);
            }

            /* Set next rotor property for turnover rotors */

            /* If we are on slot 1 or 2 */
            if (slot >= 1){
                /* if the previous rotor is a turnover rotor */
                if (enigmaMachine.getRotor(slot - 1) instanceof TurnoverRotor){
                    /* then set its next rotor field to the current rotor */
                    ((TurnoverRotor) enigmaMachine.getRotor(slot - 1)).setNextRotor(enigmaMachine.getRotor(slot));
                }
            }

            /* Print out information about the rotor that was just added in the same format as the spec */
            /* slot 0 = TurnoverRotor typeI , initial position 23 */
            System.out.printf("slot %d = %s %s, initial position %d%n", slot, enigmaMachine.getRotor(slot).toString(), indexToNumeral(typeChoice), initialPosition);

        }
    }

    private void setReflector(){
        System.out.println("Add reflector now.");

        /* Get reflector type from user */
        System.out.printf("1 - ReflectorI%n2 - ReflectorII%n");
        int reflectorChoice = getIntInRange(1, 2);

        /* Create the reflector with the correct type */
        Reflector reflector = new Reflector(getReflectorTypeFromInt(reflectorChoice));

        /* And add it to the machine */
        this.enigmaMachine.addReflector(reflector);

        System.out.printf("Added %s%n", getReflectorTypeFromInt(reflectorChoice));

    }

    /* Returns an integer in the range lowerRange - upperRange */
    private int getIntInRange(int lowerRange, int upperRange){
        while (true){
            try{
                int userInput = this.scanner.nextInt();
                if (userInput >= lowerRange && userInput <= upperRange){
                    /* When we have met both conditions, return the integer */
                    return userInput;
                } else {
                    System.out.printf("Integer should be in the range %d - %d%n", lowerRange, upperRange);
                }
            }catch(InputMismatchException e){
                System.out.printf("Invalid input ! Expecting an integer in the range %d - %d ", lowerRange, upperRange);
                /* Clear scanner buffer. If we have reached an exception, it means scanner.nextInt() failed and the non-integer data in the buffer remains, so we must clear it. */
                scanner.nextLine();
            }
        }
    }

    private void setEncodedMessage(){
        System.out.println("It is now time to set the encoded message.");
        System.out.println("Simply type or copy/paste your message.");
        this.scanner.nextLine();
        String message = this.scanner.nextLine();
        this.encodedMessage = sanitiseInput(message);
    }


    private String getReflectorTypeFromInt(int reflectorType){
        switch (reflectorType){
            case 1:
                return "ReflectorI";
            case 2:
                return "ReflectorII";
            default:
                System.err.printf("Invalid reflectorType %d%n", reflectorType);
                return null;
        }
    }

    public String indexToNumeral(int index){
        switch (index){
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            default:
                System.err.printf("Invalid index %d%n", index);
                return null;
        }
    }

    public String sanitiseInput(String message){
        /* Helper method to method to sanitise message given by user (make upperCase, remove invalid characters and whitespace) */
        /* Takes in the raw message and returns the cleaned string */

        final int ASCII_A = 65;
        final int ASCII_Z = 90;

        StringBuilder stringBuilder = new StringBuilder();
        for (char letter : message.toUpperCase().toCharArray()){
            int asciiCode = (int) letter;
            if (asciiCode >= ASCII_A && asciiCode <= ASCII_Z){
                stringBuilder.append(letter);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString(){
        return enigmaMachine.toString();
    }



}
