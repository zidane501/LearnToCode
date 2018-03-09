/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author lau
 */
public class LPG_DebugVarsTable extends JPanel {
    private JTable variablesTable;
    
    private LPG_DebugTableModel tableModel;
    
    public LPG_DebugVarsTable(){
        
        tableModel = new LPG_DebugTableModel();
        
        variablesTable = new JTable(tableModel);
         
        setLayout(new BorderLayout());
        
        add(new JScrollPane(variablesTable), BorderLayout.CENTER);
        
        
    }
    public void setData(ArrayList<ArrayList<String>> varsList){
            tableModel.setData(varsList);
    }
    
    public void refresh(){
        //System.out.println("Fire table away!");
        tableModel.fireTableDataChanged();
    }
}
