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

    public char substitute(char c){
        for (Plug plug : plugs){
            if (plug.encode(c) != c){
                Log.log("Mapped " + c + " to " + plug.encode(c));
                return plug.encode(c);
            }
        }
        Log.log("End not connected, character " + c + " not mapped.");
        return c;
    }

}
