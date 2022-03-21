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
   public String lookupEntry (int d, int m, int y) {
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
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord