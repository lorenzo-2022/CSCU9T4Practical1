// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    //declaring FindAllByDate variable - extension 1
    private JButton findAllByDate = new JButton("Find all by date");


    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(addR);
        addR.addActionListener(this);

        //commenting out the lookUpByDate button and replacing it with the findAllByDate button
        //add(lookUpByDate);
        //lookUpByDate.addActionListener(this);

        //adding findAllByDate button to GUI and in so doing, initialising the findAllByDate variable
        add(findAllByDate);
        //adding action listen to the findAllByDate button
        findAllByDate.addActionListener(this);

        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        //commenting out the lookUpByDate action listener event because it is being replaced with the findAllByDate listener and button.
        //if (event.getSource() == lookUpByDate) {
            //message = lookupEntry();
        //}
        if (event.getSource() == findAllByDate){
            //message = "Not Implemented yet";
            message = lookupEntry();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String n = name.getText();
        int m;
        int d;
        int y;
        int h;
        int mm;
        int s;
        float km;

        //improving the program so that it handles bad data in a sensible way instead of failing. - extension 4

        try{
            //let's try to convert our inputs to integers, otherwise we'll catch the exception and let the user know.
            m = Integer.parseInt(month.getText());
            d = Integer.parseInt(day.getText());
            y = Integer.parseInt(year.getText());
            //may as well make sure the h, mm, and s inputs are also integers.
            h = Integer.parseInt(hours.getText());
            mm = Integer.parseInt(mins.getText());
            s = Integer.parseInt(secs.getText());
        }catch(NumberFormatException exception) {
            //we caught a number error! These ain't integers!
            JOptionPane.showMessageDialog(null, "error: day, month, year, hour, minute, and second inputs must be integers!");
            return "data insertion error, entry not added";
        }

        try {
            km = java.lang.Float.parseFloat(dist.getText());
        }catch(NumberFormatException numberFormatException){
            //that's not a floating point number!
            JOptionPane.showMessageDialog(null, "error: distance must be a floating point number!");
            return "data insertion error, entry not added";
        }

        if (n.isBlank()){
            JOptionPane.showMessageDialog(null, "error: entries must have a name!");
            return "data insertion error, entry not added";
        }

        else{
            Entry e = new Entry (n, d, m, y, h, mm, s, km);
            myAthletes.addEntry(e);
            return "record added";
        }
    }
    
    public String lookupEntry() {
        int m;
        int d;
        int y;

        try{
            //let's try to convert our inputs to integers, otherwise we'll catch the exception and let the user know.
            m = Integer.parseInt(month.getText());
            d = Integer.parseInt(day.getText());
            y = Integer.parseInt(year.getText());
        }catch(NumberFormatException exception) {
            //we caught a number error! These ain't integers!
            JOptionPane.showMessageDialog(null, "error: day, month, and year inputs must be integers!");
            return "data insertion error, entry not looked up";
        }


        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

