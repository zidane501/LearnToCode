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
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author lau
 */
public class LPG_HelloPanel extends JPanel{
    
    LPG_TextPanel helloTP1 = new LPG_TextPanel();
    LPG_HelloExampleCode helloEC1 = new LPG_HelloExampleCode();
    LPG_GetText GT = new LPG_GetText();
    String  intro = GT.readFile("html-in-swing-components/hello1.txt");
    
    LPG_TextPanel helloText2 = new LPG_TextPanel();
    String  text2 = GT.readFile("html-in-swing-components/hello2.txt");
    
    LPG_HelloExampleCode helloEC2 = new LPG_HelloExampleCode();
    
    LPG_TextPanel helloText3 = new LPG_TextPanel();
    String  text3 = GT.readFile("html-in-swing-components/hello3.txt");
    
    LPG_HelloExampleCode helloEC3 = new LPG_HelloExampleCode();

    public LPG_HelloPanel(){
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints hello_gc = new GridBagConstraints();
        
        //////////// Intro text panel ////////////
        hello_gc.weightx = 1;
        hello_gc.weighty = 0.7;
        
        hello_gc.gridx = 0; 
        hello_gc.gridy = 0;

        hello_gc.fill = GridBagConstraints.BOTH;
        
        helloTP1.setCenterText(intro);
       
        add(helloTP1, hello_gc);
        
        //////////// Intro code panel ////////////
        
        hello_gc.weightx = 1;
        hello_gc.weighty = 0.3;
        
        hello_gc.gridx = 0; 
        hello_gc.gridy = 1;

        hello_gc.fill = GridBagConstraints.BOTH;
        
        helloEC1.setBorder(BorderFactory.createLineBorder(Color.blue));
        helloEC1.setCodeText("print 'hello world'\n\n\n\n");
        add(helloEC1, hello_gc);
        
        /////////////////////// Second text ////////////////////
        
        hello_gc.weightx = 1;
        hello_gc.weighty = 0.3;
        
        hello_gc.gridx = 0; 
        hello_gc.gridy = 2;
        
        hello_gc.fill = GridBagConstraints.BOTH;
        
        helloText2.setBorder(BorderFactory.createLineBorder(Color.blue));
        
        helloText2.setCenterText(text2);
        
        add(helloText2, hello_gc);

        //////////// Second code panel ////////////
        
        hello_gc.weightx = 1;
        hello_gc.weighty = 0.3;
        
        hello_gc.gridx = 0; 
        hello_gc.gridy = 3;

        hello_gc.fill = GridBagConstraints.BOTH;
        
        helloEC2.setBorder(BorderFactory.createLineBorder(Color.blue));
        helloEC2.setCodeText("print hello world\n\n\n\n");
        add(helloEC2, hello_gc);
        
        /////////////////////// Third text ////////////////////
        
        hello_gc.weightx = 1;
        hello_gc.weighty = 0.3;
        
        hello_gc.gridx = 0; 
        hello_gc.gridy = 4;
        
        hello_gc.fill = GridBagConstraints.BOTH;
        
        helloText3.setBorder(BorderFactory.createLineBorder(Color.blue));
        helloText3.setCenterText(text3);
        
        add(helloText3, hello_gc);
        
        //////////// Third code panel ////////////
        
        hello_gc.weightx = 1;
        hello_gc.weighty = 0.3;
        
        hello_gc.gridx = 0; 
        hello_gc.gridy = 5;

        hello_gc.fill = GridBagConstraints.BOTH;
        
        helloEC3.setBorder(BorderFactory.createLineBorder(Color.blue));
        helloEC3.setCodeText("\n\n\n\n");
        
        helloEC3.setProblem("hello0");
        
        add(helloEC3, hello_gc);
    }
}
