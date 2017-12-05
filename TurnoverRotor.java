public class TurnoverRotor extends BasicRotor {
    private int turnoverPosition;
    private BasicRotor nextRotor;
    /* The type field is used in the toString() method, which is itself used in the CLI extension */
    private String type;


    public TurnoverRotor(String type) {
        super(type);
        this.type = type;
        super.initialise(type);
    }

    public void setNextRotor(BasicRotor nextRotor){
        this.nextRotor = nextRotor;
    }

    @Override
    public void initialise(String type){
        /* Set turnover position according to type */
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
        /* Add 1 to the position, and wrap around if we get to ROTORSIZE */
        position = (position + 1) % ROTORSIZE;

        /* if the current position corresponds to the turnover position, rotate the next rotor */
        if (position == turnoverPosition){
            /* Make sure the next rotor is not null ! This will be the case for the rotor in slot 3 */
            if (nextRotor != null){
                nextRotor.rotate();
            }
        }
    }

    /* Extension to provide functionality to the CommandLineInterface */
    @Override
    public String toString(){
        return String.format("\t\tTurnoverRotor %s, position %d", this.type, this.position);
    }
}
