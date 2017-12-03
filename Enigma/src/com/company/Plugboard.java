package com.company;

import java.util.ArrayList;

public class Plugboard {
    private ArrayList<Plug> plugs;

    public Plugboard(){
        plugs = new ArrayList<Plug>();
    }

    public Boolean addPlug(char end1, char end2){
        Plug newPlug = new Plug(end1, end2);

        for (Plug plug : plugs){
            if (plug.clashesWith(newPlug)){
                return false;
            }
        }

        plugs.add(newPlug);
        return true;
    }

    public int getNumPlugs(){
        return plugs.size();
    }

    public void clear(){
        plugs.clear();
    }

    public char substitute(char letter){
        for (Plug plug : plugs){
            if (plug.encode(letter) != letter){
                return plug.encode(letter);
            }
        }
        /* We need to have a return statement outside of for loop in case there are no plugs connected. In that case, simply return the letter passed as a parameter */
        return letter;
    }

    /* Extension to provide functionality to the CommandLineInterface */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (Plug plug : plugs){
            builder.append(plug.toString());
            builder.append(" ");
        }
        return builder.toString();
    }

}