package com.company;

public class Plug {
    private char end1;
    private char end2;

    public Plug(char end1, char end2){
        this.end1 = end1;
        this.end2 = end2;
    }

    public char getEnd1() {
        return end1;
    }

    public void setEnd1(char end1) {
        this.end1 = end1;
    }

    public char getEnd2() {
        return end2;
    }

    public void setEnd2(char end2) {
        this.end2 = end2;
    }

    public char encode(char letterIn){
        if (letterIn != end1 && letterIn != end2){
            return letterIn;
        } else if (letterIn == end1){
            return end2;
        } else {
            return end1;
        }
    }

    public Boolean clashesWith(Plug plugin){
        if (plugin.end1 == this.end1 || plugin.end1 == this.end2 || plugin.end2 == this.end1 || plugin.end2 == this.end2){
            return true;
        } else {
            return false;
        }
    }
}
