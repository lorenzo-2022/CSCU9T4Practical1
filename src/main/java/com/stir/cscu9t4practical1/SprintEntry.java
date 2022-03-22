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

    //over-ridden methods from parent class: Entry
    public String getEntry(){
        //do stuff
        if (repetitions > 1) {
            String result = getName() + " sprinted " + getRepetitions() + "x" + Math.round(getDistance()) + "m "
                    + "in " + getHour() + ":" + getMin() + ":" + getSec()
                    + " with " + getRecovery() + " minutes recovery on "
                    + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
            return result;
        }

        else {
            String result = getName() + " ran " + getDistance() + " km "
                    + " in " + getHour() + ":" + getMin() + ":" + getSec() + " on "
                    + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
            return result;
        }
    }
}
