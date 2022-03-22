// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    //declaring and initialising FindAllByDate variable - extension 1
    private JButton findAllByDate = new JButton("Find all by date");
    //declaring and initialising entryType JComboBox variable -extension 6-7-8
    private JComboBox entryTypeJComboBox = new JComboBox();
    //declaring and initialing the combo boxes for the repetitions/recovery (run/sprints) - where/none (swim) - terrain/tempo (cycle)
    private JLabel repetitions_where_terrain_JL = new JLabel();
    private JTextField repetitions_where_terrain_JTF = new JTextField(6);
    private JLabel recovery_none_tempo_JL = new JLabel();
    private JTextField recovery_none_tempo_JTF = new JTextField(6);

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

        //adding the JComboBox for the sport entryType to the GUI, and also adding an action listener to it
        add(entryTypeJComboBox);
        entryTypeJComboBox.addActionListener(this);
        //adding the labels and text fields for the repetitions/recovery (run/sprints) - where/none (swim) - terrain/tempo (cycle)
        add(repetitions_where_terrain_JL);
        add(repetitions_where_terrain_JTF);
        add(recovery_none_tempo_JL);
        add(recovery_none_tempo_JTF);
        repetitions_where_terrain_JTF.setEditable(true);
        recovery_none_tempo_JTF.setEditable(true);

        //filling up the JComboBox with the three choices:
        //run/sprint, cycle, swim and their respective options: repetitions/recovery (run/sprints) -terrain/tempo (cycle) - where/none (swim)
        fillChoices(entryTypeJComboBox, repetitions_where_terrain_JL, repetitions_where_terrain_JTF, recovery_none_tempo_JL, recovery_none_tempo_JTF, new String[]{"run/sprints", "cycle", "swim"});

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
            message = lookupEntry();
        }
        if (event.getSource() == entryTypeJComboBox) {
            //if the entry type changes, then I need to update the option 1 and 2 labels and op 1 & 2 text fields.
            fillJTFOptions(entryTypeJComboBox, repetitions_where_terrain_JL, repetitions_where_terrain_JTF, recovery_none_tempo_JL, recovery_none_tempo_JTF);
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

        //adding the trainingType String to the TrainingRecord GUI addEntry method so that when the addEntry button is pressed I know what
        //the athlete's type of training was and can deal that training type accordingly.
        String trainingType = (String) entryTypeJComboBox.getSelectedItem();
        //getting results from the text fields
        String repetitions_terrain_where;
        String recovery_tempo_none;
        repetitions_terrain_where = repetitions_where_terrain_JTF.getText();
        recovery_tempo_none = recovery_none_tempo_JTF.getText();

        //making sure the data entries are correct data
        //calling addEntry method for depending on the entry type
        //run/sprint, cycle, swim and their respective options: repetitions/recovery (run/sprints) -terrain/tempo (cycle) - where/none (swim)

        //run/sprints
        int repetitions;
        int recovery;
        if (trainingType.equals("run/sprints")){
            //do stuff repetitions/recovery
            //repetitions
            try {
                //convert repetitions to integer, if fail return the error to the user
                repetitions = Integer.parseInt(repetitions_terrain_where);
            } catch (NumberFormatException exceptionNum){
                //uh oh spaghetti-o
                JOptionPane.showMessageDialog(null, "repetitions input must be an integer that represents the number of repetitions!");
                return "data insertion error, entry not added";
            }
            //recovery
            try {
                //convert recovery minutes String to Integer
                recovery = Integer.parseInt(recovery_tempo_none);
            } catch (NumberFormatException numExcep){
                JOptionPane.showMessageDialog(null, "recovery time must be an Integer that represents minutes of recovery!");
                return "data insertion error, entry not added";
            }
            //add run/sprints entry
            SprintEntry sprint_run_entry = new SprintEntry(n, d, m, y, h, mm, s, km, repetitions, recovery);
            myAthletes.addEntry(sprint_run_entry);
            return "run/sprints record added";
        }

        //cycle
        String terrain = repetitions_terrain_where;
        String tempo = recovery_tempo_none;
        if (trainingType.equals("cycle")){
            //do stuff terrain/tempo
            //terrain
            if (!(repetitions_terrain_where.equals("gravel") || repetitions_terrain_where.equals("mountain") || repetitions_terrain_where.equals("asphalt"))){
                //then terrain is not of one of three accepted types
                JOptionPane.showMessageDialog(null, "cycle terrain must be either: asphalt, gravel, or mountain.");
                return ("data insertion error, entry not added");
            }
            //tempo
            if (!(recovery_tempo_none.equals("fast") || recovery_tempo_none.equals("moderate") || recovery_tempo_none.equals("slow"))){
                //then tempo is not of one of the three accepted types!
                JOptionPane.showMessageDialog(null, "cycle tempo must be either: fast, moderate, or slow.");
                return ("data insertion error, entry not added");
            }
            else {
                //add cycle entry
                CycleEntry cycle_entry = new CycleEntry(n, d, m, y, h, mm, s, km, terrain, tempo);
                myAthletes.addEntry(cycle_entry);
                return ("cycle record added");
            }
        }

        //swim
        //where
        //pool or outdoors?
        String where = repetitions_terrain_where;
        if (trainingType.equals("swim")){
            //do stuff where/none
            //pool or outdoors
            if (!(repetitions_terrain_where.equals("pool") || repetitions_terrain_where.equals("outdoors"))){
                //then the where is not one of the accepted types: outdoor or pool
                JOptionPane.showMessageDialog(null, "swim whereabouts must be either: pool, or outdoors.");
                return "data insertion error, entry not added";
            }
            else {
                //add swim entry
                SwimEntry swim_entry = new SwimEntry(n, d, m, y, s, mm, h, km, where);
                myAthletes.addEntry(swim_entry);
                return "swim record added";
            }
        }

        //standard entry so that the junit tests for TrainingRecordGUITest still run when the addEntry method is called with an instance of a standard Entry object.
        else {
            Entry e = new Entry(n, d, m, y, h, mm, s, km);
            myAthletes.addEntry(e);
            return what+"entry added";
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

    //adding a method that will fill the combobox.
    public void fillChoices(JComboBox comboBoxMain, JLabel option1Label, JTextField textFieldOption1, JLabel option2Label, JTextField textFieldOption2, String[] choices){
        //emptying combo boxes
        comboBoxMain.removeAllItems();
        //fill that combo box.
        for (int counter = 0; counter < choices.length; counter++){
            comboBoxMain.addItem(choices[counter]);
        }
        //calling a method that fills in the option 1 & 2 combo boxes
        fillJTFOptions(comboBoxMain, option1Label, textFieldOption1, option2Label, textFieldOption2);
    }

    public void fillJTFOptions(JComboBox comboBoxMain, JLabel option1Label, JTextField Option1, JLabel option2Label, JTextField Option2){
        //filling the optional comboboxes based on the contents of the main entry type combobox.
        //repetitions/recovery (run/sprints) - terrain/tempo (cycle) - where/none (swim)
        String entryType = (String) comboBoxMain.getSelectedItem();
        //if entry type is run/sprints
        if (entryType.equals("run/sprints")){
            //do this
            option1Label.setText("repetitions");
            option2Label.setText("recovery");
            Option2.setText("");
            Option2.setEditable(true);
        }
        //if entry type is  swim
        if (entryType.equals("cycle")){
            //do this
            option1Label.setText("terrain");
            option2Label.setText("tempo");
            Option2.setText("");
            Option2.setEditable(true);
        }
        //if entry type is cycle
        if (entryType.equals("swim")){
            //do this
            option1Label.setText("where");
            option2Label.setText("");
            Option2.setText("no_option");
            Option2.setEditable(false);
        }
    }

} // TrainingRecordGUI

