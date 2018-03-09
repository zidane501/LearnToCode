/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author duck
 */
public class LPG_PaperVariables extends JPanel{
    
    private JLabel topLabel = new JLabel("Goal: determine the final values of all variables at the end of the program.");
    private JTextArea codeTA = new JTextArea("first = 2\n" +
                                                "second = 3\n" +
                                                "third = first * second\n" +
                                                "second = third - first\n" +
                                                "first = first + second + third\n" +
                                                "third = second * first");
    
    private JTextPane paperCalc = new JTextPane();
    
    JScrollPane codeJSP = new JScrollPane(codeTA);
    
    TextLineNumber textLineNumbers = new TextLineNumber(codeTA);
    
    private JLabel midLabel = new JLabel("<html>Idea: We use a table to keep track of the values as they change.<br> Scroll to the bottom to see the final answer.</html>");
    
    int margin;
    
    LPG_GetText varPaperGT = new LPG_GetText();
    
    GridBagConstraints paperGC = new GridBagConstraints();
    
    public LPG_PaperVariables(){
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.blue));
        
        margin = 4;
        paperGC.insets = new Insets(margin,margin,margin,margin);
        paperGC.ipadx = margin;
        paperGC.ipady = margin;
        paperGC.fill = GridBagConstraints.BOTH;
        paperGC.weightx = 1;
        paperGC.gridx = 0;
        
        paperGC.gridy = 0;
        add(topLabel,paperGC);
        
        paperGC.ipadx = 5;
        paperGC.ipady = 5;
        paperGC.gridy = 1;
        codeTA.setEditable(false);
        codeTA.setMargin(new Insets(margin,margin,margin,margin));
        codeJSP.setBorder(BorderFactory.createLineBorder(Color.yellow));
        codeJSP.setRowHeaderView( textLineNumbers );
        add(codeJSP,paperGC);
        
        paperGC.gridy = 2;
        add(midLabel,paperGC);
        
        paperGC.gridy = 3;
        paperCalc.setBorder(BorderFactory.createLineBorder(Color.yellow));
        paperCalc.setContentType("text/html");
        paperCalc.setText(varPaperGT.readFile("html-in-swing-components/variablesPaper.txt"));
        paperCalc.setBackground(Color.gray);
        add(paperCalc,paperGC);
    }
}
