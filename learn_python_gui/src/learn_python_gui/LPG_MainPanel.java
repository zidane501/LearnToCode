/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author lau
 */

public class LPG_MainPanel extends JPanel{
    public LPG_MainPanel(){
        setLayout(new java.awt.CardLayout());
        setBorder(BorderFactory.createTitledBorder("Main Panel"));
        
        /* Dimension maxDim = getMaximumSize();
        System.out.println(maxDim.toString());
        Dimension prefDim = getPreferredSize();
        prefDim.height = 400;
        prefDim.width = 600;
        
        setPreferredSize(prefDim); */
    }
}
