package com.stir.cscu9t4practical1;

public class SwimEntry extends Entry {
    private String where;

    public SwimEntry(String name, int day, int month, int year, int hour, int minutes, int seconds, float distance, String where) {
        super(name, day, month, year, hour, minutes, seconds, distance);
        this.where = where;
    }

    //setting getter methods for attributes that are not in parent class

    //where getter
    public String getWhere(){return where;}

    //over-ridden methods from parent class: Entry
    public String getEntry(){
        //do stuff
        if (where.equals("outdoors")) {
            String result = getName() + " swam " + getDistance() + " km "
                    + where + " in "
                    + getHour() + ":" + getMin() + ":" + getSec() + " on "
                    + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
            return result;
        }
        else{
            String result = getName() + " swam " + getDistance() + " km "
                    + " at the " + where
                    + " in " + getHour() + ":" + getMin() + ":" + getSec() + " on "
                    + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
            return result;
        }
    }
}
