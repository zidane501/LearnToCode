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
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author lau
 */
public class Learn_python_gui extends JFrame{
    
    int w,h;
    
    LPG_TopPanel topPanel = new LPG_TopPanel();
    LPG_MainPanel mainPanel = new LPG_MainPanel();
    LPG_HelloPanel helloPanel = new LPG_HelloPanel();
    LPG_VariablesPanel variablesPanel = new LPG_VariablesPanel();
    LPG_DebugCodePanel debuggerPanel = new LPG_DebugCodePanel();
    
    JScrollPane helloPanelScroll = new JScrollPane(helloPanel);
    JScrollPane varPanelScroll = new JScrollPane(variablesPanel);
    
    JTextPane leftTA = new JTextPane();
    JTextPane rightTA = new JTextPane();
    JTextPane lastTA = new JTextPane();
    
    public static String hello, variables, debugger;
    
    
    public Learn_python_gui(){
        
        super("Lær Python");
                
        w = 700; h = 700;
        setSize(w,h);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new GridBagLayout());
        GridBagConstraints main_gc = new GridBagConstraints();
        
        //////////////////// Top Panel/////////////////////
        
        main_gc.weightx = 1;
        main_gc.weighty = 0.1;
        
        main_gc.gridx = 0; 
        main_gc.gridy = 0;
        
        //main_gc.anchor = GridBagConstraints.BASELINE;
        main_gc.gridwidth = 3;
                
        //main_gc.anchor = GridBagConstraints.LINE_END;
        main_gc.fill = GridBagConstraints.BOTH; 
        // main_gc.insets = new Insets(0,0,0,5); // Padding (Top, Left, Bottom, Right)
        add(topPanel, main_gc);
        
        
        //////////////////// Left Text Area/////////////////////
        
        main_gc.anchor = GridBagConstraints.LINE_START;
        main_gc.fill = GridBagConstraints.BOTH; 
        //main_gc.anchor = GridBagConstraints.WEST; 
        main_gc.gridwidth = 1;

        main_gc.weightx = 0.1;
        main_gc.weighty = 0.9;

        main_gc.gridx = 0; 
        main_gc.gridy = 1;
        
        leftTA.setBorder(BorderFactory.createLineBorder(Color.blue));
        leftTA.setContentType("text/html");
        leftTA.setText("<html><body bgcolor=\"#b3ff99\"></body></html>");
        
        add(leftTA, main_gc);
        
        
        //////////////////// Main Panel/////////////////////

        //main_gc.anchor = GridBagConstraints.CENTER; 
        main_gc.gridwidth = 1;
        main_gc.fill = GridBagConstraints.BOTH; 
        main_gc.weightx = 0.8;
        //main_gc.weighty = 0.7;

        main_gc.gridx = 1; 
        main_gc.gridy = 1;

        //main_gc.fill = GridBagConstraints.BOTH;

        hello = "hello"; mainPanel.add(helloPanelScroll, hello);
        variables = "variables"; mainPanel.add(varPanelScroll, variables);
        debugger = "debugger"; mainPanel.add(debuggerPanel, debugger);
        
        JScrollPane mainScroll = new JScrollPane(mainPanel);
        
        add(mainPanel, main_gc);
        
        topPanel.setPanelListener(new LPG_CommunicationInterface(){
            @Override
            public void setBackgroundColor(String panel) {
                CardLayout card = (CardLayout) mainPanel.getLayout();
                card.show(mainPanel, panel);
            }

            /*@Override
            public void setCenterText(String text) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }*/
        });
        
        
        //////////////////// Right Text Area/////////////////////
        
        main_gc.fill = GridBagConstraints.BOTH; 
        
        main_gc.weightx = 0.1;
        
        main_gc.gridx = 2; 
        main_gc.gridy = 1;
        
        main_gc.gridwidth = 1;
        
        rightTA.setBorder(BorderFactory.createLineBorder(Color.blue));
        rightTA.setContentType("text/html");
        rightTA.setText("<html><body bgcolor=\"#b3ff99\"></body></html>");
        add(rightTA, main_gc);
        
       
        
    };
    
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Learn_python_gui().setVisible(true);
            }
        });
    }
    
}
