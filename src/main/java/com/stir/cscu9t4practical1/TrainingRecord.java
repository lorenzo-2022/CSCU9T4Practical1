// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import javax.swing.*;
import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public void addEntry(Entry e){
       //I need to make sure that the entry I want to add to my records is unique.
       //name, day, month, and year is a unique key for running records (athletes do at most one run per day)
       boolean entryInList = false;
       ListIterator<Entry> iter = tr.listIterator();
       while (iter.hasNext()) {
           Entry current = iter.next();
           if (current.getName() == e.getName() &&
           current.getDay() == e.getDay() &&
           current.getMonth() == e.getMonth() &&
           current.getYear() == e.getYear()){
               JOptionPane.showMessageDialog(null, "error: entry has already been added!");
               entryInList = true;
           }
       }
        if (!entryInList){
            tr.add(e);
        }
   } // addClass
   
   // look up the entry of a given day and month
   public String lookupEntryByDate(int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
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
        for (Entry current : tr) {
            if (current.getName().equals(inputName)) {
                result = result + current.getEntry();
            }
        }
        if (result.equals("")) { return "No entries found"; }
        return result;
    }

   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }

    //remove entry with given name, day, month, year
    public String removeEntry(String inputName, int m, int d, int y) {
        String result = "";
        String removedEntryString = "";

        //making a null entry object reference variable to point to the entry object that needs to be removed
        Entry toBeRemoved = null;

        //new list iterator to iterate through the training record objects
        ListIterator<Entry> iterator = tr.listIterator();
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
            tr.remove(toBeRemoved);
        }

        //returning the result message to the method called, saying which entry was removed
        result = result + "Removed entry: " + removedEntryString + "\n";
        return result;
    }
} // TrainingRecord