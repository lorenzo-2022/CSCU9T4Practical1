package com.stir.cscu9t4practical1;

import javax.swing.*;
import java.util.ListIterator;

public class SprintEntry extends Entry {
    private int repetitions;
    private int recovery;

    public SprintEntry(String name, int day, int month, int year, int hour, int minute, int second, float distance, int repetitions, int recovery) {
        super(name, day, month, year, hour, minute, second, distance);
        this.repetitions = repetitions;
        this.recovery = recovery;
    }

    //setting getter methods for attributes that are not in parent class
    //repetitions getter
    public int getRepetitions () {
        return repetitions;
    }
    //recovery getter
    public int getRecovery () {
        return recovery;
    }

}
