/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author lau
 */

public class LPG_TopPanel extends JPanel implements ActionListener{
    
    private JButton helloButton =  new JButton("Hello"); 
    private JButton variablesButton =  new JButton("Variables");
    private JButton errorsButton =  new JButton("Errors");
    private JButton functions =  new JButton("Functions");
    private JButton debugButton = new JButton("DeBugger");
   
    private LPG_CommunicationInterface panelListener;
    
    public LPG_TopPanel(){
        
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        setBorder(BorderFactory.createTitledBorder("Top Panel"));
        
        //////////////////// Hello-button /////////////////////
        
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0; 
        gc.gridy = 0;
//        gc.anchor = GridBagConstraints.LINE_END;
        gc.fill = GridBagConstraints.NONE; 
        gc.insets = new Insets(0,0,0,5); // Padding (Top, Left, Bottom, Right)
        
        helloButton.addActionListener(this);
        add(helloButton, gc);
        
        //////////////////// variablesButton-button /////////////////////

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1; 
        gc.gridy = 0;
//        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5); // Padding (Top, Left, Bottom, Right)
        
        variablesButton.addActionListener(this);
        add(variablesButton, gc);
        
        //////////////////// variablesButton-button /////////////////////

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 2; 
        gc.gridy = 0;
//        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5); // Padding (Top, Left, Bottom, Right)
        
        debugButton.setBackground(Color.blue);
        debugButton.setForeground(Color.white);
        debugButton.addActionListener(this);
        add(debugButton, gc);
       
    }
    public void setPanelListener(LPG_CommunicationInterface listener){
        this.panelListener = listener;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        JButton clicked = (JButton)e.getSource();
        
        if(clicked == helloButton){
            if(panelListener != null){
                panelListener.setBackgroundColor(Learn_python_gui.hello);
            }}
        else if(clicked == variablesButton){
            if(panelListener != null){
                panelListener.setBackgroundColor(Learn_python_gui.variables);
            }}
        else if(clicked == debugButton){
            if(panelListener != null){
                panelListener.setBackgroundColor(Learn_python_gui.debugger);
            }}

    }
    
    /*private void initComponents() { 
        helloButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helloButtonActionPerformed(evt);
            }
        });
        
        variablesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                variablesButtonActionPerformed(evt);
            }
        });
    }
    
    private void helloButtonActionPerformed(ActionEvent evt) {                                   
        CardLayout card = (CardLayout) Learn_python_gui.mainPanel.getLayout();
        card.show(Learn_python_gui.helloPanel, "helloPanel");
    }  
    private void variablesButtonActionPerformed(ActionEvent evt) {                                   
        CardLayout card = (CardLayout) Learn_python_gui.mainPanel.getLayout();
        card.show(Learn_python_gui.variablesPanel, "variablesPanel");
    }  
    */
   
    
}
