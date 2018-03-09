/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author lau
 */
public class LPG_VariablesPanel extends JPanel{
    GridBagConstraints varGC = new GridBagConstraints();
    
    LPG_TextPanel varTP1 = new LPG_TextPanel();
    LPG_TextPanel varTP2 = new LPG_TextPanel();
    LPG_TextPanel varTP3 = new LPG_TextPanel();
    
    LPG_GetText varGT = new LPG_GetText();
    
    LPG_HelloExampleCode varEC1 = new LPG_HelloExampleCode();
    
    LPG_PaperVariables paperVars = new LPG_PaperVariables();
    
    LPG_DebugCodePanel varDebugCP = new LPG_DebugCodePanel();
    
    public LPG_VariablesPanel(){
        
        
        setLayout(new GridBagLayout());
        
        varGC.fill = GridBagConstraints.BOTH;
        varGC.weightx = 1;
        varGC.gridx = 0;
        
        varGC.gridy = 0;
        varTP1.setCenterText(varGT.readFile("html-in-swing-components/variables1.txt"));
        add(varTP1, varGC);
        
        varGC.gridy = 1;
        varEC1.setCodeText("myLuckyNumber = 13\n" +
                            "print myLuckyNumber + 1\n" +
                            "print myLuckyNumber\n" +
                            "myLuckyNumber = 5 + 2\n" +
                            "print myLuckyNumber");
        
        varEC1.setEditable(false);
        add(varEC1, varGC);
        
        varGC.gridy = 2;
        varTP2.setCenterText(varGT.readFile("html-in-swing-components/variables2.txt"));
        add(varTP2, varGC);
        
        varGC.gridy = 3;
        add(paperVars, varGC);
        
        varGC.gridy = 4;
        varTP3.setCenterText(varGT.readFile("html-in-swing-components/variables3.txt"));
        add(varTP3, varGC);
        
        varGC.gridy = 5;
        varGC.weightx = 0.1;
        varGC.weighty = 0.1;
        add(varDebugCP,varGC);
    }
}
