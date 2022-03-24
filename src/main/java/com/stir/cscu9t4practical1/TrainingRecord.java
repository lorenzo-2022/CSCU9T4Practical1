// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import javax.swing.*;
import java.util.*;


public class TrainingRecord {
    private List<Entry> trainingRecord;
    
    public TrainingRecord() {
        trainingRecord = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public void addEntry(Entry entry) {
       //I need to make sure that the entry I want to add to my records is unique.
       //name, day, month, and year is a unique key for running records (athletes do at most one run per day)
       if (isUnique(entry)) {
           trainingRecord.add(entry);
       }
       else {
           JOptionPane.showMessageDialog(null, "error: entry has already been added!");
       }
   }

    public boolean isUnique (Entry entry) {
        boolean isUnique = true;

        ListIterator<Entry> recordIterator = trainingRecord.listIterator();

        while (recordIterator.hasNext()) {
            Entry currentRecord = recordIterator.next();

            if (currentRecord.getName().equals(entry.getName()) &&
                    currentRecord.getDay() == entry.getDay() &&
                    currentRecord.getMonth() == entry.getMonth() &&
                    currentRecord.getYear() == entry.getYear()){
                isUnique = false;
            }
        }
        return isUnique;
    }
   
   // look up the entry of a given day and month
   public String lookupEntryByDate(int d, int m, int y) {
       ListIterator<Entry> iter = trainingRecord.listIterator();
       String result = "";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
              result = result + current.getEntry();
          }
       }
       if (result.equals("")){
           result = "No entries found";
       }
       return result;
   } // lookupEntry

    // look up entry of a given name
    public String findAllByName(String inputName) {
        String result = "";
        for (Entry current : trainingRecord) {
            if (current.getName().equals(inputName)) {
                result = result + current.getEntry();
            }
        }
        if (result.equals("")) { return "No entries found"; }
        return result;
    }

   // Count the number of entries
   public int getNumberOfEntries(){
       return trainingRecord.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       trainingRecord.clear();
   }

    //remove entry with given name, day, month, year
    public String removeEntry(String inputName, int m, int d, int y) {
        String result = "";
        String removedEntryString = "";

        //making a null entry object reference variable to point to the entry object that needs to be removed
        Entry toBeRemoved = null;

        //new list iterator to iterate through the training record objects
        ListIterator<Entry> iterator = trainingRecord.listIterator();
        //while loop to loop through iterator
        while (iterator.hasNext()) {
            //selecting the next record in our list of records
            Entry current = iterator.next();
            //if the current record matches the one that the user wants to remove
            if (current.getName().equals(inputName) && current.getMonth() == m && current.getDay() == d && current.getYear() == y) {
                //then this entry must be removed
                removedEntryString = current.getEntry();
                toBeRemoved = current;
            }
        }

        //removing the entry with the same details as those provided by the user if one was found
        if (toBeRemoved != null) {
            trainingRecord.remove(toBeRemoved);
        }

        //returning the result message to the method called, saying which entry was removed
        result = result + "Removed entry: " + removedEntryString + "\n";
        return result;
    }

    public String findWeeklyDistance(String athleteName) {
        String weekRecords = "";

        float weekRunDistance = 0;
        float weekCycleDistance = 0;
        float weekSwimDistance = 0;

        //Calendar's getInstance method returns a Calendar object whose calendar fields have been initialized with the current date and time.
        //current date and time
        Calendar rightNow = Calendar.getInstance();
        //Calendar's add method adds or subtracts the specified amount of time to the given calendar field
        rightNow.add(Calendar.DATE, -7);
        Calendar aWeekAgo = rightNow;

        //a for each loop --> for each element in the list
        for (Entry record : trainingRecord){
            if (record.getName().equals(athleteName)){
                //then its one of athleteName's training records
                //now I need to figure out if the athleteName's record is from the last 7 days, including today
                Calendar recordDateAndTime = record.getCalendar();

                //Returns whether this Calendar represents a time after the time represented by the specified Object.
                if (recordDateAndTime.after(aWeekAgo) && recordDateAndTime.before(rightNow)) {
                    //then the record is from the last 7 days, and I need to add it to the output
                    weekRecords = weekRecords + record.getEntry();

                    //now I need to figure out the type of activity so that I can add the distance of the activity to the total weekly distance for that activity
                    //first figure out the type, then add the distance to the total weekly distance for that type of entry/activity
                    //You can make a logical test as to the type of particular object using the instanceof operator.
                    if (record instanceof SprintEntry) {
                        //if the repetitions are more than 1 then the distance is in metres
                        int reps = ((SprintEntry) record).getRepetitions();
                        if (reps > 1) {
                            //then distance is in metres and there are repetitions
                            float runRecordDistanceMetres = record.getDistance() * ((SprintEntry) record).getRepetitions();
                            float runRecordDistanceKM = metresToKilometres(runRecordDistanceMetres);
                            weekRunDistance += runRecordDistanceKM;
                        }
                        else {
                            //the distance is in km
                            weekRunDistance += record.getDistance();
                        }
                    }
                    else if (record instanceof CycleEntry) {
                        weekCycleDistance += record.getDistance();
                    }
                    else if (record instanceof SwimEntry) {
                        weekSwimDistance += record.getDistance();
                    }
                }
            }
        }

        String weekDistances = "Over the last week " + athleteName
                + " ran " + weekRunDistance + "km, "
                + "cycled " + weekCycleDistance + "km, "
                + "and swam " + weekSwimDistance + "km. \n";

        String output = weekDistances + weekRecords;

        //return the output message so that the program displays all records for the named person for the last seven days (including today)
        if (athleteName.isBlank()) {
            return "Athlete name is blank!";
        }
        else {
            return output;
        }
    }

    public float metresToKilometres (float metres) {
        float kilometres = metres / 1000;
        return kilometres;
    }
} // TrainingRecord