package com.tictactoe;// Java Program to create a simple JTextArea
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
class text extends JFrame implements ActionListener {

    // JFrame
     JFrame f;

    // JButton
     JButton b;

    // label to display text
     JLabel l;

    // text area
     JTextArea jt;

    // default constructor
    text()
    {
    }

    // main class
    public void start()
    {
        // create a new frame to store text field and button
        f = new JFrame("textfield");

        // create a label to display text
        l = new JLabel("nothing entered");

        // create a new button
        b = new JButton("submit");

        // create a object of the text class
        text te = new text();

        // addActionListener to button
        b.addActionListener(this);

        // create a text area, specifying the rows and columns
        jt = new JTextArea(10, 10);

        JPanel p = new JPanel();

        // add the text area and button to panel
        p.add(jt);
        p.add(b);
        p.add(l);

        f.add(p);
        // set the size of frame
        f.setSize(300, 300);

        f.show();
    }

    // if the button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("submit")) {
            // set the text of the label to the text of the field
            l.setText(jt.getText());
        }
    }
}
