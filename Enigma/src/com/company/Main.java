package com.company;

public class Main {

    public static void main(String[] args) {
	    start();
        EnigmaFile ef = new EnigmaFile("first.txt");
        ef.encode();
        ef.writeToFile();

        Bombe bombe = new Bombe("possibleAnswers.txt");
        bombe.challenge1();
        bombe.challenge2();
        bombe.challenge3();

    }

    public static void start(){

        test1();
        test2();

    }

    public static void test1(){
        /* TEST 1 */

        /* Create the enigma machine according to spec */
        EnigmaMachine machine = new EnigmaMachine();
        machine.addPlug('A', 'M');
        machine.addPlug('G', 'L');
        machine.addPlug('E', 'T');

        BasicRotor basicRotor1 = new BasicRotor("I");
        basicRotor1.setPosition(6);
        machine.addRotor(basicRotor1, 0);

        BasicRotor basicRotor2 = new BasicRotor("II");
        basicRotor2.setPosition(12);
        machine.addRotor(basicRotor2, 1);

        BasicRotor basicRotor3 = new BasicRotor("III");
        basicRotor3.setPosition(5);
        machine.addRotor(basicRotor3, 2);

        Reflector reflector = new Reflector();
        reflector.initialise("ReflectorI");
        machine.addReflector(reflector);

        /* Decode message given in spec */
        String word = "GFWIQH";
        for (char c : word.toCharArray()){
            machine.encodeLetter(c);
        }
    }

    public static void test2(){
        /* TEST 2 */

        /* Create the enigma machine according to spec */
        EnigmaMachine machine = new EnigmaMachine();
        machine.addPlug('B', 'C');
        machine.addPlug('R', 'I');
        machine.addPlug('S', 'M');
        machine.addPlug('A', 'F');

        BasicRotor basicRotor1 = new BasicRotor("IV");
        basicRotor1.setPosition(23);
        machine.addRotor(basicRotor1, 0);

        BasicRotor basicRotor2 = new BasicRotor("V");
        basicRotor2.setPosition(4);
        machine.addRotor(basicRotor2, 1);

        BasicRotor basicRotor3 = new BasicRotor("II");
        basicRotor3.setPosition(9);
        machine.addRotor(basicRotor3, 2);

        Reflector reflector = new Reflector();
        reflector.initialise("ReflectorII");
        machine.addReflector(reflector);

        /* Decode message given in spec */
        String word = "GACIG";
        for (char c : word.toCharArray()){
            machine.encodeLetter(c);
        }
    }
}
