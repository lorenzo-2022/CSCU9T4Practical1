package com.stir.cscu9t4practical1;

public class CycleEntry extends Entry {
    private String terrain;
    private String tempo;

    //setting up the constructor and adding the terrain and tempo values to the cycleEntry object
    public CycleEntry(String name, int day, int month, int year, int hour, int minutes, int seconds, float distance, String terrain, String tempo) {
        super(name, day, month, year, hour, minutes, seconds, distance);
        this.terrain = terrain;
        this.tempo = tempo;
    }

    //setting up the getter methods for the CycleEntry object attributes that are not covered by the Entry class.

    //get terrain
    public String getTerrain () {return terrain;}

    //get tempo
    public String getTempo () {return tempo;}

    //over-ridden methods from parent class: Entry
    public String getEntry(){
        //do stuff
        String result = getName()+" cycled " + getDistance() + " km "
                +"at a " + tempo + " tempo "
                +"on " + terrain + " terrain "
                +"in " + getHour()+":"+getMin()+":"+ getSec() + " on "
                +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
        return result;
    }
}
