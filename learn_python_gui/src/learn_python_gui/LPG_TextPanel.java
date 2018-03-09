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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author lau
 */
public class LPG_TextPanel extends JPanel{
    
    
    JTextPane centerTA = new JTextPane();
    
    JScrollPane cant_set_margin_around_bordered_JTA_JSP = new JScrollPane(centerTA);
    public String centerText;
    
    
    
    private int margin;
    
    public LPG_TextPanel(){
        
        setLayout(new GridBagLayout());
        GridBagConstraints hello_gc = new GridBagConstraints();
        
        margin = 4;
        
        
        //////////////////// Center Text Area/////////////////////

        hello_gc.weightx = 1;
        hello_gc.weighty = 1;

        hello_gc.gridx = 0; 
        hello_gc.gridy = 0;

        hello_gc.fill = GridBagConstraints.BOTH;
        
        centerTA.setContentType("text/html");
        centerTA.setMargin(new Insets(margin,3*margin,margin,3*margin));
        cant_set_margin_around_bordered_JTA_JSP.setBorder(BorderFactory.createLineBorder(Color.blue));
        
        add(cant_set_margin_around_bordered_JTA_JSP, hello_gc);
    }
    /*public void setIF(LPG_HelloIF IF){
        this.helloIF = IF;
    }*/

    public void setCenterText(String text){
        centerTA.setText(text);
    }
}
